package com.example.myapplication.data.source

import com.example.myapplication.base.Result
import com.example.myapplication.data.model.User
import com.example.myapplication.data.source.local.SharedPrefs
import com.example.myapplication.data.source.remote.ArchitectureApi
import com.example.myapplication.data.source.remote.response.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface UserRepository {

    suspend fun searchUser(keyword: String, page: Int = 1): Result<BaseResponse<List<User>>>
}

class UserRepositoryImp(
    private val sharedPrefs: SharedPrefs,
    private val architectureApi: ArchitectureApi
) : UserRepository {

    override suspend fun searchUser(keyword: String, page: Int): Result<BaseResponse<List<User>>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(architectureApi.searchUser(keyword, page).await())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

}