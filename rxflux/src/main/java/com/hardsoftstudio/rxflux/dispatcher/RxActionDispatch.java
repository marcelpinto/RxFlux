package com.hardsoftstudio.rxflux.dispatcher;

import com.hardsoftstudio.rxflux.action.RxAction;

/**
 * This interface must be implemented by the store
 */
public interface RxActionDispatch {

  void onRxAction(RxAction action);
}
