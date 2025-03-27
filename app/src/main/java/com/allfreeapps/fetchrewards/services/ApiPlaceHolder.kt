package com.allfreeapps.fetchrewards.services

import com.allfreeapps.fetchrewards.model.Reward
import retrofit2.Response
import retrofit2.http.GET


interface ApiPlaceHolder {

    @GET("hiring.json")
    suspend fun getRewards(): Response<List<Reward>>?
}