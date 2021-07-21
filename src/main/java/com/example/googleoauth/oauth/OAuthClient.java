package com.example.googleoauth.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("client")
public class OAuthClient {
    private String id;
    private String secret;
    private String redirectUri;

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

}
