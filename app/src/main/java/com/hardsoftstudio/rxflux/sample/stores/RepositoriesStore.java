package com.hardsoftstudio.rxflux.sample.stores;

import android.support.annotation.Nullable;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.sample.actions.Actions;
import com.hardsoftstudio.rxflux.sample.actions.Keys;
import com.hardsoftstudio.rxflux.sample.model.GitHubRepo;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marcel on 10/09/15.
 */
public class RepositoriesStore extends RxStore implements RepositoriesStoreInterface {

  public static final String ID = "RepositoriesStore";
  private static RepositoriesStore instance;
  private ArrayList<GitHubRepo> gitHubRepos;

  private RepositoriesStore(Dispatcher dispatcher) {
    super(dispatcher);
  }

  public static synchronized RepositoriesStore get(Dispatcher dispatcher) {
    if (instance == null) instance = new RepositoriesStore(dispatcher);
    return instance;
  }

  @Override public void onRxAction(RxAction action) {
    switch (action.getType()) {
      case Actions.GET_PUBLIC_REPOS:
        this.gitHubRepos = action.get(Keys.PUBLIC_REPOS);
        break;
      default: // IMPORTANT if we don't modify the store just ignore
        return;
    }
    postChange(new RxStoreChange(ID, action));
  }

  @Override public ArrayList<GitHubRepo> getRepositories() {
    return gitHubRepos == null ? new ArrayList<GitHubRepo>() : gitHubRepos;
  }

  @Nullable
  @Override
  public List<String> getActionListToUnsubscribe() {
    return Arrays.asList(Actions.GET_PUBLIC_REPOS);
  }
}
