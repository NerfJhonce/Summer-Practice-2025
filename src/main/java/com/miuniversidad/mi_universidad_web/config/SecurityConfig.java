package com.miuniversidad.mi_universidad_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Habilita las funcionalidades de Spring Security
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/", "/login", "/register").permitAll()


                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()


                        .anyRequest().authenticated()
                )
                // Configuración del formulario de login
                .formLogin(form -> form
                        .loginPage("/login")      // Define la URL de tu página de login
                        .defaultSuccessUrl("/", true) // Redirige a la raíz después del login
                        .permitAll()
                )
                // Configuración de la salida (logout)
                .logout(logout -> logout
                        // ✅ CORRECCIÓN CLAVE: Usamos logoutRequestMatcher
                        // Esto asegura que Spring Security maneje correctamente el POST a /logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/") // Redirige a la página de inicio después del logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

    // Definición del PasswordEncoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}