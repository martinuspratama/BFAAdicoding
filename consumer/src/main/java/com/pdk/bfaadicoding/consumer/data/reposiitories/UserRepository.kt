package com.pdk.bfaadicoding.consumer.data.reposiitories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.pdk.bfaadicoding.consumer.data.datasource.UserDataSource
import com.pdk.bfaadicoding.consumer.data.models.User
import kotlinx.coroutines.Dispatchers


/**
 * Created by Budi Ardianata on 30/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class UserRepository(private val source: UserDataSource) {
    fun getUserList(): LiveData<List<User>> = liveData(Dispatchers.IO) {
        emit(source.fetchUsers())
    }
}