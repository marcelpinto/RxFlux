package com.hardsoftstudio.rxflux.sample;

import android.app.Application;
import android.content.Context;
import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.sample.actions.GitHubActionCreator;
import com.hardsoftstudio.rxflux.sample.stores.RepositoriesStore;
import com.hardsoftstudio.rxflux.sample.stores.UsersStore;

/**
 * Created by marcel on 10/09/15.
 */
public class SampleApp extends Application {

  private RxFlux rxFlux;

  /**
   * Please, note that it would be much better to use a singleton patter or DI instead of keeping
   * the variable reference here.
   */
  private GitHubActionCreator gitHubActionCreator;
  private RepositoriesStore repositoriesStore;
  private UsersStore usersStore;

  @Override
  public void onCreate() {
    super.onCreate();

    rxFlux = RxFlux.init();
    gitHubActionCreator = new GitHubActionCreator(this, rxFlux.getDispatcher(),
        rxFlux.getSubscriptionManager());
    repositoriesStore = new RepositoriesStore(rxFlux.getDispatcher());
    usersStore = new UsersStore(rxFlux.getDispatcher());
    registerActivityLifecycleCallbacks(rxFlux);
  }

  public static SampleApp get(Context context) {
    return ((SampleApp) context.getApplicationContext());
  }

  public GitHubActionCreator getGitHubActionCreator() {
    return gitHubActionCreator;
  }

  public RepositoriesStore getRepositoriesStore() {
    return repositoriesStore;
  }

  public UsersStore getUsersStore() {
    return usersStore;
  }
}
