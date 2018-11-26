package com.shaker.shaker.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Feature entity.
 */
@Entity
public class Feature {

  @SerializedName("type")
  @Expose
  private String type;

  @Embedded
  @SerializedName("properties")
  @Expose
  private Properties properties;

  @Embedded
  @SerializedName("geometry")
  @Expose
  private Geometry geometry;

  @NonNull
  @PrimaryKey
  @Expose
  private String id;

  /**
   * Gets feature type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets feature type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets feature properties.
   *
   * @return the properties
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Sets feature properties.
   *
   * @param properties the properties
   */
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  /**
   * Gets feature geometry.
   *
   * @return the geometry
   */
  public Geometry getGeometry() {
    return geometry;
  }

  /**
   * Sets feature geometry.
   *
   * @param geometry the geometry
   */
  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  /**
   * Gets feature id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets feature id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }
}