package com.pdk.bfaadicoding.submission.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
@Parcelize
data class User (
    val username : String,
    val name: String?,
    val avatar: Int?,
    val company: String?,
    val location: String?,
    val repository: String?,
    val follower: String?,
    val following: String?
) : Parcelable