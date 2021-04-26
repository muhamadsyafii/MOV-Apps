/*
 * Created by Muhamad Syafii
 * Tuesday, 20/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.checkout

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.syafii.movapps.databinding.ActivityCheckoutBinding
import com.syafii.movapps.model.Checkout
import com.syafii.movapps.model.Film
import com.syafii.movapps.model.arrayFromData
import com.syafii.movapps.util.constant.DATA_SEAT
import com.syafii.movapps.util.constant.MOVIE_DATA
import com.syafii.movapps.util.currency
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity

class CheckoutActivity : AppCompatActivity() {
    private val TAG = CheckoutActivity::class.java.simpleName
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var sharedPreference: SharedPreference
    private var total = 0
    private var dataList = ArrayList<Checkout>()
    private lateinit var adapter: CheckoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = SharedPreference(this)

        //this is get data from previous activity and SharedPreference
        dataList = arrayFromData(intent.getStringExtra(DATA_SEAT))
        val data: Film = Gson().fromJson(intent.getStringExtra(MOVIE_DATA), Film::class.java)
        val user = sharedPreference.getCurrentUser()


        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }
        dataList.add(Checkout("Total Harus bayar", total.toString()))

        if (user != null) {
            currency(user.saldo.toDouble(), binding.tvSaldo)
        }

        adapter = CheckoutAdapter(dataList)
        binding.rvItemSeat.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItemSeat.adapter = adapter

        //this is button back to previous activity
        binding.mToolbarBack.setOnClickListener {
            finish()
        }

        binding.btnPayNow.setOnClickListener {
            openActivity(CheckoutSuccessActivity::class.java)
            sharedPreference.saveString(DATA_SEAT, Gson().toJson(dataList))
            sharedPreference.saveString(MOVIE_DATA, Gson().toJson(data))
            finish()
        }

        binding.btnBatalkan.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda yakin ingin membatalkan ?")
            builder.setPositiveButton("OK") { dialog, which ->
                finish()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}