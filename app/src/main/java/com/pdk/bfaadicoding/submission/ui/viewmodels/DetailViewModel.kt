package com.pdk.bfaadicoding.submission.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pdk.bfaadicoding.submission.data.local.UserDao
import com.pdk.bfaadicoding.submission.data.local.UserDatabase
import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.data.repositories.UserDetailRepository
import com.pdk.bfaadicoding.submission.utils.Resource
import kotlinx.coroutines.launch

/**
 * Created by Budi Ardianata on 28/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao: UserDao = UserDatabase.getDatabase(application).userDao()

    private var userDetailRepository: UserDetailRepository

    init {
        userDetailRepository = UserDetailRepository(userDao)
    }

    fun data(username: String): LiveData<Resource<User>> =
        userDetailRepository.getDetailUser(username)

    fun addFavorite(user: User) = viewModelScope.launch {
        userDetailRepository.insert(user)
    }

    fun removeFavorite(user: User) = viewModelScope.launch {
        userDetailRepository.delete(user)
    }

    val isFavorite: LiveData<Boolean> = userDetailRepository.isFavorite
}