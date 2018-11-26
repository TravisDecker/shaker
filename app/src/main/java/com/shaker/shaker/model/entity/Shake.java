package com.shaker.shaker.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * The Shake entity.
 */
public class Shake {

  @SerializedName("type")
  @Expose
  private String type;

  @SerializedName("metadata")
  @Expose
  private Metadata metadata;

  @SerializedName("features")
  @Expose
  private List<Feature> features = null;

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets metadata.
   *
   * @return the metadata
   */
  public Metadata getMetadata() {
    return metadata;
  }

  /**
   * Sets metadata.
   *
   * @param metadata the metadata
   */
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  /**
   * Gets list of features.
   *
   * @return the features
   */
  public List<Feature> getFeatures() {
    return features;
  }

  /**
   * Sets list of features.
   *
   * @param features the features
   */
  public void setFeatures(List<Feature> features) {
    this.features = features;
  }
}
