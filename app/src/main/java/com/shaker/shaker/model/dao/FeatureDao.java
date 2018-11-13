package com.shaker.shaker.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.shaker.shaker.model.entity.Feature;
import java.util.List;

@Dao
public interface FeatureDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Feature feature);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Feature> features);

  @Query("SELECT * FROM Feature")
  List<Feature> select();

  @Query("SELECT * FROM Feature WHERE time = '2018-11-11' ")
  List<Feature> selectToday();


  @Query("SELECT * FROM Feature WHERE JULIANDAY('now') - JULIANDAY(`time`) <= :numDays ORDER BY time")
  List<Feature> selectRecent(int numDays);

  @Delete
  int delete(Feature feature);

  @Query("DELETE FROM Feature")
  int nuke();

}
//Todo update queries