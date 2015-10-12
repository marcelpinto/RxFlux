package com.hardsoftstudio.rxflux;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.dispatcher.RxBus;
import com.hardsoftstudio.rxflux.dispatcher.RxStoreDispatch;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;

/**
 * Created by marcel on 09/09/15.
 */
public class RxFlux implements Application.ActivityLifecycleCallbacks {

  private static RxFlux instance;
  private final RxBus rxBus;
  private final Dispatcher dispatcher;
  private final SubscriptionManager subscriptionManager;
  private int activityCounter;

  private RxFlux() {
    this.rxBus = RxBus.getInstance();
    this.dispatcher = Dispatcher.getInstance(rxBus);
    this.subscriptionManager = SubscriptionManager.getInstance();
    activityCounter = 0;
  }

  public static RxFlux init() {
    if (instance != null) throw new IllegalStateException("Init was already called");
    return instance = new RxFlux();
  }

  public static void shutdown() {
    if (instance == null) return;
    instance.subscriptionManager.clear();
    instance.dispatcher.unregisterAll();
  }

  public RxBus getRxBus() {
    return rxBus;
  }

  public Dispatcher getDispatcher() {
    return dispatcher;
  }

  public SubscriptionManager getSubscriptionManager() {
    return subscriptionManager;
  }

  @Override
  public void onActivityCreated(Activity activity, Bundle bundle) {
    activityCounter++;
    if (activity instanceof RxStoreDispatch) {
      ((RxStoreDispatch) activity).onRegister();
    }
  }

  @Override
  public void onActivityStarted(Activity activity) {

  }

  @Override
  public void onActivityResumed(Activity activity) {
    if (activity instanceof RxStoreDispatch) {
      dispatcher.registerRxStore((RxStoreDispatch) activity);
    }
  }

  @Override
  public void onActivityPaused(Activity activity) {
    if (activity instanceof RxStoreDispatch) {
      dispatcher.unregisterRxStore((RxStoreDispatch) activity);
    }
  }

  @Override
  public void onActivityStopped(Activity activity) {

  }

  @Override
  public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

  }

  @Override
  public void onActivityDestroyed(Activity activity) {
    activityCounter--;

    if (activityCounter == 0) {
      RxFlux.shutdown();
    }
  }
}
