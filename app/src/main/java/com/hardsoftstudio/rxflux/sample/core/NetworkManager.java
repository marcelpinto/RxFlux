package com.hardsoftstudio.rxflux.sample.core;

import retrofit.RestAdapter;

/**
 * Created by marcel on 09/10/15.
 */
public class NetworkManager {

  private static GitHubApi instance;

  private static void build() {
    final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
        GitHubApi.SERVICE_ENDPOINT).build();
    instance = restAdapter.create(GitHubApi.class);
  }

  public static synchronized GitHubApi getApi() {
    if (instance == null) {
      build();
    }
    return instance;
  }
}
