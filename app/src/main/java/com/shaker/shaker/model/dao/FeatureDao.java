package com.shaker.shaker.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.shaker.shaker.model.entity.Feature;
import java.util.List;

@Dao
public interface FeatureDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Feature> features);

  @Query("SELECT * FROM Feature")
  List<Feature> select();

  @Query("SELECT * FROM Feature WHERE time >= (:time - 2592000000)")
  List<Feature> select30(long time);

  @Query("SELECT * FROM Feature WHERE time >= (:time - 86400000) ")
  List<Feature> select24(long time);

  @Query("SELECT * FROM Feature WHERE time >= (:time - 21600000) ")
  List<Feature> select12(long time);

  @Query("DELETE FROM Feature")
  int nuke();

}