package com.pdk.bfaadicoding.submission.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.pdk.bfaadicoding.submission.data.local.UserDao
import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.data.network.RetrofitBuilder
import com.pdk.bfaadicoding.submission.utils.Resource
import kotlinx.coroutines.Dispatchers


/**
 * Created by Budi Ardianata on 30/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class UserDetailRepository(private val userDao: UserDao) {
    private val favorite: MutableLiveData<Boolean> = MutableLiveData()

    fun getDetailUser(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val user = userDao.getUserDetails(username)
        if (user != null) {
            favorite.postValue(true)
            emit(Resource.success(data = user))
        } else {
            favorite.postValue(false)
            try {
                emit(Resource.success(data = RetrofitBuilder.apiGithub.userDetail(username)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }

    suspend fun insert(user: User) {
        userDao.insertUser(user)
        favorite.value = true
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
        favorite.value = false
    }

    val isFavorite: LiveData<Boolean> = favorite
}