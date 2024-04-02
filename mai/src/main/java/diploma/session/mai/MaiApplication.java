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
	//Добавить в БД в товары колонку ячейки склада да и вообще таблицы складов в целом
	//добавить в order_details колонку на какой склад едет заказ и в какую ячейку
	//карты и нейронки, 
	//также можно сделать график закупок по месяцам и поставщикам
	//потом фронтом
	//Потом можно переходить на android приложение
	//Добавить страницу ошибки /error
	//git push -f origin main
}