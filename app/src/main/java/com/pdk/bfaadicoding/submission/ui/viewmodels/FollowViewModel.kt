package com.pdk.bfaadicoding.submission.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.data.repositories.UsersRepository
import com.pdk.bfaadicoding.submission.utils.Resource
import com.pdk.bfaadicoding.submission.utils.TypeView


/**
 * Created by Budi Ardianata on 28/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class FollowViewModel : ViewModel() {
    private val username: MutableLiveData<String> = MutableLiveData()

    private lateinit var type: TypeView

    val dataFollow: LiveData<Resource<List<User>>> = Transformations
        .switchMap(username) {
            when (type) {
                TypeView.FOLLOWER -> {
                    UsersRepository.getFollowers(it)
                }
                TypeView.FOLLOWING -> {
                    UsersRepository.getFollowing(it)
                }
            }
        }

    fun setParam(user: String, typeView: TypeView) {
        if (username.value == user) {
            return
        }
        username.value = user
        type = typeView
    }
}