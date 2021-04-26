/*
 * Created by Muhamad Syafii
 * Thursday, 22/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syafii.movapps.R
import com.syafii.movapps.controller.main.MainActivity
import com.syafii.movapps.controller.ticket.TicketPurchasedActivity
import com.syafii.movapps.databinding.ActivityCheckoutSuccessBinding
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast

class CheckoutSuccessActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCheckoutSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnHome.setOnClickListener {
            openActivity(MainActivity::class.java)
            finish()
        }

        binding.btnLookTicket.setOnClickListener {
            openActivity(TicketPurchasedActivity::class.java)

        }
    }

    override fun onBackPressed() {
    }
}