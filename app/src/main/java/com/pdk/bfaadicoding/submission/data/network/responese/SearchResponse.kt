package com.pdk.bfaadicoding.submission.data.network.responese

import android.os.Parcelable
import com.pdk.bfaadicoding.submission.data.models.User
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 28/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
@Parcelize
data class SearchResponse(
    val total_count : String,
    val incomplete_results: Boolean? = null,
    val items : List<User>
):Parcelable