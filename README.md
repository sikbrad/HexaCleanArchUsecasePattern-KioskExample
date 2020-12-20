# HexaCleanArchUsecasePattern-KioskExample
McDonalds kiosk example, using usecase-centric clean architecture.



Techs used : Spring Boot, Maven, H2, Mockito, JUnit5, slf4j logger, JDBC

Inspired by : jivimberg's [hexagonal-architecture](https://github.com/jivimberg/hexagonal-architecture) and mattia-battiston's [clean-architecture-example](https://github.com/mattia-battiston/clean-architecture-example).



## To Run

Open in eclipse. Select the imported project, and in context menu, 'Maven' > 'Update Project'. Then in 'adapters-gqshopkiosk' submodule, in context menu, 'Run As' > 'SpringBoot App'.



## Example Domain

### Business Model

![](https://image.chosun.com/sitedata/image/201702/20/2017022001601_0.jpg)

![McDonalds Kiosk](https://image.ytn.co.kr/general/jpg/2019/0720/201907200800071408_img_02.jpg)

In McDonald, customer orders food with kiosk, and staff takes care of it. The process of this ordering is simulated.

* First, menu is displayed on the kiosk screen, and customer orders food menu.
* After order is taken, 'waiting display' shows order status, both 'In progress(right)' and 'Finished(left)'.
* Staff cooks food, and mark as 'cooked', then 'waiting display' shows the status.
* The customer takes the food, and the staff marks the order as 'taken'.



### Usecases

- customer_ordering
  - get_menu
  - create_order
  - get_orders
- staff_serving
  - get_order
  - update_order



## License

"THE ROOT BEER LICENSE" (Revision 2):

<sikbrad@gmail.com> wrote this file. As long as you retain this notice you
can do whatever you want with this stuff. If we meet some day, and you think
this stuff is worth it, you can buy me a root beer in return.