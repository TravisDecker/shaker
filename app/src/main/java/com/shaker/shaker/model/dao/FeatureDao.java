package com.shaker.shaker.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.shaker.shaker.model.entity.Feature;
import java.util.List;

/**
 * The interface Feature dao.
 */
@Dao
public interface FeatureDao {

  /**
   * Insert list.
   *
   * @param features the features
   * @return the list
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Feature> features);

  /**
   * Select list.
   *
   * @return the list
   */
  @Query("SELECT * FROM Feature")
  List<Feature> select();

  /**
   * Select 30 list.
   *
   * @param time the time
   * @return the list
   */
  @Query("SELECT * FROM Feature WHERE time >= (:time - 2592000000)")
  List<Feature> select30(long time);

  /**
   * Select 24 list.
   *
   * @param time the time
   * @return the list
   */
  @Query("SELECT * FROM Feature WHERE time >= (:time - 86400000) ")
  List<Feature> select24(long time);

  /**
   * Select 12 list.
   *
   * @param time the time
   * @return the list
   */
  @Query("SELECT * FROM Feature WHERE time >= (:time - 21600000) ")
  List<Feature> select12(long time);

  /**
   * Nuke int.
   *
   * @return the int
   */
  @Query("DELETE FROM Feature")
  int nuke();

}