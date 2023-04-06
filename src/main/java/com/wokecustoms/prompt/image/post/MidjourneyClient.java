package com.wokecustoms.prompt.image.post;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.wokecustoms.prompt.image.exception.ServiceErrorCode;
import com.wokecustoms.prompt.image.exception.ServiceException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MidjourneyClient {

    private static final String MIDJOURNEY_URL = "https://api.mj.run/v1/something";

    @Value("${midjourney.bearer-token}")
    private static String BEARER_TOKEN;

    private final RestTemplate restTemplate;

    public String postPromptImage(Midjourney midjourney) {
        try {
            return restTemplate.exchange(MIDJOURNEY_URL,
                    HttpMethod.POST,
                    new HttpEntity<>(midjourney, getHttpHeaders()),
                    String.class).getBody();
        } catch (HttpStatusCodeException exception) {
            throw new ServiceException("Error with midjourney request.", ServiceErrorCode.INVALID_CLIENT_REQUEST);
        } catch (ResourceAccessException exception) {
            throw new IllegalStateException("Midjourney service connection timed out.", exception);
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(BEARER_TOKEN);
        return headers;
    }
}
