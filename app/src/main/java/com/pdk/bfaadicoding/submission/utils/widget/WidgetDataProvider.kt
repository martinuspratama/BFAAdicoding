package com.pdk.bfaadicoding.submission.utils.widget

import android.R.id
import android.R.layout
import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.pdk.bfaadicoding.submission.data.local.UserDao
import com.pdk.bfaadicoding.submission.data.local.UserDatabase
import com.pdk.bfaadicoding.submission.data.models.User


/**
 * Created by Budi Ardianata on 01/07/2020.
 * Project: BFFAdicoding
 * Email: budiardianata@windowslive.com
 */
class WidgetDataProvider(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {
    private lateinit var users: List<User>
    private lateinit var userDao: UserDao
    override fun onCreate() {
        userDao = UserDatabase.getDatabase(context).userDao()
    }

    override fun onDataSetChanged() {
        users = userDao.getWidgetList()
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = users.size

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, layout.simple_list_item_1)
        views.setTextViewText(id.text1, users[position].login)
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long {
        return users[position].id.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }


}