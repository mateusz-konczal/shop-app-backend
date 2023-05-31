package pl.webapp.shop.order.service.payment.p24;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "app.payment.p24")
class PaymentMethodP24Config {

    private boolean testMode;
    private Integer merchantId;
    private Integer posId;
    private String url;
    private String testUrl;
    private String apiUrl;
    private String testApiUrl;
    private String urlReturn;
    private String testUrlReturn;
    private String urlStatus;
    private String testUrlStatus;
    private String crc;
    private String testCrc;
    private String secretId;
    private String testSecretId;
    private List<String> servers;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    WebClient p24Client() {
        return WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication(posId.toString(), testMode ? testSecretId : secretId))
                .baseUrl(testMode ? testApiUrl : apiUrl)
                .build();
    }
}
