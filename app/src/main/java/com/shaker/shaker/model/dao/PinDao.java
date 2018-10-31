package com.shaker.shaker.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.shaker.shaker.model.entity.Pin;
import java.util.List;

@Dao
public interface PinDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Pin pin);

  @Query("SELECT * FROM pin ORDER BY name")
  List<Pin> select();

  @Delete
  int delete(Pin pin);

  @Query("DELETE FROM Pin")
  int nuke();

}
