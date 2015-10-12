package com.hardsoftstudio.rxflux.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import com.hardsoftstudio.rxflux.action.RxAction;
import rx.Subscription;

/**
 * Created by marcel on 13/08/15.
 */
public final class SubscriptionManager {

  private static SubscriptionManager instance;
  private ArrayMap<String, Pair<Integer, Subscription>> mMap;

  private SubscriptionManager() {
    mMap = new ArrayMap<>();
  }

  public static synchronized SubscriptionManager getInstance() {
    if (instance == null) instance = new SubscriptionManager();
    return instance;
  }

  public void add(RxAction action, Subscription subscription) {
    Pair<Integer, Subscription> old = mMap.put(action.getType(), getPair(action, subscription));
    if (old != null && !old.second.isUnsubscribed()) old.second.unsubscribe();
  }

  public void remove(RxAction action) {
    Pair<Integer, Subscription> old = mMap.remove(action.getType());
    if (old != null && !old.second.isUnsubscribed()) old.second.unsubscribe();
  }

  /**
   * Checks if the action (with the same params) is already running a subscription
   *
   * @return true if the exact action is inside the map and running
   */
  public boolean contains(RxAction action) {
    Pair<Integer, Subscription> old = mMap.get(action.getType());
    return (old != null && old.first == action.hashCode() && !old.second.isUnsubscribed());
  }

  public synchronized void clear() {
    if (mMap.isEmpty()) return;

    for (Pair<Integer, Subscription> pair : mMap.values()) {
      if (!pair.second.isUnsubscribed()) pair.second.unsubscribe();
    }
  }

  private Pair<Integer, Subscription> getPair(RxAction action, Subscription subscription) {
    return new Pair<>(action.hashCode(), subscription);
  }
}
