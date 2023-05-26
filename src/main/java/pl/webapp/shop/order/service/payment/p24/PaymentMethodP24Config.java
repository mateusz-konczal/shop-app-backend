package pl.webapp.shop.order.service.payment.p24;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    private String crc;
    private String testCrc;
    private String secretId;
    private String testSecretId;
}
