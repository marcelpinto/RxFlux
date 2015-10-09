package com.hardsoftstudio.rxflux.dispatcher;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by marcel on 10/09/15.
 */
public class RxBus {

  private static RxBus instance;
  // If multiple threads are going to emit events to this
  // then it must be made thread-safe like this instead
  private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

  private RxBus() {
  }

  public synchronized static RxBus getInstance() {
    if (instance == null) {
      instance = new RxBus();
    }
    return instance;
  }

  public void send(Object o) {
    bus.onNext(o);
  }

  public Observable<Object> get() {
    return bus;
  }

  public boolean hasObservers() {
    return bus.hasObservers();
  }
}
