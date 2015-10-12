package com.hardsoftstudio.rxflux.dispatcher;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

/**
 * Created by marcel on 10/09/15.
 * All the activities must implement this interface in order to get notified by RxFlux when
 * events happen.
 */
public interface RxViewDispatch {

  /**
   * All the stores will call this event so the view can react and request the needed data
   * @param change
   */
  void onRxStoreChanged(RxStoreChange change);

  void onRxError(RxError error);

  /**
   * When an activity has been registered RxFlux will notify the activity so it can register
   * custom views or fragments.
   */
  void onRxViewRegistered();

  /**
   * When the activity goes to Pause RxFlux will unregister it and the call this method so the
   * activity can unregister custom views or fragments
   */
  void onRxViewUnRegistered();

  /**
   * RxFlux method to let the view create the stores that need for this activity, this method is
   * called every time the activity is created. Normally you will instantiate the store with the
   * singleton instance.
   */
  void onRxStoresRegister();
}
