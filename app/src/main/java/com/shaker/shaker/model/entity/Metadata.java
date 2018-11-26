package com.shaker.shaker.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Metadata.
 */
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

  /**
   * Gets generated.
   *
   * @return the generated
   */
  public Long getGenerated() {
    return generated;
  }

  /**
   * Sets generated.
   *
   * @param generated the generated
   */
  public void setGenerated(Long generated) {
    this.generated = generated;
  }

  /**
   * Gets url.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets url.
   *
   * @param url the url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public Long getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(Long status) {
    this.status = status;
  }

  /**
   * Gets api.
   *
   * @return the api
   */
  public String getApi() {
    return api;
  }

  /**
   * Sets api.
   *
   * @param api the api
   */
  public void setApi(String api) {
    this.api = api;
  }

  /**
   * Gets count.
   *
   * @return the count
   */
  public Long getCount() {
    return count;
  }

  /**
   * Sets count.
   *
   * @param count the count
   */
  public void setCount(Long count) {
    this.count = count;
  }
}
