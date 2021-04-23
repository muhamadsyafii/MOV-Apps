/*
 * Created by Muhamad Syafii
 * Wednesday, 24/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.syafii.movapps.R
import com.syafii.movapps.model.OnBoarding

class OnBoardingAdapter(
    private val context: Context) : PagerAdapter() {



    private lateinit var onBoardingList: List<OnBoarding>

    fun setBoardingList(onBoardingList: List<OnBoarding>) {
        this.onBoardingList = onBoardingList
        notifyDataSetChanged()
    }

    override fun getCount(): Int = onBoardingList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` is View && view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)

        var view = View(context)
        if (inflater != null) {
            val data: OnBoarding = onBoardingList[position]
            view = inflater.inflate(R.layout.item_on_boarding, null)
            val imBoarding: ImageView = view.findViewById(R.id.im_boarding)
            val tvTitle : TextView = view.findViewById(R.id.tv_title_boarding)
            val tvDesc: TextView = view.findViewById(R.id.tv_desc_boarding)

            imBoarding.setImageResource(data.image)
            tvTitle.text = data.title
            tvDesc.text = data.desc
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

}