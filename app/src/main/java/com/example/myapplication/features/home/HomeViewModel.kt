package com.example.myapplication.features.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.base.Result
import com.example.myapplication.data.model.User
import com.example.myapplication.data.source.UserRepository
import com.example.myapplication.utils.rx.SingleLiveData
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val usersLiveData by lazy { SingleLiveData<List<User>>() }
    val users by lazy { mutableListOf<User>() }

    fun searchUser(keyword: String, page: Int = 1) {
        viewModelScope.launch {
            showLoading()
            userRepository.searchUser(keyword, page).let { result ->
                showLoading(false)
                when (result) {
                    is Result.Success -> {
                        users.addAll(result.data.data)
                        usersLiveData.value = users
                    }
                    is Result.Error -> {
                        Log.e("Home View Model", result.exception.localizedMessage)
                    }
                }
            }
        }
    }
}