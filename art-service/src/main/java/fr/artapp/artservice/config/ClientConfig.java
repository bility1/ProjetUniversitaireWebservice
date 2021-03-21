
/*
package fr.artapp.artservice.config;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.security.Principal;

import static org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory.AUTHORIZATION_HEADER;

public class ClientConfig implements ClientHttpRequestInterceptor {

    protected KeycloakSecurityContext getKeycloakSecurityContext() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Principal principal = attributes.getRequest().getUserPrincipal();
        if (principal == null) {
            throw new IllegalStateException("Cannot set authorization header because there is no authenticated principal");
        }
        if (!(principal instanceof KeycloakPrincipal)) {
            throw new IllegalStateException(
                    String.format(
                            "Cannot set authorization header because the principal type %s does not provide the KeycloakSecurityContext",
                            principal.getClass()));
        }
        return ((KeycloakPrincipal) principal).getKeycloakSecurityContext();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        KeycloakSecurityContext context = this.getKeycloakSecurityContext();
        httpRequest.getHeaders().set(AUTHORIZATION_HEADER, "Bearer " + context.getTokenString());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }


 */

/*
//methode 2?
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ClientHttpRequestInterceptor requestInterceptor() {
        return (requestTemplate, body, execution) -> {
            KeycloakSecurityContext context = getKeycloakSecurityContext();
            requestTemplate.getHeaders().add("Authorization", "Bearer " + context.getTokenString());
            return  execution.execute(requestTemplate, body);
        };
    }

    protected KeycloakSecurityContext getKeycloakSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken token;
        KeycloakSecurityContext context;

        if (authentication == null) {
            throw new IllegalStateException("Cannot set authorization header because there is no authenticated principal");
        }

        if (!KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            throw new IllegalStateException(
                    String.format(
                            "Cannot set authorization header because Authentication is of type %s but %s is required",
                            authentication.getClass(), KeycloakAuthenticationToken.class)
            );
        }

        token = (KeycloakAuthenticationToken) authentication;
        context = token.getAccount().getKeycloakSecurityContext();

        return context;

    }


}
*/