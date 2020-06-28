package com.pdk.bfaadicoding.submission.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.data.repositories.UsersRepository
import com.pdk.bfaadicoding.submission.utils.Resource


/**
 * Created by Budi Ardianata on 28/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */

class HomeViewModel : ViewModel() {

    private val username: MutableLiveData<String> = MutableLiveData()

    val searchResult: LiveData<Resource<List<User>>> = Transformations
        .switchMap(username) {
            UsersRepository.searchUsers(it)
        }

    fun setUserId(query: String) {
        if (username.value == query) {
            return
        }
        username.value = query
    }

}
