package com.hardsoftstudio.rxflux.util;

import com.hardsoftstudio.rxflux.action.RxAction;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Subscription;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by marcel on 12/10/15.
 */
public class SubscriptionManagerTest {

  private static final String TYPE = "type";
  private static final String KEY1 = "key1";
  private SubscriptionManager manager;

  @Before
  public void setUp() throws Exception {

    manager = SubscriptionManager.getInstance();
  }

  @After
  public void tearDown() throws Exception {
    manager.clear();
    manager = null;
  }

  @Test
  public void testManager() throws Exception {
    RxAction action = RxAction.type(TYPE).bundle(KEY1, 1).build();
    manager.add(action, Observable.just(0).subscribe());
    assertFalse(manager.contains(action));

    manager.add(action, Observable.just(0).delay(2, TimeUnit.SECONDS).subscribe());
    assertTrue(manager.contains(action));

    manager.remove(action);
    assertFalse(manager.contains(action));

    Subscription s1 = Observable.just(0).delay(2, TimeUnit.SECONDS).subscribe();
    manager.add(action, s1);
    assertTrue(manager.contains(action));
    Subscription s2 = Observable.just(1).delay(2, TimeUnit.SECONDS).subscribe();
    manager.add(action, s2);
    assertTrue(s1.isUnsubscribed());
  }

}