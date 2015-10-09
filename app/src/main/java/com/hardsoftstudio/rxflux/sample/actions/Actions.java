package com.hardsoftstudio.rxflux.sample.actions;

/**
 * Created by marcel on 11/09/15.
 */
public interface Actions {

  String GET_PUBLIC_REPOS = "get_public_repos";
  String GET_USER = "get_user";

  void getPublicRepositories();

  void getUserDetails(String userId);
}
