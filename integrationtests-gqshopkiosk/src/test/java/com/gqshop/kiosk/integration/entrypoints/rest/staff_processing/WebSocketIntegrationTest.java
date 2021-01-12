package com.gqshop.kiosk.integration.entrypoints.rest.staff_processing;

import static  java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

import  java.lang.reflect.Type ;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import  java.util.List ;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import  org.springframework.messaging.converter.MappingJackson2MessageConverter ;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import  org.springframework.messaging.simp.stomp.StompFrameHandler ;
import  org.springframework.messaging.simp.stomp.StompHeaders ;
import  org.springframework.messaging.simp.stomp.StompSession ;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import  org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter ;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.MimeType;
import org.springframework.web.socket.client.WebSocketClient;
import  org.springframework.web.socket.client.standard.StandardWebSocketClient ;
import  org.springframework.web.socket.messaging.WebSocketStompClient ;
import  org.springframework.web.socket.sockjs.client.SockJsClient ;
import org.springframework.web.socket.sockjs.client.Transport;
import  org.springframework.web.socket.sockjs.client.WebSocketTransport ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gqshop.kiosk.entrypoint.model.MessageVO;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class WebSocketIntegrationTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SEND_UPDATE_STATUS_ENDPOINT = "/app/order/status";
    private static final String SEND_ORDER_ENDPOINT = "/app/order";
    private static final String SEND_TEST = "/app/welcome";
    private static final String SUBSCRIBE_ENDPOINT = "/topic/reflection";
    private static final String SUBSCRIBE_TEST = "/topic/greetings";

	private WebSocketStompClient webSocketStompClient;

	@Autowired
	Environment env;

	@BeforeEach
	public void setUp() throws Exception {
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport( new StandardWebSocketClient()));	
		WebSocketClient transport = new SockJsClient(transports);
		this.webSocketStompClient = new WebSocketStompClient(transport);
	}

	private CompletableFuture<MessageVO> messageCompletableFuture = new CompletableFuture<>();
	
	@Test
	public void verifyGreetingIsReceived() throws Exception {

	    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1);

	    webSocketStompClient.setMessageConverter(new StringMessageConverter());

	    StompSession session = webSocketStompClient
	      .connect(getWsPath(), new StompSessionHandlerAdapter() {})
	      .get(1, SECONDS);

	    session.subscribe(SUBSCRIBE_TEST, new StompFrameHandler() {
	      @Override
	      public Type getPayloadType(StompHeaders headers) {
	        return String.class;
	      }

	      @Override
	      public void handleFrame(StompHeaders headers, Object payload) {
	        blockingQueue.add((String) payload);
	      }
	    });
	    session.send(SEND_TEST, "Mike");

	    assertEquals("Hello, Mike!", blockingQueue.poll(1, SECONDS));
	}

	@Test
	public void whenUpdateOrderStatusWithOrderId_thenReturnMessage() throws Exception {
	 
	  BlockingQueue<MessageVO> blockingQueue = new ArrayBlockingQueue(1);

	  MimeType mimetype = new MimeType("application", "json", StandardCharsets.UTF_8);
	  MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter(mimetype);

	  webSocketStompClient.setMessageConverter(jacksonMessageConverter);

	  StompSessionHandler sessionHandler = new LogConnectedSessionHandler();

	  StompSession session = webSocketStompClient
			    .connect(getWsPath(), sessionHandler)
			    .get(1, SECONDS);
	  
	  int orderId = 1;
	  MessageVO toSendVO = new MessageVO("test", orderId ,"end");

	  session.subscribe(SUBSCRIBE_ENDPOINT, new StompFrameHandler() { 
		  @Override
		  public Type getPayloadType(StompHeaders headers) {
			  return MessageVO.class;
		  }
		  @Override
          public void handleFrame(StompHeaders headers, Object payload) {
			  blockingQueue.offer((MessageVO)payload);
          } 
	  });
	  
	  session.send(SEND_UPDATE_STATUS_ENDPOINT, toSendVO);
	  
	  MessageVO receivedVO = null;
	  assertEquals(receivedVO, blockingQueue.poll(1, SECONDS));

//	  ObjectMapper objectMapper = new ObjectMapper()
//          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//          .registerModule(new JavaTimeModule());
//	  System.out.println("payload : " + blockingQueue.poll(1,SECONDS)); 
//	  MessageVO accessLog = objectMapper.readValue(blockingQueue.poll(1,SECONDS), MessageVO.class);
//	  System.out.println("accessLog : " + accessLog.getSendMessage()); 
	  
	}
	
	//Using CompletableFuture
//	private class CreateStompFrameHandler implements StompFrameHandler {
//        @Override
//        public Type getPayloadType(StompHeaders stompHeaders) {
//            return MessageVO.class;
//        }
//
//        @Override
//        public void handleFrame(StompHeaders stompHeaders, Object o) {
//            completableFuture.complete((MessageVO) o);
//        }
//	}

//	@Test
//	public void whenUpdateOrderStatusWithOrderId_thenReturnMessage() throws Exception {
//		
//		webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//		StompSession session = webSocketStompClient
//		    .connect(getWsPath(), new StompSessionHandlerAdapter() {})
//		    .get(1, SECONDS);
//		
//		int orderId = 1;
//		session.send(SEND_UPDATE_STATUS_ENDPOINT, new MessageVO("test", orderId ,"end"));
//		  
//		session.subscribe(SUBSCRIBE_ENDPOINT, new CreateStompFrameHandler());
//		  
//		MessageVO sendAndreturedVO = completableFuture.get(5, SECONDS);
//		
//		System.out.println("completableFuture.size() [after send] : " + completableFuture.getNumberOfDependents());
//		  
//		assertNotNull(sendAndreturedVO);
//		assertEquals("success", sendAndreturedVO.getSendMessage());
//	}
	

	//send에 대해서는 현재 구현이안되있으니 할필요가 x. 추후에 참고
	//이건 테스트실행하고 postman으로 쏴줘야될듯
//	@Test
//	  public void verifyWelcomeMessageIsSent() throws Exception {
//	    CountDownLatch latch = new CountDownLatch(1);
//
//	    webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//	    StompSession session = webSocketStompClient
//	      .connect(getWsPath(), new StompSessionHandlerAdapter() {
//	      })
//	      .get(1, SECONDS);
//
//	    session.subscribe("/app/chat", new StompFrameHandler() {
//
//	      @Override
//	      public Type getPayloadType(StompHeaders headers) {
//	        return MessageVO.class;
//	      }
//
//	      @Override
//	      public void handleFrame(StompHeaders headers, Object payload) {
//	    	  System.out.println("Received message: " + payload);  
//	        latch.countDown();
//	      }
//	    });
//
//	    if (!latch.await(1, TimeUnit.SECONDS)) {
//	      fail("Message not received");
//	    }
//	}
	
	private String getWsPath() {
		return String.format("ws://localhost:%s/ws-endpoint", env.getProperty("server.port"));
	}

	private static class LogConnectedSessionHandler extends StompSessionHandlerAdapter {
		
		public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
			System.out.println("STOMP Client connected");
		}

		@Override
		public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
			System.out.println("handleException: StompSession:" + session + " StompCommand:" + command + " Exception:" + exception);
		}
	
		@Override
		public void handleTransportError(StompSession session, Throwable exception) {
			System.out.println("handleTransportError: StompSession:" + session + " Exception:" + exception);
		}
	}
	
	
}
