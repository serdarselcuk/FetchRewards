package com.allfreeapps.fetchrewards.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allfreeapps.fetchrewards.model.Reward
import com.allfreeapps.fetchrewards.services.ApiPlaceHolder
import com.allfreeapps.fetchrewards.services.ServiceClient
import com.allfreeapps.fetchrewards.utils.FetchRewardsConstants.Companion.URL
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.IOException

class FetchRewardsViewModel: ViewModel() {
    private var _rewardsResponse = MutableLiveData<List<Reward>?>()
    val rewardsResponse: LiveData<List<Reward>?> = _rewardsResponse

    fun getRewards(){
        val retrofitClientService = ServiceClient().createRetrofit(URL).create(ApiPlaceHolder::class.java)
        viewModelScope.launch {
            val rewardsRequest = retrofitClientService.getRewards()
            try {
                val response = rewardsRequest?.awaitResponse()
                if (response?.isSuccessful == true)
                _rewardsResponse.postValue(
                    response?.body() ?: throw IOException("Response body is null")
                )
            }catch (e:Exception){
                Log.e("GET_REWARDS_SERVICE",e.message.toString() )
                _rewardsResponse.postValue(null)
            }
        }
    }
}