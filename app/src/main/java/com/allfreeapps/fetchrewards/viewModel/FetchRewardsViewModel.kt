package com.allfreeapps.fetchrewards.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allfreeapps.fetchrewards.model.Reward
import com.allfreeapps.fetchrewards.services.ApiPlaceHolder
import com.allfreeapps.fetchrewards.services.ServiceClient
import com.allfreeapps.fetchrewards.utils.FetchRewardsConstants.Companion.BASE_URL
import kotlinx.coroutines.launch
import java.io.IOException

open class FetchRewardsViewModel : ViewModel() {

    private var _rewardsResponse = MutableLiveData<List<Reward>?>()
    open val rewardsResponse: LiveData<List<Reward>?> = _rewardsResponse

    private var _errorResponse = MutableLiveData<String>()
    open val errorResponse: LiveData<String> = _errorResponse


    fun getRewards() {
        val retrofitClientService =
            ServiceClient().createRetrofit(BASE_URL).create(ApiPlaceHolder::class.java)
        viewModelScope.launch {
            val rewardsRequest = retrofitClientService.getRewards()
            try {
                val response = rewardsRequest ?: throw IOException("Response body is null")
                if (response.isSuccessful)
                    _rewardsResponse.postValue(
                        response.body()
                    )
            } catch (e: Exception) {
                Log.e("GET_REWARDS_SERVICE", e.message.toString())
                _errorResponse.postValue(e.message)
            }
        }
    }
}