package com.hardsoftstudio.rxflux.sample;

import android.app.Application;
import android.content.Context;
import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.sample.actions.GitHubActionCreator;

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

  @Override
  public void onCreate() {
    super.onCreate();

    rxFlux = RxFlux.init();
    gitHubActionCreator = new GitHubActionCreator(this, rxFlux.getDispatcher(),
        rxFlux.getSubscriptionManager());

    registerActivityLifecycleCallbacks(rxFlux);
  }

  public static SampleApp get(Context context) {
    return ((SampleApp) context.getApplicationContext());
  }

  public RxFlux getRxFlux() {
    return rxFlux;
  }

  public GitHubActionCreator getGitHubActionCreator() {
    return gitHubActionCreator;
  }

}
