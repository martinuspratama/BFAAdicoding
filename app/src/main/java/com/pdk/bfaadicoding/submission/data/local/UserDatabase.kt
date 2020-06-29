package com.pdk.bfaadicoding.submission.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdk.bfaadicoding.submission.data.models.User


/**
 * Created by Budi Ardianata on 29/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
public abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "database_user"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}