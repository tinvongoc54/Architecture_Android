package com.example.myapplication.data.source.remote

import com.example.myapplication.data.model.User
import com.example.myapplication.data.source.remote.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ArchitectureApi {
    @GET("/search/users")
    fun searchUser(@Query("q") keyword: String, @Query("page") page: Int): Deferred<BaseResponse<List<User>>>
}