/*
 * Created by Muhamad Syafii
 * Thursday, 25/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.chooseseat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.syafii.movapps.R
import com.syafii.movapps.controller.checkout.CheckoutActivity
import com.syafii.movapps.databinding.ActivityChooseSeatBinding
import com.syafii.movapps.model.Checkout
import com.syafii.movapps.model.Film
import com.syafii.movapps.util.constant.*
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast
import kotlinx.android.synthetic.main.activity_choose_seat.*

class ChooseSeatActivity : AppCompatActivity(){
    private val TAG = ChooseSeatActivity::class.java.simpleName
    private lateinit var binding: ActivityChooseSeatBinding

    private var statusA1 = false
    private var statusA2 = false
    private var statusA3 = false
    private var statusA4 = false

    private var statusB1 = false
    private var statusB2 = false
    private var statusB3 = false
    private var statusB4 = false

    private var statusC1 = false
    private var statusC2 = false
    private var statusC3 = false
    private var statusC4 = false

    private var statusD1 = false
    private var statusD2 = false
    private var statusD3 = false
    private var statusD4 = false

    private var total = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseSeatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //this is get data from previous activity
        val data : Film = Gson().fromJson(intent.getStringExtra(MOVIE_DATA), Film::class.java)

        Log.e(TAG, Gson().toJson(data))

        //initialization from data to some item
        binding.tvNameMovie.text = data.judul!!

        //this is button back for previous page
        binding.mToolbarBack.setOnClickListener {
            finish()
        }

        binding.imA1.setOnClickListener {
            if (statusA1){
                binding.imA1.setImageResource(R.drawable.ic_empty_seat)
                statusA1 = false
                total--
                buyTicket(total)
            }else{
                binding.imA1.setImageResource(R.drawable.ic_selected_seat)
                statusA1 = true
                total++
                buyTicket(total)

                val value = Checkout(A1, "50000")
                dataList.add(value)
            }
        }

        binding.imA2.setOnClickListener {
            if (statusA2){
                binding.imA2.setImageResource(R.drawable.ic_empty_seat)
                statusA2 = false
                total--
                buyTicket(total)
            }else{
                binding.imA2.setImageResource(R.drawable.ic_selected_seat)
                statusA2 = true
                total++
                buyTicket(total)

                val value = Checkout(A2, "50000")
                dataList.add(value)
            }
        }

        binding.imA3.setOnClickListener {
            if (statusA3){
                binding.imA3.setImageResource(R.drawable.ic_empty_seat)
                statusA3 = false
                total--
                buyTicket(total)
            }else{
                binding.imA3.setImageResource(R.drawable.ic_selected_seat)
                statusA3 = true
                total++
                buyTicket(total)

                val value = Checkout(A3, "50000")
                dataList.add(value)
            }
        }

        binding.imA4.setOnClickListener {
            if (statusA4){
                binding.imA4.setImageResource(R.drawable.ic_empty_seat)
                statusA4 = false
                total--
                buyTicket(total)
            }else{
                binding.imA4.setImageResource(R.drawable.ic_selected_seat)
                statusA4 = true
                total++
                buyTicket(total)

                val value = Checkout(A4, "50000")
                dataList.add(value)
            }
        }

        binding.imB1.setOnClickListener {
            if (statusB1){
                binding.imB1.setImageResource(R.drawable.ic_empty_seat)
                statusB1 = false
                total--
                buyTicket(total)
            }else{
                binding.imB1.setImageResource(R.drawable.ic_selected_seat)
                statusB1 = true
                total++
                buyTicket(total)

                val value = Checkout(B1, "50000")
                dataList.add(value)
            }
        }

        binding.imB2.setOnClickListener {
            if (statusB2){
                binding.imB2.setImageResource(R.drawable.ic_empty_seat)
                statusB2 = false
                total--
                buyTicket(total)
            }else{
                binding.imB2.setImageResource(R.drawable.ic_selected_seat)
                statusB2 = true
                total++
                buyTicket(total)

                val value = Checkout(B2, "50000")
                dataList.add(value)
            }
        }

        binding.imB3.setOnClickListener {
            if (statusB3){
                binding.imB3.setImageResource(R.drawable.ic_empty_seat)
                statusB3 = false
                total--
                buyTicket(total)
            }else{
                binding.imB3.setImageResource(R.drawable.ic_selected_seat)
                statusB3 = true
                total++
                buyTicket(total)

                val value = Checkout(B3, "50000")
                dataList.add(value)
            }
        }

        binding.imB4.setOnClickListener {
            if (statusB4){
                binding.imB4.setImageResource(R.drawable.ic_empty_seat)
                statusB4 = false
                total--
                buyTicket(total)
            }else{
                binding.imB4.setImageResource(R.drawable.ic_selected_seat)
                statusB4 = true
                total++
                buyTicket(total)

                val value = Checkout(B4, "50000")
                dataList.add(value)
            }
        }

        binding.imC1.setOnClickListener {
            if (statusC1){
                binding.imC1.setImageResource(R.drawable.ic_empty_seat)
                statusC1 = false
                total--
                buyTicket(total)
            }else{
                binding.imC1.setImageResource(R.drawable.ic_selected_seat)
                statusC1 = true
                total++
                buyTicket(total)

                val value = Checkout(C1, "50000")
                dataList.add(value)
            }
        }

        binding.imC2.setOnClickListener {
            if (statusC2){
                binding.imC2.setImageResource(R.drawable.ic_empty_seat)
                statusC2 = false
                total--
                buyTicket(total)
            }else{
                binding.imC2.setImageResource(R.drawable.ic_selected_seat)
                statusC2 = true
                total++
                buyTicket(total)

                val value = Checkout(C2, "50000")
                dataList.add(value)
            }
        }

        binding.imC3.setOnClickListener {
            if (statusC3){
                binding.imC3.setImageResource(R.drawable.ic_empty_seat)
                statusC3 = false
                total--
                buyTicket(total)
            }else{
                binding.imC3.setImageResource(R.drawable.ic_selected_seat)
                statusC3 = true
                total++
                buyTicket(total)

                val value = Checkout(C3, "50000")
                dataList.add(value)
            }
        }

        binding.imC4.setOnClickListener {
            if (statusC4){
                binding.imC4.setImageResource(R.drawable.ic_empty_seat)
                statusC4 = false
                total--
                buyTicket(total)
            }else{
                binding.imC4.setImageResource(R.drawable.ic_selected_seat)
                statusC4 = true
                total++
                buyTicket(total)

                val value = Checkout(C4, "50000")
                dataList.add(value)
            }
        }

        binding.imD1.setOnClickListener {
            if (statusD1){
                binding.imD1.setImageResource(R.drawable.ic_empty_seat)
                statusD1 = false
                total--
                buyTicket(total)
            }else{
                binding.imD1.setImageResource(R.drawable.ic_selected_seat)
                statusD1 = true
                total++
                buyTicket(total)

                val value = Checkout(D1, "50000")
                dataList.add(value)
            }
        }

        binding.imD2.setOnClickListener {
            if (statusD2){
                binding.imD2.setImageResource(R.drawable.ic_empty_seat)
                statusD2 = false
                total--
                buyTicket(total)
            }else{
                binding.imD2.setImageResource(R.drawable.ic_selected_seat)
                statusD2 = true
                total++
                buyTicket(total)

                val value = Checkout(D2, "50000")
                dataList.add(value)
            }
        }

        binding.imD3.setOnClickListener {
            if (statusD3){
                binding.imD3.setImageResource(R.drawable.ic_empty_seat)
                statusD3 = false
                total--
                buyTicket(total)
            }else{
                binding.imD3.setImageResource(R.drawable.ic_selected_seat)
                statusD3 = true
                total++
                buyTicket(total)

                val value = Checkout(D3, "50000")
                dataList.add(value)
            }
        }

        binding.imD4.setOnClickListener {
            if (statusD4){
                binding.imD4.setImageResource(R.drawable.ic_empty_seat)
                statusD4 = false
                total--
                buyTicket(total)
            }else{
                binding.imD4.setImageResource(R.drawable.ic_selected_seat)
                statusD4 = true
                total++
                buyTicket(total)

                val value = Checkout(D4, "50000")
                dataList.add(value)
            }
        }

        binding.btnPayTicket.setOnClickListener {
            if (total !=0){
                val move = Intent(this, CheckoutActivity::class.java)
                move.putExtra(DATA_SEAT, Gson().toJson(dataList))
                move.putExtra(MOVIE_DATA,  Gson().toJson(data))
                startActivity(move)
            }else{
                showToast("Please Select seat first")
            }
        }
    }



    private fun buyTicket(total: Int) {
        if (total == 0){
            binding.btnPayTicket.text = resources.getString(R.string.txt_beli_tiket)
            binding.btnPayTicket.visibility = View.VISIBLE
        }else{
            binding.btnPayTicket.text = "Beli Tiket ($total)"
            binding.btnPayTicket.visibility = View.VISIBLE
        }
    }
}