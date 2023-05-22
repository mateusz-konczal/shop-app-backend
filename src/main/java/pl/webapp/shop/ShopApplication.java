package pl.webapp.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ShopApplication {

    public static final String PASSWORD_REGEX = "^(?=.*[a-ząćęłńóśźż])(?=.*[A-ZĄĆĘŁŃÓŚŹŻ])(?=.*\\d)" +
            "[a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ\\d\\S]{8,}$";

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
