package com.shaker.shaker.model.database;

import com.shaker.shaker.model.entity.Quake;
import java.util.Date;

public class PreData {


  private String alert = "yellow";
  private double latitude = 37.383237;
  private double longitude = -118.417027;
  private String title = "M 2.0 - 1km ESE of Dixon Lane-Meadow Creek, CA";
  private int depth = 33;
  private String felt = "300";



  public Quake setUp() {
    Quake quake = new Quake();
    quake.setLatitude(latitude);
    quake.setLongitude(longitude);
    quake.setTime(new Date());
    quake.setTitle(title);
    quake.setAlert(alert);
    quake.setDepth(depth);
    quake.setFelt(felt);
    return quake;
  }
}