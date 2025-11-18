package com.example.desafio.dto.response.authentication;

public class ResponseDtoTokens{
    private String accessToken;
    private String refreshToken;

    public ResponseDtoTokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
