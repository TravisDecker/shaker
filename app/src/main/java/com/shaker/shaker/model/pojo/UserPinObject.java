package com.shaker.shaker.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;

public class UserPinObject {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "pin_id")
  private long id;

  public String getPinName() {
    return pinName;
  }

  public void setPinName(String pinName) {
    this.pinName = pinName;
  }

  @ColumnInfo(name = "pin_name")
  private  String pinName;

  @NonNull
  @ColumnInfo(name = "pin_lat")
  private  long latitude;

  @NonNull
  @ColumnInfo(name = "pin_long")
  private  long longitude;


  @ColumnInfo(name = "pin_info")
  private String[] information;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getLatitude() {
    return latitude;
  }

  public void setLatitude(long latitude) {
    this.latitude = latitude;
  }

  public long getLongitude() {
    return longitude;
  }

  public void setLongitude(long longitude) {
    this.longitude = longitude;
  }

  public String[] getInformation() {
    return information;
  }

  public void setInformation(String[] information) {
    this.information = information;
  }
}
