package com.hardsoftstudio.rxflux.dispatcher;

import com.hardsoftstudio.rxflux.store.RxStoreChange;

/**
 * Created by marcel on 10/09/15.
 */
public interface RxStoreDispatch {

  void onRxStoreChanged(RxStoreChange change);
}
