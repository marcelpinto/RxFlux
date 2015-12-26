package com.hardsoftstudio.rxflux.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hardsoftstudio.rxflux.sample.model.GitHubRepo;
import java.util.ArrayList;

/**
 * Created by marcel on 09/10/15.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

  private ArrayList<GitHubRepo> repos;
  private OnRepoClicked callback;

  public RepoAdapter() {
    super();
    repos = new ArrayList<GitHubRepo>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.card_repository, viewGroup, false));
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
    GitHubRepo repo = repos.get(i);
    viewHolder.setUp(repo);
    viewHolder.itemView.setOnClickListener(view -> {
      if (callback != null) callback.onClicked(repo);
    });
  }

  @Override public int getItemCount() {
    return repos.size();
  }

  public void setRepos(ArrayList<GitHubRepo> repos) {
    this.repos = repos;
    notifyDataSetChanged();
  }

  public void setCallback(OnRepoClicked callback) {
    this.callback = callback;
  }

  public interface OnRepoClicked {
    void onClicked(GitHubRepo repo);
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.description) public TextView descView;
    @Bind(R.id.id) public TextView idView;
    @Bind(R.id.name) TextView nameView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void setUp(GitHubRepo repo) {
      nameView.setText(repo.getName());
      descView.setText(repo.getDescription());
      idView.setText("Github id: " + repo.getId());
    }
  }
}
