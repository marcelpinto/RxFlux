package com.hardsoftstudio.rxflux.sample.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.sample.core.GitHubApi;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.hardsoftstudio.rxflux.sample.actions.Keys.ID;
import static com.hardsoftstudio.rxflux.sample.actions.Keys.PUBLIC_REPOS;
import static com.hardsoftstudio.rxflux.sample.actions.Keys.USER;

/**
 * Action Creator responsible of creating the needed actions
 */
public class GitHubActionCreator extends RxActionCreator implements Actions {

  /**
   * If you want to give more things to the constructor like API or Preferences or any other
   * parameter you can buy make sure to call super(dispatcher, manager)
   */
  public GitHubActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
    super(dispatcher, manager);
  }

  @Override
  public void getPublicRepositories() {
    final RxAction action = newRxAction(GET_PUBLIC_REPOS);
    if (hasRxAction(action)) return;

    addRxAction(action, GitHubApi.Factory.getApi()
        .getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repos -> postRxAction(newRxAction(GET_PUBLIC_REPOS, PUBLIC_REPOS, repos)), throwable -> postError(action, throwable)));
  }

  @Override
  public void getUserDetails(String userId) {
    final RxAction action = newRxAction(GET_USER, Keys.ID, userId);
    if (hasRxAction(action)) return;

    addRxAction(action, GitHubApi.Factory.getApi()
        .getUser(userId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(user -> {
          action.getData().put(USER, user);
          postRxAction(action);
        }, throwable -> postError(action, throwable)));
  }

  @Override
  public boolean retry(RxAction action) {
    if (hasRxAction(action)) return true;

    switch (action.getType()) {
      case GET_USER:
        getUserDetails(action.get(ID));
        return true;
      case GET_PUBLIC_REPOS:
        getPublicRepositories();
        return true;
    }
    return false;
  }
}
