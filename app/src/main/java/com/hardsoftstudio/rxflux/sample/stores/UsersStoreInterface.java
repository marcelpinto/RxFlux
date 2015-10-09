package com.hardsoftstudio.rxflux.sample.stores;

import com.hardsoftstudio.rxflux.sample.model.GitUser;
import java.util.ArrayList;

/**
 * Created by marcel on 11/09/15.
 */
public interface UsersStoreInterface {

  GitUser getUser(String id);

  ArrayList<GitUser> getUsers();

}
