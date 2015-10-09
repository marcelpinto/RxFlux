package com.hardsoftstudio.rxflux.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitUser {

  @SerializedName("login") @Expose public String login;
  @SerializedName("id") @Expose public int id;
  @SerializedName("avatar_url") @Expose public String avatarUrl;
  @SerializedName("gravatar_id") @Expose public String gravatarId;
  @SerializedName("url") @Expose public String url;
  @SerializedName("html_url") @Expose public String htmlUrl;
  @SerializedName("followers_url") @Expose public String followersUrl;
  @SerializedName("following_url") @Expose public String followingUrl;
  @SerializedName("gists_url") @Expose public String gistsUrl;
  @SerializedName("starred_url") @Expose public String starredUrl;
  @SerializedName("subscriptions_url") @Expose public String subscriptionsUrl;
  @SerializedName("organizations_url") @Expose public String organizationsUrl;
  @SerializedName("repos_url") @Expose public String reposUrl;
  @SerializedName("events_url") @Expose public String eventsUrl;
  @SerializedName("received_events_url") @Expose public String receivedEventsUrl;
  @SerializedName("type") @Expose public String type;
  @SerializedName("site_admin") @Expose public boolean siteAdmin;
  @SerializedName("name") @Expose public String name;
  @SerializedName("company") @Expose public Object company;
  @SerializedName("blog") @Expose public String blog;
  @SerializedName("location") @Expose public String location;
  @SerializedName("email") @Expose public String email;
  @SerializedName("public_repos") @Expose public int publicRepos;
  @SerializedName("public_gists") @Expose public int publicGists;
  @SerializedName("followers") @Expose public int followers;
  @SerializedName("following") @Expose public int following;
  @SerializedName("created_at") @Expose public String createdAt;
  @SerializedName("updated_at") @Expose public String updatedAt;

  public String getLogin() {
    return login;
  }

  public int getId() {
    return id;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getGravatarId() {
    return gravatarId;
  }

  public String getUrl() {
    return url;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public String getFollowersUrl() {
    return followersUrl;
  }

  public String getFollowingUrl() {
    return followingUrl;
  }

  public String getGistsUrl() {
    return gistsUrl;
  }

  public String getStarredUrl() {
    return starredUrl;
  }

  public String getSubscriptionsUrl() {
    return subscriptionsUrl;
  }

  public String getOrganizationsUrl() {
    return organizationsUrl;
  }

  public String getReposUrl() {
    return reposUrl;
  }

  public String getEventsUrl() {
    return eventsUrl;
  }

  public String getReceivedEventsUrl() {
    return receivedEventsUrl;
  }

  public String getType() {
    return type;
  }

  public boolean isSiteAdmin() {
    return siteAdmin;
  }

  public String getName() {
    return name;
  }

  public Object getCompany() {
    return company;
  }

  public String getBlog() {
    return blog;
  }

  public String getLocation() {
    return location;
  }

  public String getEmail() {
    return email;
  }

  public int getPublicRepos() {
    return publicRepos;
  }

  public int getPublicGists() {
    return publicGists;
  }

  public int getFollowers() {
    return followers;
  }

  public int getFollowing() {
    return following;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("GitUser{");
    sb.append("login='").append(login).append('\'');
    sb.append(", id=").append(id);
    sb.append(", avatarUrl='").append(avatarUrl).append('\'');
    sb.append(", gravatarId='").append(gravatarId).append('\'');
    sb.append(", url='").append(url).append('\'');
    sb.append(", htmlUrl='").append(htmlUrl).append('\'');
    sb.append(", followersUrl='").append(followersUrl).append('\'');
    sb.append(", followingUrl='").append(followingUrl).append('\'');
    sb.append(", gistsUrl='").append(gistsUrl).append('\'');
    sb.append(", starredUrl='").append(starredUrl).append('\'');
    sb.append(", subscriptionsUrl='").append(subscriptionsUrl).append('\'');
    sb.append(", organizationsUrl='").append(organizationsUrl).append('\'');
    sb.append(", reposUrl='").append(reposUrl).append('\'');
    sb.append(", eventsUrl='").append(eventsUrl).append('\'');
    sb.append(", receivedEventsUrl='").append(receivedEventsUrl).append('\'');
    sb.append(", type='").append(type).append('\'');
    sb.append(", siteAdmin=").append(siteAdmin);
    sb.append(", name='").append(name).append('\'');
    sb.append(", company=").append(company);
    sb.append(", blog='").append(blog).append('\'');
    sb.append(", location='").append(location).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append(", publicRepos=").append(publicRepos);
    sb.append(", publicGists=").append(publicGists);
    sb.append(", followers=").append(followers);
    sb.append(", following=").append(following);
    sb.append(", createdAt='").append(createdAt).append('\'');
    sb.append(", updatedAt='").append(updatedAt).append('\'');
    sb.append('}');
    return sb.toString();
  }
}