package com.sweetsdelight_bk.Utils;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
	
	@Bean
	public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
		
		
		http.sessionManagement(se -> se.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
		cors(cors ->{
			cors.configurationSource(new org.springframework.web.cors.CorsConfigurationSource(){
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration configuration= new CorsConfiguration();
					configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
					configuration.setAllowedMethods(Collections.singletonList("*"));
					configuration.setAllowCredentials(true);
					configuration.setAllowedHeaders(Collections.singletonList("*"));
					configuration.setExposedHeaders(Arrays.asList("Authorization"));
					return configuration;
				}
			});
		}) .authorizeHttpRequests(auth -> auth.requestMatchers("/sweetDelight/customerUser/register","/sweetDelight/admin/register").permitAll()
				.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
				.requestMatchers("/sweetDelight/admin/logini").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/sweetDelight/customerUser/logini").permitAll()
				.requestMatchers(HttpMethod.GET, "/sweetDelight/customerUser/logini", "/sweetDelight/categories",
						"/product/product/getallproduct", "/product/product/getallavailableproduct", 
						"/product/product/getallavailableproduct/{category}",
						"/sweetDelight/searchbyname").permitAll()
                .requestMatchers(HttpMethod.GET, "/sweetDelight/admin/users", "/sweetDelight/admin/customers",
                        "/sweetDelight/admin/customers/{customerId}", "/sweetDelight/admin/products/{productId}",
                        "/sweetDelight/admin/products/{prodId}/categories/{catId}", "/sweetDelight/admin/allcarts",
                        "/sweetDelight/admin/bills/allbills", "/sweetDelight/admin/orderbill/{customerbillid}",
                        "/sweetDelight/admin/orders/allorders").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/sweetDelight/admin/products/{categoryId}",
                        "/sweetDelight/admin/category").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/sweetDelight/admin/product/update/{productId}",
                        "/sweetDelight/admin/categories/update/{categoryId}", 
                        "/sweetDelight/admin/{customerId}/role/{role}",
                        "/sweetDelight/admin/{orderId}/status/{status}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/sweetDelight/admin/products/{productId}",
                        "/sweetDelight/admin/categories/{categoryId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/sweetDelight/customerUser/update/{customerId}",
                        "/sweetDelight/customerUser/carts/{customerId}/add/{productId}",
                        "/sweetDelight/customerUser/carts/{customerId}/remove/{productId}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/sweetDelight/customerUser/carts/{customerId}",
                        "/sweetDelight/customerUser/price/{orderid}", "/sweetDelight/customerUser/getallorders/{customerId}",
                        "/sweetDelight/customerUser/cart/{id}/product").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/sweetDelight/products",
                        "/sweetDelight/categories/{id}", "/sweetDelight/products/sorted",
                        "/sweetDelight/category",
                        "/sweetDelight/product/getallproduct", "/sweetDelight/product/getallavailableproduct")
                    .hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		
		return http.build() ;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}

}


