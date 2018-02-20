/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.smb.ui.shows;

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v17.leanback.widget.ImageCardView
import android.support.v17.leanback.widget.Presenter
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.smb.R
import com.smb.data.models.Video
import kotlin.properties.Delegates

/**
 * A ShowsCardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains an ImageCardView.
 */
class ShowsCardPresenter : Presenter() {
    private var mDefaultCardImage: Drawable? = null
    private var sSelectedBackgroundColor: Int by Delegates.notNull()
    private var sDefaultBackgroundColor: Int by Delegates.notNull()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        context = parent.context
        sDefaultBackgroundColor = ContextCompat.getColor(context, R.color.default_background)
        sSelectedBackgroundColor =
                ContextCompat.getColor(context, R.color.selected_background)
        mDefaultCardImage = ContextCompat.getDrawable(context, R.drawable.movie)

        val cardView = object : ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(cardView, false)
        return Presenter.ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val video = item as Video
        val cardView = viewHolder.view as ImageCardView

        Log.d(TAG, "onBindViewHolder")
        if (video.cover != null) {
            cardView.titleText = video.title
            initChaptersCount(cardView, video)
            cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
            Glide.with(viewHolder.view.context)
                    .load(video.cover)
                    .centerCrop()
                    .error(mDefaultCardImage)
                    .into(cardView.mainImageView)
        }
    }

    private fun initChaptersCount(cardView: ImageCardView, video: Video) {
        cardView.contentText = context.resources.getQuantityString(R.plurals.chapters_plurals, video.chapters.size, video.chapters.size)
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        Log.d(TAG, "onUnbindViewHolder")
        val cardView = viewHolder.view as ImageCardView
        // Remove references to images so that the garbage collector can free up memory
        cardView.badgeImage = null
        cardView.mainImage = null
    }

    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) sSelectedBackgroundColor else sDefaultBackgroundColor
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color)
        view.setInfoAreaBackgroundColor(color)
    }

    companion object {
        private val TAG = "ShowsCardPresenter"

        private val CARD_WIDTH = 313
        private val CARD_HEIGHT = 176
    }
}
