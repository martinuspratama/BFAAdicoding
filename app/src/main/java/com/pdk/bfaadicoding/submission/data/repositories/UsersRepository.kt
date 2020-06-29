package com.pdk.bfaadicoding.submission.data.repositories

import androidx.lifecycle.liveData
import com.pdk.bfaadicoding.submission.data.local.UserDao
import com.pdk.bfaadicoding.submission.data.network.RetrofitBuilder
import com.pdk.bfaadicoding.submission.utils.Resource
import kotlinx.coroutines.Dispatchers


/**
 * Created by Budi Ardianata on 28/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
object UsersRepository {

    fun searchUsers(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val userSearch = RetrofitBuilder.apiGithub.searchUsers(query)
            emit(Resource.success(data = userSearch.items))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getDetailUser(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = RetrofitBuilder.apiGithub.userDetail(username)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getFollowers(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = RetrofitBuilder.apiGithub.userFollower(username)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getFollowing(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = RetrofitBuilder.apiGithub.userFollowing(username)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getFavorite(userDao: UserDao) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userDao.getUserList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}