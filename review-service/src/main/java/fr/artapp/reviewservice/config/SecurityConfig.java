package fr.artapp.reviewservice.config;


import org.apache.http.HttpHeaders;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/hello").authenticated()
                .anyRequest()
                .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }


/* webflux donc webclient ?
    @Bean
    @RequestScope
    public Httpc keycloakRestTemplate(HttpServletRequest inReq) {
        //récupérer l'en-tête d'authentification de la demande entrante
        final String authHeader =
                inReq.getHeader(HttpHeaders.AUTHORIZATION);
        final RestTemplate restTemplate = new RestTemplate();
        //
        //ajouter un token seulement si un en-tête d'authentification entrant existe
        if (authHeader != null && !authHeader.isEmpty()) {
            // puisque l'en-tête doit être ajouté à chaque requête sortante,
            // ajoute un intercepteur qui gère cela.
            restTemplate.getInterceptors().add(
                    (outReq, bytes, clientHttpReqExec) -> {
                        outReq.getHeaders().set(
                                HttpHeaders.AUTHORIZATION, authHeader
                        );
                        return clientHttpReqExec.execute(outReq, bytes);
                    });
        }
        return restTemplate;
    }

 */

}