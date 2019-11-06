package com.shaker.shaker.service;

import com.shaker.shaker.model.entity.Shake;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The interface Shaker service.
 */
public interface ShakerService {

  /**
   * Get call.
   *
   * @return the call
   */
  @GET("query?format=geojson&limit=20000")
  Call<Shake> get();
}
