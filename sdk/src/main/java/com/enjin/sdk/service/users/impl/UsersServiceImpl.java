package com.enjin.sdk.service.users.impl;

import java.io.IOException;
import java.util.List;

import com.enjin.sdk.graphql.GraphQLResponse;
import com.enjin.sdk.http.HttpCallback;
import com.enjin.sdk.http.HttpResponse;
import com.enjin.sdk.model.service.auth.AuthTokens;
import com.enjin.sdk.model.service.auth.AuthUser;
import com.enjin.sdk.model.service.users.OAuthUser;
import com.enjin.sdk.http.SchemaProvider;
import com.enjin.sdk.model.service.users.CreateUser;
import com.enjin.sdk.model.service.users.GetUser;
import com.enjin.sdk.model.service.users.GetUsers;
import com.enjin.sdk.model.service.users.User;
import com.enjin.sdk.service.GraphQLServiceBase;
import com.enjin.sdk.service.users.UsersService;

import retrofit2.Retrofit;

public class UsersServiceImpl extends GraphQLServiceBase implements UsersService {

    private final UsersRetrofitService service;
    private final SchemaProvider schemaProvider;

    public UsersServiceImpl(Retrofit retrofit, SchemaProvider schemaProvider) {
        this.service = retrofit.create(UsersRetrofitService.class);
        this.schemaProvider = schemaProvider;
    }

    @Override
    public void getUsersAsync(GetUsers query,
                              HttpCallback<GraphQLResponse<List<User>>> callback) {
        enqueueGraphQLCall(this.service.getUsers(schemaProvider.get(), query), callback);
    }

    @Override
    public void getUserAsync(GetUser query, HttpCallback<GraphQLResponse<User>> callback) {
        enqueueGraphQLCall(this.service.getUser(schemaProvider.get(), query), callback);
    }

    @Override
    public void createUserAsync(CreateUser query,
                                HttpCallback<GraphQLResponse<User>> callback) {
        enqueueGraphQLCall(this.service.createUser(schemaProvider.get(), query), callback);
    }

    @Override
    public void oAuthUserAsync(OAuthUser query, HttpCallback<GraphQLResponse<User>> callback) {
        enqueueGraphQLCall(this.service.oAuthUser(schemaProvider.get(), query), callback);
    }

    @Override
    public void authUserAsync(AuthUser query,
                              HttpCallback<GraphQLResponse<AuthTokens>> callback) {
        enqueueGraphQLCall(this.service.authUser(schemaProvider.get(), query), callback);
    }

    @Override
    public HttpResponse<GraphQLResponse<List<User>>> getUsersSync(GetUsers query) throws IOException {
        return executeGraphQLCall(this.service.getUsers(schemaProvider.get(), query));
    }

    @Override
    public HttpResponse<GraphQLResponse<User>> getUserSync(GetUser query) throws IOException {
        return executeGraphQLCall(this.service.getUser(schemaProvider.get(), query));
    }

    @Override
    public HttpResponse<GraphQLResponse<User>> createUserSync(CreateUser query) throws IOException {
        return executeGraphQLCall(this.service.createUser(schemaProvider.get(), query));
    }

    @Override
    public HttpResponse<GraphQLResponse<User>> oAuthUserSync(OAuthUser query) throws IOException {
        return executeGraphQLCall(this.service.oAuthUser(schemaProvider.get(), query));
    }

    @Override
    public HttpResponse<GraphQLResponse<AuthTokens>> authUserSync(AuthUser query) throws IOException {
        return executeGraphQLCall(this.service.authUser(schemaProvider.get(), query));
    }

}
