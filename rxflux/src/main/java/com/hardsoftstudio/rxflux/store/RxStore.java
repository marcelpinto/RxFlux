package com.hardsoftstudio.rxflux.store;

import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.dispatcher.RxActionDispatch;

/**
 * This class must be extended by each store of the app in order to recieve the actions dispatched
 * by the {@link RxActionCreator}
 */
public abstract class RxStore implements RxActionDispatch {

  private final Dispatcher dispatcher;

  public RxStore(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void register() {
    dispatcher.subscribeRxStore(this);
  }

  public void unregister() {
    dispatcher.unsubscribeRxStore(this);
  }

  protected void postChange(RxStoreChange change) {
    dispatcher.postRxStoreChange(change);
  }
}
