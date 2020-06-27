package com.pdk.bfaadicoding.submission.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
@BindingAdapter("avatar")
fun avatar(imageView: ImageView, avatar: Int) =
    imageView.apply {
        Glide.with(this)
            .load(avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }