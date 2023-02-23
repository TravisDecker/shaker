package com.shaker.shaker.model.entity;

import androidx.room.Ignore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * The  Geometry entity.
 */
public class Geometry {

  @Ignore
  @SerializedName("coordinates")
  @Expose
  private ArrayList<Double> coordinates;

  private Double latitude;
  private Double longitude;
  private Double depth;

  /**
   * Gets the coordinates of the feature.
   *
   * @return the coordinates
   */
  public ArrayList<Double> getCoordinates() {
    return coordinates;
  }

  /**
   * Sets the coordinates of the feature.
   *
   * @param coordinates the coordinates
   */
  public void setCoordinates(ArrayList<Double> coordinates) {
    this.coordinates = coordinates;
  }

  /**
   * Gets latitude.
   *
   * @return the latitude
   */
  public Double getLatitude() {
    return latitude;
  }

  /**
   * Sets latitude.
   *
   * @param latitude the latitude
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets longitude.
   *
   * @return the longitude
   */
  public Double getLongitude() {
    return longitude;
  }

  /**
   * Sets longitude.
   *
   * @param longitude the longitude
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets depth.
   *
   * @return the depth
   */
  public Double getDepth() {
    return depth;
  }

  /**
   * Sets depth.
   *
   * @param depth the depth
   */
  public void setDepth(Double depth) {
    this.depth = depth;
  }
}