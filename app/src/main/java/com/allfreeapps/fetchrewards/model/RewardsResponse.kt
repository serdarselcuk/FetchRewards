package com.allfreeapps.fetchrewards.model

import com.google.gson.annotations.SerializedName

data class Reward(
    @SerializedName("name")
    val name:String?,
    @SerializedName("id")
    val id:Int?,
    @SerializedName("listId")
    val listId:Int?
)
