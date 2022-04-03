//package com.weat.weat.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.media.StringSchema;
//import io.swagger.v3.oas.models.parameters.HeaderParameter;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.customizers.OpenApiCustomiser;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.weat.weat.common.utils.Constants;
//
//@Configuration
//public class OpenAPIConfig {
//
//	@Bean
//	public OpenAPI customOpenAPI() {
//		return new OpenAPI()
//				.components(new Components().addSecuritySchemes("bearer-key",
//						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
//						.addParameters(Constants.CLIENT_HEADER,
//								new HeaderParameter().required(false).name(Constants.CLIENT_HEADER).description("Client header for recaptcha")
//										.schema(new StringSchema())))
//				.info(new Info()
//						.title("Weat Rest Appliciation")
//						.version("1.0.0")
//						.description("Weat Backend related stuff"));
//	}
//
//	@Bean
//	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
//		return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
//				.forEach(operation -> operation
//						.addParametersItem(new HeaderParameter().example(Constants.Client.WEB).$ref("#/components/parameters/" + Constants.CLIENT_HEADER)));
//	}
//}