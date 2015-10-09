package com.hardsoftstudio.rxflux.dispatcher;

import com.hardsoftstudio.rxflux.action.RxAction;

/**
 * Created by marcel on 10/09/15.
 */
public interface RxActionDispatch {

  void onRxAction(RxAction action);

}
