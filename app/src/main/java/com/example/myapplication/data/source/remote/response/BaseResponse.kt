package com.example.myapplication.data.source.remote.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val data: T
)