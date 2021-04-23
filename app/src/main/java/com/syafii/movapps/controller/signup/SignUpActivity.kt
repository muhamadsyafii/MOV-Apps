/*
 * Created by Muhamad Syafii
 * Thursday, 25/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.syafii.movapps.controller.uploadphoto.UploadPhotoActivity
import com.syafii.movapps.databinding.ActivitySignUpBinding
import com.syafii.movapps.model.User
import com.syafii.movapps.util.constant.NAME
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var dbDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        dbDatabase = FirebaseDatabase.getInstance().reference
        dbReference = firebaseDatabase.getReference("User")

        binding.imBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()

            when {
                username == "" -> {
                    binding.etUsername.error = "Silahkan isi username anda"
                    binding.etUsername.requestFocus()
                }
                password == "" -> {
                    binding.etPassword.error = "Silahkan isi password anda"
                    binding.etPassword.requestFocus()
                }
                name == "" -> {
                    binding.etName.error = "Silahkan isi nama anda"
                    binding.etName.requestFocus()
                }
                email == "" -> {
                    binding.etEmail.error = "Silahkan isi email anda"
                    binding.etEmail.requestFocus()
                }
                else -> {
                    pushData(username, password, name, email)
                }
            }
        }
    }
    private fun pushData(username: String, password: String, name: String, email: String) {
        val user = User()
        user.username = username
        user.password = password
        user.nama = name
        user.email = email

        checkingUsername(username,user)
    }

    private fun checkingUsername(username: String, data: User) {
        dbReference.child(username).addValueEventListener(object  : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user == null){
                    dbReference.child(username).setValue(data)
                    openActivity(UploadPhotoActivity::class.java, NAME, data.nama)
                }else{
                    showToast("User sudah terdaftar")
                }
            }
        })
    }
}

