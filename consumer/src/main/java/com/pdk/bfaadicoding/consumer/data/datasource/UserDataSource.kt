package com.pdk.bfaadicoding.consumer.data.datasource

import android.content.ContentResolver
import com.pdk.bfaadicoding.consumer.data.DatabaseContract
import com.pdk.bfaadicoding.consumer.data.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.pdk.bfaadicoding.consumer.data.models.User


/**
 * Created by Budi Ardianata on 30/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class UserDataSource(private val contentResolver: ContentResolver) {

    fun fetchUsers(): List<User> {
        val result: MutableList<User> = mutableListOf()

        val cursor = contentResolver.query(
            CONTENT_URI, null, null, null, null
        )
        cursor?.apply {
            while (moveToNext()) {
                result.add(
                    User(
                        getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.ID)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.LOGIN)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.AVATAR)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.NAME)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.LOCATION)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.TYPE))
                    )
                )
            }
            close()
        }
        return result.toList()
    }

}