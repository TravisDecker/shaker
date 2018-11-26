//Class to be implemented at a later time.
//package com.shaker.shaker.model.dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//import com.shaker.shaker.model.entity.Pin;
//import java.util.List;
//
///**
// * The interface Pin dao.
// */
//@Dao
//public interface PinDao {
//
//  /**
//   * Insert long.
//   *
//   * @param pin the pin
//   * @return the long
//   */
//  @Insert(onConflict = OnConflictStrategy.FAIL)
//  long insert(Pin pin);
//
//  /**
//   * Select list.
//   *
//   * @return the list
//   */
//  @Query("SELECT * FROM pin ORDER BY name")
//  List<Pin> select();
//
//  /**
//   * Delete int.
//   *
//   * @param pin the pin
//   * @return the int
//   */
//  @Delete
//  int delete(Pin pin);
//
//  /**
//   * Nuke int.
//   *
//   * @return the int
//   */
//  @Query("DELETE FROM Pin")
//  int nuke();
//
//}
