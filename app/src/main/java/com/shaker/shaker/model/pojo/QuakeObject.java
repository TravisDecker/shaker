package com.shaker.shaker.model.pojo;

import java.util.Date;

public class QuakeObject {

  private Date time;
  private  long latitude;
  private  long longitude;
  private double depth;
  private  double mag;
  private String magType;
  private int nst;
  private   int gap;
  private long dmin;
  private  double rms;
  private String net;
  private   int id; // ex. ci37407450
  private  Date updated;
  private  String place;
  private  String type;
  private  double horizontalError;
  private  double depthError;
  private long magError;
  private int magNst;
  private  String status;
  private  String locationSource;
  private  String magSource;

}
