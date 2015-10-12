package com.hardsoftstudio.rxflux.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hardsoftstudio.rxflux.dispatcher.RxStoreDispatch;
import com.hardsoftstudio.rxflux.sample.actions.Actions;
import com.hardsoftstudio.rxflux.sample.actions.Keys;
import com.hardsoftstudio.rxflux.sample.model.GitHubRepo;
import com.hardsoftstudio.rxflux.sample.stores.RepositoriesStore;
import com.hardsoftstudio.rxflux.sample.stores.UsersStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import static com.hardsoftstudio.rxflux.sample.SampleApp.get;

public class MainActivity extends AppCompatActivity
    implements RxStoreDispatch, RepoAdapter.OnRepoClicked {

  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @Bind(R.id.loading_frame) TextView loadingFrame;
  private RepoAdapter adapter;
  private UsersStore usersStore;
  private RepositoriesStore repositoriesStore;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new RepoAdapter();
    adapter.setCallback(this);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_refresh) {
      refresh();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onResume() {
    super.onResume();
    refresh();
  }

  @Override
  public void onRxStoreChanged(RxStoreChange change) {
    setLoadingFrame(false);

    switch (change.getStoreId()) {
      case RepositoriesStore.ID:
        switch (change.getRxAction().getType()) {
          case Actions.GET_PUBLIC_REPOS:
            adapter.setRepos(repositoriesStore.getRepositories());
            break;
        }
        break;
      case UsersStore.ID:
        switch (change.getRxAction().getType()) {
          case Actions.GET_USER:
            showUserDialog((String) change.getRxAction().getData().get(Keys.ID));
        }
    }
  }

  @Override
  public void onRegister() {
    repositoriesStore = RepositoriesStore.get(SampleApp.get(this).getRxFlux().getDispatcher());
    repositoriesStore.register();
    usersStore = UsersStore.get(SampleApp.get(this).getRxFlux().getDispatcher());
    usersStore.register();
  }

  private void showUserDialog(String id) {
    UserFragment dialog = UserFragment.newInstance(id);
    dialog.show(getSupportFragmentManager(), UserFragment.class.getName());
  }

  private void setLoadingFrame(boolean show) {
    loadingFrame.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  private void refresh() {
    setLoadingFrame(true);
    get(this).getGitHubActionCreator().getPublicRepositories();
  }

  @Override
  public void onClicked(GitHubRepo repo) {
    String login = repo.getOwner().getLogin();
    if (usersStore.getUser(login) != null) {
      showUserDialog(login);
      return;
    }
    setLoadingFrame(true);
    SampleApp.get(this).getGitHubActionCreator().getUserDetails(login);
  }
}
