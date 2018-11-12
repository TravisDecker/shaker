package com.shaker.shaker.service;

import com.shaker.shaker.model.entity.Shake;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ShakerService {

//  @GET("query?format=geojson")
//  Call<Shake> get(@Query("starttime")String startTime, @Query("endtime") String endTime);

  @GET("query?format=geojson")
  Call<Shake> get();

}
