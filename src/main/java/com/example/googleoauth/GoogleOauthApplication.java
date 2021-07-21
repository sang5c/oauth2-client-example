package com.example.googleoauth;

import com.example.googleoauth.oauth.OAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OAuthClient.class)
public class GoogleOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoogleOauthApplication.class, args);
    }

}
