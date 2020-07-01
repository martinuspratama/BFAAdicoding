package com.pdk.bfaadicoding.consumer.data

import android.net.Uri
import android.provider.BaseColumns


/**
 * Created by Budi Ardianata on 30/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
object DatabaseContract {
    private const val AUTHORITY = "com.pdk.bfaadicoding.submission"
    private const val SCHEME = "content"

    class UserColumns : BaseColumns {
        companion object {
            private const val TABLE_NAME = "database_github"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR = "avatar_url"
            const val NAME = "name"
            const val LOCATION = "location"
            const val TYPE = "type"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

    }
}