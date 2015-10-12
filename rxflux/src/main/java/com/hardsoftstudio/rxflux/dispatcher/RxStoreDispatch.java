package com.hardsoftstudio.rxflux.dispatcher;

import com.hardsoftstudio.rxflux.store.RxStoreChange;

/**
 * Created by marcel on 10/09/15.
 * All the activities must implement this interface in order to get notified by RxFlux when
 * events happen.
 */
public interface RxStoreDispatch {

  /**
   * All the stores will call this event so the view can react and request the needed data
   * @param change
   */
  void onRxStoreChanged(RxStoreChange change);

  /**
   * RxFlux method to let the view create the stores that need for this activity, this method is
   * called every time the activity is created. Normally you will instantiate the store with the
   * singleton instance.
   */
  void onRegister();
}
