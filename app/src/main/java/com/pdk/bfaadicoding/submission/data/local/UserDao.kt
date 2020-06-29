package com.pdk.bfaadicoding.submission.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdk.bfaadicoding.submission.data.models.User


/**
 * Created by Budi Ardianata on 29/06/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY name ASC")
    fun getUserList(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}