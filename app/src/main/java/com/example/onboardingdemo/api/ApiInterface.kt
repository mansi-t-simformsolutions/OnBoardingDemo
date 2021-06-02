package com.example.onboardingdemo

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/users?")
    fun getAllUsers(): Call<UserListResponse>
}