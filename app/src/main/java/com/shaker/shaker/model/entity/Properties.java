package com.shaker.shaker.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Properties entity.
 */
public class Properties implements Parcelable {

  /**
   * The CREATOR for Parcelable.
   */
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

  @SerializedName("time")
  @Expose
  private Long time;

  @SerializedName("updated")
  @Expose
  private Long updated;

  @SerializedName("url")
  @Expose
  private String url;

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

  /**
   * Instantiates a new Properties.
   */
  public Properties() {
  }

  /**
   * Gets mag.
   *
   * @return the mag
   */
  public Double getMag() {
    return mag;
  }

  /**
   * Sets mag.
   *
   * @param mag the mag
   */
  public void setMag(Double mag) {
    this.mag = mag;
  }

  /**
   * Gets place.
   *
   * @return the place
   */
  public String getPlace() {
    return place;
  }

  /**
   * Sets place.
   *
   * @param place the place
   */
  public void setPlace(String place) {
    this.place = place;
  }

  /**
   * Gets time.
   *
   * @return the time
   */
  public Long getTime() {
    return time;
  }

  /**
   * Sets time.
   *
   * @param time the time
   */
  public void setTime(Long time) {
    this.time = time;
  }

  /**
   * Gets updated.
   *
   * @return the updated
   */
  public Long getUpdated() {
    return updated;
  }

  /**
   * Sets updated.
   *
   * @param updated the updated
   */
  public void setUpdated(Long updated) {
    this.updated = updated;
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
   * Gets felt.
   *
   * @return the felt
   */
  public Long getFelt() {
    return felt;
  }

  /**
   * Sets felt.
   *
   * @param felt the felt
   */
  public void setFelt(Long felt) {
    this.felt = felt;
  }

  /**
   * Gets alert.
   *
   * @return the alert
   */
  public String getAlert() {
    return alert;
  }

  /**
   * Sets alert.
   *
   * @param alert the alert
   */
  public void setAlert(String alert) {
    this.alert = alert;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets net.
   *
   * @return the net
   */
  public String getNet() {
    return net;
  }

  /**
   * Sets net.
   *
   * @param net the net
   */
  public void setNet(String net) {
    this.net = net;
  }

  /**
   * Gets ids.
   *
   * @return the ids
   */
  public String getIds() {
    return ids;
  }

  /**
   * Sets ids.
   *
   * @param ids the ids
   */
  public void setIds(String ids) {
    this.ids = ids;
  }

  /**
   * Gets nst.
   *
   * @return the nst
   */
  public Long getNst() {
    return nst;
  }

  /**
   * Sets nst.
   *
   * @param nst the nst
   */
  public void setNst(Long nst) {
    this.nst = nst;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  private Properties(Parcel in) {
    in.readDouble();
    in.readString();
    in.readLong();
    in.readLong();
    in.readString();
    in.readLong();
    in.readString();
    in.readString();
    in.readString();
    in.readString();
    in.readLong();
    in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(mag);
    dest.writeString(place);
    dest.writeLong(time);
    dest.writeLong(updated);
    dest.writeString(url);
    if (felt == null) {
      felt = 0L;
      dest.writeLong(felt);
    } else {
      dest.writeLong(felt);
    }
    if (alert == null) {
      alert = "N/A";
      dest.writeString(alert);
    } else {
      dest.writeString(alert);
    }
    dest.writeString(status);
    dest.writeString(net);
    dest.writeString(ids);
    if (nst == null) {
      nst = 0L;
      dest.writeLong(nst);
    } else {
      dest.writeLong(nst);
    }
    dest.writeString(title);
  }
}
