package com.hardsoftstudio.rxflux.sample.core;

import com.hardsoftstudio.rxflux.sample.model.GitHubRepo;
import com.hardsoftstudio.rxflux.sample.model.GitUser;
import java.util.ArrayList;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by marcel on 09/10/15.
 */
public interface GitHubApi {

  String SERVICE_ENDPOINT = "https://api.github.com";

  @GET("/repositories")
  Observable<ArrayList<GitHubRepo>> getRepositories();

  @GET("/users/{id}")
  Observable<GitUser> getUser(@Path("id") String userId);
}