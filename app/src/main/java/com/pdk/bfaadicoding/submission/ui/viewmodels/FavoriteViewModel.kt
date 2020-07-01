package com.pdk.bfaadicoding.submission.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pdk.bfaadicoding.submission.data.local.UserDatabase
import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.data.repositories.UsersRepository


/**
 * Created by Budi Ardianata on 29/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class FavoriteViewModel (application: Application) : AndroidViewModel(application) {
    val dataFavorite: LiveData<List<User>>

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        dataFavorite = UsersRepository.getFavorite(userDao)
    }
}