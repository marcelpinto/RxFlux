package com.hardsoftstudio.rxflux.store;

import com.hardsoftstudio.rxflux.action.RxAction;

/**
 * Created by marcel on 10/09/15.
 */
public class RxStoreChange {

  String storeId;
  RxAction rxAction;

  public RxStoreChange(String storeId, RxAction rxAction) {
    this.storeId = storeId;
    this.rxAction = rxAction;
  }

  public RxAction getRxAction() {
    return rxAction;
  }

  public String getStoreId() {
    return storeId;
  }
}
