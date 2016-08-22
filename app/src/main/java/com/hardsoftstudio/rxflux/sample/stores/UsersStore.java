package com.hardsoftstudio.rxflux.sample.stores;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.sample.actions.Actions;
import com.hardsoftstudio.rxflux.sample.actions.Keys;
import com.hardsoftstudio.rxflux.sample.model.GitUser;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marcel on 09/10/15.
 */
public class UsersStore extends RxStore implements UsersStoreInterface {

  public static final String ID = "UsersStore";
  private static UsersStore instance;
  private ArrayMap<String, GitUser> users;

  private UsersStore(Dispatcher dispatcher) {
    super(dispatcher);
    users = new ArrayMap<>();
  }

  public static synchronized UsersStore get(Dispatcher dispatcher) {
    if (instance == null) instance = new UsersStore(dispatcher);
    return instance;
  }

  /**
   * This callback will get all the actions, each store must react on the types he want and do
   * some logic with the model, for example add it to the list to cache it, modify
   * fields etc.. all the logic for the models should go here and then call postChange so the
   * view request the new data
   */
  @Override public void onRxAction(RxAction action) {
    switch (action.getType()) {
      case Actions.GET_USER:
        GitUser user = action.get(Keys.USER);
        users.put(user.getLogin(), user);
        break;
      default: // IMPORTANT if we don't modify the store just ignore
        return;
    }
    postChange(new RxStoreChange(ID, action));
  }

  @Override public GitUser getUser(String id) {
    return users.get(id);
  }

  @Override public ArrayList<GitUser> getUsers() {
    // TODO Make this store contains a list of users so every time we fetch a user we added to
    // the list, so we don't need to request it again
    return new ArrayList<>();
  }

  @Nullable
  @Override
  public List<String> getActionListToUnsubscribe() {
    return Arrays.asList(Actions.GET_USER);
  }
}
