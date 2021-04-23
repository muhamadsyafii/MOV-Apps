/*
 * Created by Muhamad Syafii
 * Wednesday, 24/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.syafii.movapps.R
import com.syafii.movapps.databinding.ActivityOnBoardingBinding
import com.syafii.movapps.model.OnBoarding
import com.syafii.movapps.controller.signin.SignInActivity
import com.syafii.movapps.util.constant.FIRST_INSTALL
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var adapter: OnBoardingAdapter
    private var list = mutableListOf<OnBoarding>()
    private var position = 0
    private lateinit var sharedPreference: SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = SharedPreference(this)
        adapter = OnBoardingAdapter(this)

        list.add(
            OnBoarding(
                R.drawable.ic_boarding_one,
                getString(R.string.txt_tite_boarding_one),
                getString(R.string.txt_desc_boarding_one)
            )
        )
        list.add(
            OnBoarding(
                R.drawable.ic_boarding_two,
                getString(R.string.txt_tite_boarding_two),
                getString(R.string.txt_desc_boarding_two)
            )
        )
        list.add(
            OnBoarding(
                R.drawable.ic_boarding_three,
                getString(R.string.txt_tite_boarding_three),
                getString(R.string.txt_desc_boarding_three)
            )
        )

        adapter.setBoardingList(list)
        binding.vpOnBoarding.adapter = adapter
        position = binding.vpOnBoarding.currentItem

        binding.btnNext.setOnClickListener {
            if (position < list.size) {
                position++
                binding.vpOnBoarding.currentItem = position
            }

            if (position == list.size - 1) {
                binding.btnSkip.visibility = View.GONE
                binding.btnNext.text = getString(R.string.txt_memulai)
                binding.btnNext.setOnClickListener {
                    sharedPreference.saveBoolean(FIRST_INSTALL, true)
                    openActivity(SignInActivity::class.java)
                    finish()
                }
            }
            if (position == list.size - 2) {
                binding.btnSkip.visibility = View.GONE
            }
        }

        binding.btnSkip.setOnClickListener {
            position = list.size - 1
            binding.vpOnBoarding.currentItem = position

            if (position == list.size - 1) {
                binding.btnSkip.visibility = View.GONE
                binding.btnNext.text = getString(R.string.txt_memulai)
                binding.btnNext.setOnClickListener {
                    sharedPreference.saveBoolean(FIRST_INSTALL, true)
                    openActivity(SignInActivity::class.java)
                    finish()
                }
            }
        }
    }
}