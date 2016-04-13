package com.hardsoftstudio.rxflux.dispatcher;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.List;

/**
 * Created by marcel on 10/09/15.
 *
 * Activities implementing this interface will be part of the RxFlux flow. Implement the methods in
 * order to get the proper callbacks and un/register stores accordingly to Flux flow.
 */
public interface RxViewDispatch {

  /**
   * All the stores will call this event after they process an action and the store change it.
   * The view can react and request the needed data
   */
  void onRxStoreChanged(@NonNull RxStoreChange change);

  /**
   * Called when an error occur in some point of the flux flow.
   *
   * @param error {@link RxError} containing the information for that specific error
   */
  void onRxError(@NonNull RxError error);

  /**
   * When an activity has been registered RxFlux will notify the activity so it can register
   * custom views or fragments.
   */
  void onRxViewRegistered();

  /**
   * When the activity goes to Stop {@link com.hardsoftstudio.rxflux.RxFlux#onActivityStopped(Activity)}
   * RxFlux will unregister it and the call this method so the activity can unregister custom views or fragments
   */
  void onRxViewUnRegistered();

  /**
   * The stores needs to be register in order to get Actions, this method will be called once the activity
   * get create. Return the store list with the RxStores that need to be registered for this specific view.
   * No need to check if one or many RxStore on the list were registered. RxFlux will ignore those.
   *
   * For example, a base method could create a list with the common stores and each activity will add stores
   * in case they are needed.
   *
   * @return list of {@link RxStore} to be registered, can be null.
   */
  @Nullable List<RxStore> getRxStoreListToRegister();

  /**
   * Return a list of RxStore that contains the RxStore you want to specifically unregister when the
   * activity implementing this interface gets destroyed.
   * Notice that if the Application is destroyed RxFlux will automatically unregister any store to avoid leaks.
   *
   * @return list of {@link RxStore} to be unregister, can be null
   */
  @Nullable List<RxStore> getRxStoreListToUnRegister();
}
