package com.gqshop.kiosk.entrypoint.web.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlingController implements ErrorController {

	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {
		//Error code -> RequestDispatcher.ERROR_STATUS_CODE
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String errorCode = status.toString();
		
		String message;
		switch(errorCode) {
		case "403": message = "!!!!!!!STAFF ONLY!!!!!!!!";
					break;
		case "404": message = "!!This is not the web page you are looking for!!!";
					break;
		case "500": message = "!!!!Something's gone wrong!!!!!";
					break;
		default: message = "~~Something wrong~~";
					break;
		}
		
		model.addAttribute("errorType", errorCode);
		model.addAttribute("errorMessage", message);
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "error";
	}
}
