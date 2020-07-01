package com.pdk.bfaadicoding.consumer.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pdk.bfaadicoding.consumer.data.datasource.UserDataSource
import com.pdk.bfaadicoding.consumer.data.reposiitories.UserRepository


/**
 * Created by Budi Ardianata on 30/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val source = UserDataSource(application.contentResolver)
        repository = UserRepository(source)
    }

    var userLists = repository.getUserList()
}