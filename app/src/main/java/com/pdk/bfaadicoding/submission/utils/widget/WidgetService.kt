package com.pdk.bfaadicoding.submission.utils.widget

import android.content.Intent
import android.widget.RemoteViewsService

class WidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return WidgetDataProvider(this.applicationContext)
    }

}
