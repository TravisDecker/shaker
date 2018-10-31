package com.shaker.shaker.model.database;

import com.shaker.shaker.model.entity.Quake;
import java.util.Date;

public class PreData {

  Quake quake;
  String place = "10km NW of The Geysers, CA";
  private double latitude = -122.8424988;
  private double longitude = 38.8388329;


  public Quake setUp() {
    Quake quake = new Quake();
    quake.setLatitude(latitude);
    quake.setLongitude(longitude);
    quake.setPlace(place);
    quake.setTime(new Date());
    return quake;
  }
}
