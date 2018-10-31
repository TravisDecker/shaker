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

  @ColumnInfo(index = true)
  private double mag;

  private String place;

  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @NonNull
  @ColumnInfo(name = "time")
  private Date time;

  private String ids;

  private double latitude;

  private double longitude;

  private double depth;

  private Date updated;

  private String url;

  private String detail;

  private String felt;

  @ColumnInfo
  private String alert;

  private int nst;

  private String type;

  @ColumnInfo(name = "depth_error")
  private double depthError;

  @ColumnInfo(name = "mag_error")
  private long magError;

  @ColumnInfo(name = "mag_nst")
  private int magNst;

  public long getQuakeId() {
    return quakeId;
  }

  public void setQuakeId(long quakeId) {
    this.quakeId = quakeId;
  }

  public String getAlert() {
    return alert;
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

  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getFelt() {
    return felt;
  }

  public void setFelt(String felt) {
    this.felt = felt;
  }

  public void setAlert(String alert) {
    this.alert = alert;
  }

  public int getNst() {
    return nst;
  }

  public void setNst(int nst) {
    this.nst = nst;
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
}