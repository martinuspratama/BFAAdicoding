package com.pdk.bfaadicoding.submission.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pdk.bfaadicoding.submission.R


/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
@BindingAdapter("avatar")
fun avatar(imageView: ImageView, avatar: String) {
    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.apply {
        setColorSchemeColors(R.color.colorPrimary)
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }

    Glide.with(imageView)
        .asBitmap()
        .load(avatar)
        .placeholder(circularProgressDrawable)
        .apply(
            RequestOptions
                .circleCropTransform()
                .override(100, 100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .into(imageView)
}
