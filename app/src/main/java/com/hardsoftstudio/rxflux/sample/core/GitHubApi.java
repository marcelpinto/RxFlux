package com.hardsoftstudio.rxflux.sample.core;

import com.hardsoftstudio.rxflux.sample.model.GitHubRepo;
import com.hardsoftstudio.rxflux.sample.model.GitUser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by marcel on 09/10/15.
 */
public interface GitHubApi {

  String ENDPOINT = "https://api.github.com";

  @GET("/repositories") Observable<ArrayList<GitHubRepo>> getRepositories();

  @GET("/users/{id}") Observable<GitUser> getUser(@Path("id") String userId);

  class Factory {
    private static GitHubApi instance;

    private static void create() {

      OkHttpClient client = new OkHttpClient();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

      final Retrofit retrofit = new Retrofit.Builder().baseUrl(GitHubApi.ENDPOINT).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
      instance = retrofit.create(GitHubApi.class);
    }

    public static synchronized GitHubApi getApi() {
      if (instance == null) {
        create();
      }
      return instance;
    }
  }
}
