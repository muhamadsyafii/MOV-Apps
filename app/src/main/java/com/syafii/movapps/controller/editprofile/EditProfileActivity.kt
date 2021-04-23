/*
 * Created by Muhamad Syafii
 * Tuesday, 20/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syafii.movapps.R
import com.syafii.movapps.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}