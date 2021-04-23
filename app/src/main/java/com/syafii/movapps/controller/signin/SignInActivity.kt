/*
 * Created by Muhamad Syafii
 * Thursday, 25/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.signin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.gson.Gson
import com.syafii.movapps.controller.main.MainActivity
import com.syafii.movapps.controller.signup.SignUpActivity
import com.syafii.movapps.databinding.ActivitySignInBinding
import com.syafii.movapps.model.User
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbReference = FirebaseDatabase.getInstance().getReference("User")
        sharedPreference = SharedPreference(this)

        binding.btnRegister.setOnClickListener {
            openActivity(SignUpActivity::class.java)
        }
        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username == "") {
                binding.etUsername.error = "Silahkan isi username terlebih dahulu"
                binding.etUsername.requestFocus()
            } else {
                if (password == "") {
                    binding.etPassword.error = "Silahkan isi password terlebih dahulu"
                    binding.etPassword.requestFocus()
                } else {
                    pushLogin(username, password)
                }
            }
        }
    }

    private fun pushLogin(username: String, password: String) {
        dbReference.child(username).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                sharedPreference.setCurrentUser(user)
                if (user == null) {
                    showToast("User tidak ditemukan")
                } else {
                    if (user.password == password) {
                        showToast("Welcome " + user.nama)
                        openActivity(MainActivity::class.java)
                        finish()
                    } else {
                        showToast("Password anda salah")
                    }
                }
            }
        })
    }
}