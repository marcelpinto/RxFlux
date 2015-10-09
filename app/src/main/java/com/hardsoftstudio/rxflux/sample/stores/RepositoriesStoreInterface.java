package com.hardsoftstudio.rxflux.sample.stores;

import com.hardsoftstudio.rxflux.sample.model.GitHubRepo;
import java.util.ArrayList;

/**
 * Created by marcel on 11/09/15.
 */
public interface RepositoriesStoreInterface {

  ArrayList<GitHubRepo> getRepositories();

}
