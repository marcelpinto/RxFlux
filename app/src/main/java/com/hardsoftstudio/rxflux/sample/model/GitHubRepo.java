package com.hardsoftstudio.rxflux.sample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcel on 09/10/15.
 */
public class GitHubRepo {

  private int id;
  private String name;
  @SerializedName("full_name") private String fullName;
  private GitUser owner;
  private String description;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public GitUser getOwner() {
    return owner;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
