/*
 * Created by Muhamad Syafii
 * Tuesday, 27/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.ewallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syafii.movapps.R
import com.syafii.movapps.databinding.ActivityMyWalletBinding

class MyWalletActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyWalletBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mToolbarBack.setOnClickListener {
            finish()
        }


    }
}