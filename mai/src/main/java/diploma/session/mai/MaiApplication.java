package diploma.session.mai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class MaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaiApplication.class, args);
	}
	//TODO добавить роли для пользователей, админ, например, может менять показатели, которые будут красным, зелёным светиться.
	//Добавить страницу заказа товара у поставщика, а также заняться реализацией шаблона заказа
	//карты и нейронки, 
	//также можно сделать график закупок по месяцам и поставщикам
	//потом фронтом
	//Потом можно переходить на android приложение
	//Добавить страницу ошибки /error
}