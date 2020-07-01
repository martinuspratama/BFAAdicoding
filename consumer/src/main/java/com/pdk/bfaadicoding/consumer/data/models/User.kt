package com.pdk.bfaadicoding.consumer.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
@Parcelize
data class User(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val name: String?,
    val location: String?,
    val type: String?
) : Parcelable