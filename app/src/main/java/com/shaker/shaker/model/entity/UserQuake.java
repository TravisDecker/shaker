package com.shaker.shaker.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;


@Entity
public class UserQuake {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "quake_id")
  private long quakeId;

  @NonNull
  @ColumnInfo(name = "quake_time")
  private Date time;

  @NonNull
  @ColumnInfo(name = "quake_lat")
  private  long latitude;

  @NonNull
  @ColumnInfo(name = "quake_long")
  private  long longitude;

  @NonNull
  @ColumnInfo(name = "quake_depth")
  private double depth;

  @NonNull
  @ColumnInfo(name = "quake_mag")
  private  double mag;

  @NonNull
  @ColumnInfo(name = "quake_magType")
  private String magType;

  @NonNull
  @ColumnInfo(name = "quake_nst")
  private int nst;

  @NonNull
  @ColumnInfo(name = "quake_gap")
  private   int gap;

  @NonNull
  @ColumnInfo(name = "quake_dmin")
  private long dmin;

  @NonNull
  @ColumnInfo(name = "quake_rms")
  private  double rms;

  @NonNull
  @ColumnInfo(name = "quake_net")
  private String net;

  @NonNull
  @ColumnInfo(name = "quake_netId")
  private   int id; // ex. ci37407450

  @NonNull
  @ColumnInfo(name = "quake_updated")
  private  Date updated;

  @NonNull
  @ColumnInfo(name = "quake_place")
  private  String place;

  @NonNull
  @ColumnInfo(name = "quake_type")
  private  String type;

  @NonNull
  @ColumnInfo(name = "quake_horizontalError")
  private  double horizontalError;

  @NonNull
  @ColumnInfo(name = "quake_depthError")
  private  double depthError;

  @NonNull
  @ColumnInfo(name = "quake_magError")
  private long magError;

  @NonNull
  @ColumnInfo(name = "quake_magNst")
  private int magNst;

  @NonNull
  @ColumnInfo(name = "quake_status")
  private  String status;

  @NonNull
  @ColumnInfo(name = "quake_locationSource")
  private  String locationSource;

  @NonNull
  @ColumnInfo(name = "quake_magSource")
  private  String magSource;



}
