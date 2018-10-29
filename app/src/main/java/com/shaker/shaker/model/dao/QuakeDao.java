package com.shaker.shaker.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.shaker.shaker.model.entity.Quake;
import java.util.List;

@Dao
public interface QuakeDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Quake quake);

  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<Quake> quakes);

  @Query("SELECT * FROM quake ORDER BY time")
  List<Quake> select();

  @Query("SELECT * FROM quake WHERE JULIANDAY('now') - JULIANDAY(`time`) <= :numDays ORDER BY time")
  List<Quake> selectRecent(int numDays);

  @Delete
  int delete(Quake quake);

  @Query("DELETE FROM Quake")
  int nuke();

}
