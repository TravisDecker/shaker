package com.shaker.shaker.model.entity;

import android.arch.persistence.room.Ignore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Geometry {

  @Ignore
  @SerializedName("coordinates")
  @Expose
  private ArrayList<Double> coordinates;

  private Double latitude;
  private Double longitude;
  private Double depth;

  public ArrayList<Double> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(ArrayList<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getDepth() {
    return depth;
  }

  public void setDepth(Double depth) {
    this.depth = depth;
  }
}