package com.example.googleoauth.oauth;

import com.example.googleoauth.util.MultiValueMapConverter;
import com.example.googleoauth.util.Urls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

    private static final Logger log = LoggerFactory.getLogger(OAuthService.class);

    private final OAuthClient oAuthClient;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OAuthService(OAuthClient oAuthClient, RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.oAuthClient = oAuthClient;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public String googleCalendarList(String code) throws JsonProcessingException {
        log.debug("code : " + code);
        TokenRequest tokenRequest = new TokenRequest(
                code,
                oAuthClient.getId(),
                oAuthClient.getSecret(),
                oAuthClient.getRedirectUri()
        );
        MultiValueMap<String, String> multiValueMap = MultiValueMapConverter.convert(tokenRequest);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(Urls.OAUTH_TOKEN_URL, multiValueMap, String.class);
        log.info("access token : " + responseEntity.getBody());
        TokenResponse tokenResponse = objectMapper.readValue(responseEntity.getBody(), TokenResponse.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenResponse.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(Urls.OAUTH_API_URL, HttpMethod.GET, entity, String.class);
        log.info(response.getBody());
        return response.getBody();
    }

}
