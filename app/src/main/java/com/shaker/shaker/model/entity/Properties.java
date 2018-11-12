package com.shaker.shaker.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Properties implements Parcelable {

//  @ColumnInfo(index = true)

  public static final Parcelable.Creator<Properties> CREATOR
      = new Parcelable.Creator<Properties>() {
    public Properties createFromParcel(Parcel in) {
      return new Properties(in);
    }

    @Override
    public Properties[] newArray(int size) {
      return new Properties[size];
    }
  };
  @SerializedName("mag")
  @Expose
  private Double mag;

  @SerializedName("place")
  @Expose
  private String place;

  @NonNull
  @SerializedName("time")
  @Expose
  private Long time;

  @SerializedName("updated")
  @Expose
  private Long updated;

  @SerializedName("url")
  @Expose
  private String url;

  @SerializedName("detail")
  @Expose
  private String detail;

  @SerializedName("felt")
  @Expose
  private Long felt;

  @SerializedName("alert")
  @Expose
  private String alert;

  @SerializedName("status")
  @Expose
  private String status;

  @SerializedName("net")
  @Expose
  private String net;

  @SerializedName("ids")
  @Expose
  private String ids;

  @SerializedName("nst")
  @Expose
  private Long nst;

  @SerializedName("title")
  @Expose
  private String title;

  public Properties() {

  }

  private Properties(Parcel in) {
    in.readDouble();
    in.readString();
    in.readLong();
    in.readLong();
    in.readString();
    in.readString();
    in.readLong();
    in.readString();
    in.readString();
    in.readString();
    in.readString();
    in.readLong();
    in.readString();
  }

  public Double getMag() {
    return mag;
  }

  public void setMag(Double mag) {
    this.mag = mag;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Long getUpdated() {
    return updated;
  }

  public void setUpdated(Long updated) {
    this.updated = updated;
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

  public Long getFelt() {
    return felt;
  }

  public void setFelt(Long felt) {
    this.felt = felt;
  }

  public String getAlert() {
    return alert;
  }

  public void setAlert(String alert) {
    this.alert = alert;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getNet() {
    return net;
  }

  public void setNet(String net) {
    this.net = net;
  }

  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }

  public Long getNst() {
    return nst;
  }

  public void setNst(Long nst) {
    this.nst = nst;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(mag);
    dest.writeString(place);
    dest.writeLong(time);
    dest.writeLong(updated);
    dest.writeString(url);
    dest.writeString(detail);
    dest.writeLong(felt);
    dest.writeString(alert);
    dest.writeString(status);
    dest.writeString(net);
    dest.writeString(ids);
    dest.writeLong(nst);
    dest.writeString(title);
  }

}
