package com.shaker.shaker.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserPins {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "pin_id")
  private long id;


}
