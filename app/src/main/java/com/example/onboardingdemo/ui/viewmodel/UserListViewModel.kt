package com.example.onboardingdemo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val retrofit: ApiInterface) : ViewModel(){

    var apiResponse = MutableLiveData<Response<UserListResponse>>()

    fun showListOfUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                retrofit.getAllUsers().apply {
                    enqueue(object : Callback<UserListResponse> {
                        override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                            apiResponse.postValue(response)
                        }

                        override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                            println("failed $t.message")
                        }

                    })
                }
            }
        }

    }
}