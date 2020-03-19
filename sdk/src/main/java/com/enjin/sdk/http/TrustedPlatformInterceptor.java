package com.enjin.sdk.http;

import java.io.IOException;

import com.enjin.java_commons.StringUtils;

import com.enjin.sdk.model.service.auth.AuthTokens;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TrustedPlatformInterceptor implements Interceptor {

    public static final String AUTHORIZATION = "Authorization";

    @Setter
    private String tokenType;
    @Setter
    private String token;
    @Getter
    @Setter
    private Integer appId;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        if (!StringUtils.isEmpty(tokenType) && !StringUtils.isEmpty(token)) {
            builder.header(AUTHORIZATION, String.format("%s %s", tokenType, token));
        }

        return chain.proceed(builder.build());
    }

    public void auth(AuthTokens data) {
        tokenType = "Bearer";
        token = data.getAccessToken();
    }

}