//package com.book.sphere.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
//
//	@Bean
//	public JwtDecoder jwtDecoder() {
//		return NimbusJwtDecoder
//				.withJwkSetUri("https://your-keycloak-server/auth/realms/your-realm/protocol/openid-connect/certs")
//				.build();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtAuthenticationConverter());
//
//		http.cors().and().csrf().disable().authorizeRequests()
//				.antMatchers("/auth/**", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
//						"/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**",
//						"/webjars/**", "/swagger-ui.html")
//				.permitAll().anyRequest().authenticated().and().oauth2ResourceServer().jwt()
//				.jwtAuthenticationConverter(jwtAuthenticationConverter);
//
//	}
//
//}
