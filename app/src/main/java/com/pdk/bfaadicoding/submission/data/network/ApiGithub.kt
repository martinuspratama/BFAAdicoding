package com.pdk.bfaadicoding.submission.data.network

import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.data.network.responese.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Budi Ardianata on 27/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
interface ApiGithub {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") q: String?): SearchResponse

    @GET("users/{username}")
    suspend fun userDetail(@Path("username") username: String?): User

    @GET("users/{username}/followers")
    suspend fun userFollower(@Path("username") username: String?): List<User>

    @GET("users/{username}/following")
    suspend fun userFollowing(@Path("username") username: String?): List<User>
}