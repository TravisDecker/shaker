package com.shaker.shaker.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;


@Entity
public class Quake {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "quake_id")
  private long quakeId;

  @NonNull
  @ColumnInfo(name = "time")
  private Date time;


  private double latitude;


  private double longitude;


  private double depth;

  @ColumnInfo(index = true)
  private double mag;


  @ColumnInfo(name = "mag_type")
  private String magType;


  private int nst;


  private int gap;


  private long dmin;


  private double rms;


  private String net;

  @ColumnInfo(name = "net_id")
  private String netId;


  private Date updated;
  @ColumnInfo
  private String place;
  private String type;
  @ColumnInfo(name = "horizontal_error")
  private double horizontalError;
  @ColumnInfo(name = "depth_error")
  private double depthError;
  @ColumnInfo(name = "mag_error")
  private long magError;
  @ColumnInfo(name = "mag_nst")
  private int magNst;
  private String status;
  private String locationSource;
  @ColumnInfo(name = "mag_source")
  private String magSource;

  public long getQuakeId() {
    return quakeId;
  }

  public void setQuakeId(long quakeId) {
    this.quakeId = quakeId;
  }

  @NonNull
  public Date getTime() {
    return time;
  }

  public void setTime(@NonNull Date time) {
    this.time = time;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getDepth() {
    return depth;
  }

  public void setDepth(double depth) {
    this.depth = depth;
  }

  public double getMag() {
    return mag;
  }

  public void setMag(double mag) {
    this.mag = mag;
  }

  public String getMagType() {
    return magType;
  }

  public void setMagType(String magType) {
    this.magType = magType;
  }

  public int getNst() {
    return nst;
  }

  public void setNst(int nst) {
    this.nst = nst;
  }

  public int getGap() {
    return gap;
  }

  public void setGap(int gap) {
    this.gap = gap;
  }

  public long getDmin() {
    return dmin;
  }

  public void setDmin(long dmin) {
    this.dmin = dmin;
  }

  public double getRms() {
    return rms;
  }

  public void setRms(double rms) {
    this.rms = rms;
  }

  public String getNet() {
    return net;
  }

  public void setNet(String net) {
    this.net = net;
  }

  public String getNetId() {
    return netId;
  }

  public void setNetId(String netId) {
    this.netId = netId;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getHorizontalError() {
    return horizontalError;
  }

  public void setHorizontalError(double horizontalError) {
    this.horizontalError = horizontalError;
  }

  public double getDepthError() {
    return depthError;
  }

  public void setDepthError(double depthError) {
    this.depthError = depthError;
  }

  public long getMagError() {
    return magError;
  }

  public void setMagError(long magError) {
    this.magError = magError;
  }

  public int getMagNst() {
    return magNst;
  }

  public void setMagNst(int magNst) {
    this.magNst = magNst;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLocationSource() {
    return locationSource;
  }

  public void setLocationSource(String locationSource) {
    this.locationSource = locationSource;
  }

  public String getMagSource() {
    return magSource;
  }

  public void setMagSource(String magSource) {
    this.magSource = magSource;
  }


}