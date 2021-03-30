/*
package fr.artapp.artservice.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {
    private String token;
    public RestTemplateHeaderModifierInterceptor(String token) {
        this.token =token;
    }

    @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws
        IOException {

            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().add("Foo", token);
            return response;
        }
    }

*/