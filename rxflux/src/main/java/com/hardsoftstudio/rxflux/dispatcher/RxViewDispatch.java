package com.hardsoftstudio.rxflux.dispatcher;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.List;

/**
 * Created by marcel on 10/09/15.
 * All the activities must implement this interface in order to get notified by RxFlux when
 * events happen.
 */
public interface RxViewDispatch {

  /**
   * All the stores will call this event so the view can react and request the needed data
   */
  void onRxStoreChanged(RxStoreChange change);

  /**
   * Called when an error occur in some point of the flux flow.
   *
   * @param error {@link RxError} contianing the information for that specific error
     */
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
   * This method is called so the stores can register. Return the store list with the RxStores that need to be registered to the RxFlux flow.
   * No need to check if one or many RxStore on the list were registered. RxFlux will ignore those.
   *
   * @return list of {@link RxStore}
   */
  List<RxStore> getRxStoreListToRegister();

  /**
   * Return a list of RxStore that contains the RxStore you want to specifically unregister. Notice that if the Application is destroyed RxFlux will
   * automatically unregister any store to avoid leaks.
   *
   * @return list of {@link RxStore}
   */
  List<RxStore> getRxStoreListToUnRegister();
}
