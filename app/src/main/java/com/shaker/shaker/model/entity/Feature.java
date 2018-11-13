package com.shaker.shaker.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}