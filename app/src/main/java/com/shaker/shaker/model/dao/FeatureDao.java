package com.shaker.shaker.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.shaker.shaker.model.entity.Feature;
import java.util.List;

/**
 * The interface Feature dao.
 */
@Dao
public interface FeatureDao {

  /**
   * Insert features.
   *
   * @param features the features to be inserted
   * @return features
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Feature> features);

  /**
   * Select all features.
   *
   * @return features
   */
  @Query("SELECT * FROM Feature")
  List<Feature> select();

  /**
   * Select features from the last 30 days.
   *
   * @param time the time
   * @return the list
   */
  @Query("SELECT * FROM Feature WHERE time >= (:time - 2592000000)")
  List<Feature> select30(long time);

  /**
   * Select features form the last 24 hours.
   *
   * @param time the time
   * @return features
   */
  @Query("SELECT * FROM Feature WHERE time >= (:time - 86400000) ")
  List<Feature> select24(long time);

  /**
   * Select features form the last 12 hours.
   *
   * @param time the time
   * @return features
   */
  @Query("SELECT * FROM Feature WHERE time >= (:time - 21600000) ")
  List<Feature> select12(long time);

  /**
   * delete all from features
   *
   * @return the int
   */
  @Query("DELETE FROM Feature")
  int nuke();

}