package com.allfreeapps.fetchrewards.services

import com.allfreeapps.fetchrewards.model.Reward
import retrofit2.Call
import retrofit2.http.GET


interface ApiPlaceHolder {

    @GET("hiring.json")
    fun getRewards():Call<List<Reward>>?
}