package com.shaker.shaker.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

  @SerializedName("generated")
  @Expose
  private Long generated;

  @SerializedName("url")
  @Expose
  private String url;

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("status")
  @Expose
  private Long status;

  @SerializedName("api")
  @Expose
  private String api;

  @SerializedName("count")
  @Expose
  private Long count;

  public Long getGenerated() {
    return generated;
  }

  public void setGenerated(Long generated) {
    this.generated = generated;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getApi() {
    return api;
  }

  public void setApi(String api) {
    this.api = api;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
