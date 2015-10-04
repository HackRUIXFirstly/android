package com.github.hackruixfirstly.firstly.models;

/**
 * Created by trevor on 10/3/15.
 */
public class AuthRequest {

    public AuthRequest(String token) {
        access_token = token;
    }

    public String access_token;

}
