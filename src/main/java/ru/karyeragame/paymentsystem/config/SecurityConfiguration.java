package ru.karyeragame.paymentsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // не подключается фильтр SecurityFilterChain ...

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
////                .csrf((csrf) -> csrf
////                        .ignoringRequestMatchers("/api/*")
////                )
////                .csrf((csrf) -> csrf.disable())
//                .httpBasic(withDefaults())
//                .authorizeHttpRequests(authorizeHttpRequest -> {
////                    authorizeHttpRequest
////                            .requestMatchers("/karyeraSecurity/registration").permitAll();
////
//                    authorizeHttpRequest
////                            .requestMatchers("/karyerapay/admin/findUser").hasAnyRole("ADMIN")
////                            .requestMatchers("/karyerapay/admin/findUser").hasAuthority("ADMIN")
//                            .requestMatchers("/karyerapay/admin/findUser").hasRole("ADMIN")
////                            .requestMatchers("/karyerapay/admin/findUser").access(AuthenticationManager.hasRole("ADMIN"))
////                            .requestMatchers("/karyerapay/admin/findUser").access(new WebExpressionAuthorizationManager("hasRole('ADMIN')"))
//                            .anyRequest().authenticated();
////                    .requestMatchers("/karyerapay/**", "/registration", "/confirmation").permitAll()
////                    .anyRequest().authenticated()
//                }).build();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/karyeraSecurity/registration", "/karyeraSecurity/confirmation", "/mail");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
