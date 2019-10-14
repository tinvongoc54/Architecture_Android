package com.example.myapplication.data.source.remote.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(@SerializedName("message") val message: String)