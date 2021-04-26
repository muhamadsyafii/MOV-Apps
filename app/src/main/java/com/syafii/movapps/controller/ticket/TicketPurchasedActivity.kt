/*
 * Created by Muhamad Syafii
 * Monday, 26/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.ticket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.syafii.movapps.R
import com.syafii.movapps.databinding.ActivityTicketPurchasedBinding
import com.syafii.movapps.model.Checkout
import com.syafii.movapps.model.Film
import com.syafii.movapps.model.arrayFromData
import com.syafii.movapps.util.constant.DATA_SEAT
import com.syafii.movapps.util.constant.MOVIE_DATA
import com.syafii.movapps.util.db.DialogUtil
import com.syafii.movapps.util.db.SharedPreference

class TicketPurchasedActivity : AppCompatActivity() {
    private val TAG = TicketPurchasedActivity::class.java.simpleName
    private lateinit var binding: ActivityTicketPurchasedBinding
    private lateinit var sharedPreference: SharedPreference
    private var dataList = ArrayList<Checkout>()
    private lateinit var adapter: TicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketPurchasedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = SharedPreference(this)
        adapter = TicketAdapter(this)

        dataList = arrayFromData(sharedPreference.getString(DATA_SEAT))
        val data = Gson().fromJson(sharedPreference.getString(MOVIE_DATA), Film::class.java)

        Glide.with(this).load(data.poster).error(R.drawable.ic_profile).into(binding.imPoster)
        binding.tvTitle.text = data.judul
        binding.tvGenre.text = data.genre
        binding.tvRating.text = data.rating

        binding.rvItemSeat.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItemSeat.isNestedScrollingEnabled = false

        //check condition if dataList empty or not
        if (dataList.size > 0) {
            adapter.setTicketAdapter(dataList)
        } else {
            dataList.add(Checkout("A1", "50000"))
            dataList.add(Checkout("A2", "50000"))
            adapter.setTicketAdapter(dataList)
        }
        binding.rvItemSeat.adapter = adapter

        binding.mToolbarBack.setOnClickListener {
            finish()
        }

        binding.imQr.setOnClickListener {
            val dialog = DialogUtil()
            dialog.showQrCode(this)
        }
    }
}