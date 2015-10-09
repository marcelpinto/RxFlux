package com.hardsoftstudio.rxflux.sample.actions;

import android.content.Context;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.sample.core.NetworkManager;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.hardsoftstudio.rxflux.sample.actions.Keys.PUBLIC_REPOS;
import static com.hardsoftstudio.rxflux.sample.actions.Keys.USER;

/**
 * Created by marcel on 11/09/15.
 */
public class GitHubActionCreator extends RxActionCreator implements Actions {

  private final Context context;

  /**
   * If you want to give more things to the constructor like API or Preferences or any other
   * parameter you can buy make sure to call super(dispatcher, manager)
   */
  public GitHubActionCreator(Context context, Dispatcher dispatcher, SubscriptionManager manager) {
    super(dispatcher, manager);
    this.context = context;
  }

  @Override
  public void getPublicRepositories() {
    final RxAction action = newRxAction(GET_PUBLIC_REPOS);
    if (hasRxAction(action)) return;

    addRxAction(action, NetworkManager.getApi()
        .getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repos -> postRxAction(newRxAction(GET_PUBLIC_REPOS, PUBLIC_REPOS, repos)),
            throwable -> postError(action, throwable)));
  }

  @Override
  public void getUserDetails(String userId) {
    final RxAction action = newRxAction(GET_USER, Keys.ID, userId);
    if (hasRxAction(action)) return;

    addRxAction(action, NetworkManager.getApi()
        .getUser(userId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(user -> {
          action.getData().put(USER, user);
          postRxAction(action);
        }, throwable -> postError(action, throwable)));
  }
}
