package com.pdk.bfaadicoding.submission.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
@Entity(tableName = "user_table")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "type")
    val type: String?
) : Parcelable