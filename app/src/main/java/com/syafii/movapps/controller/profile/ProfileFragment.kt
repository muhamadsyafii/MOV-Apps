/*
 * Created by Muhamad Syafii
 * Wednesday, 7/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.syafii.movapps.R
import com.syafii.movapps.controller.editprofile.EditProfileActivity
import com.syafii.movapps.databinding.FragmentProfileBinding
import com.syafii.movapps.model.User
import com.syafii.movapps.util.db.DialogUtil
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        sharedPreference = SharedPreference(requireContext())

        showProfile()

        binding.tvWallet.setOnClickListener {
            showToast("Sorry this feature not available")
        }

        binding.tvEditProfile.setOnClickListener {
            openActivity(EditProfileActivity::class.java)
        }

        binding.tvChangeLanguage.setOnClickListener {
            showToast("Sorry this feature not available")
        }

        binding.tvHelp.setOnClickListener {
            showToast("Sorry this feature not available")
        }


        return binding.root
    }

    //this is method for update profile
    private fun showProfile() {
        val user : User? = sharedPreference.getCurrentUser()

        binding.tvName.text = user!!.nama
        binding.tvEmail.text = user!!.email

        //check condition for url have value or not
        if (user.url == "") {
            binding.imProfile.setImageResource(R.drawable.ic_profile)
        } else {
            Glide.with(this)
                .load(user.url)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imProfile)
        }

        binding.imProfile.setOnClickListener {
            val dialog = DialogUtil()
            dialog.showDialog(requireContext(), user)
        }
    }
}