package br.com.pismo.customerassessment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Customer Assessment API",
                version = "v1",
                description = "REST API for managing customer accounts and transactions. "
                        + "Operation types: 1=PURCHASE, 2=INSTALLMENT_PURCHASE, 3=WITHDRAWAL, 4=PAYMENT."
        )
)
public class OpenApiConfig {
}
