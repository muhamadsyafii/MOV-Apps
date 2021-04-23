package com.syafii.movapps.controller.main
/*
 * Created by Muhamad Syafii
 * Wednesday, 24/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.*
import com.syafii.movapps.R
import com.syafii.movapps.controller.homepage.HomeFragment
import com.syafii.movapps.controller.profile.ProfileFragment
import com.syafii.movapps.controller.signin.SignInActivity
import com.syafii.movapps.controller.ticket.TicketFragment
import com.syafii.movapps.databinding.ActivityMainBinding
import com.syafii.movapps.model.User
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var sharedPreference: SharedPreference
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var user: User
    private var isExit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbReference = FirebaseDatabase.getInstance().getReference("User")
        sharedPreference = SharedPreference(this)
        user = sharedPreference.getCurrentUser()!!

        loadFragment(fragment = HomeFragment())


        binding.imNavHome.setOnClickListener {
            loadFragment(fragment = HomeFragment())

            changeIconNav(binding.imNavHome, R.drawable.ic_nav_home_active)
            changeIconNav(binding.imNavTicket, R.drawable.ic_nav_tiket)
            changeIconNav(binding.imNavProfile, R.drawable.ic_nav_profile)
        }

        binding.imNavTicket.setOnClickListener {
            loadFragment(fragment = TicketFragment())

            changeIconNav(binding.imNavHome, R.drawable.ic_nav_home)
            changeIconNav(binding.imNavTicket, R.drawable.ic_nav_tiket_active)
            changeIconNav(binding.imNavProfile, R.drawable.ic_nav_profile)
        }

        binding.imNavProfile.setOnClickListener {
            loadFragment(fragment = ProfileFragment())

            changeIconNav(binding.imNavHome, R.drawable.ic_nav_home)
            changeIconNav(binding.imNavTicket, R.drawable.ic_nav_tiket)
            changeIconNav(binding.imNavProfile, R.drawable.ic_nav_profile_active)
        }

        syncMember()
    }

    override fun onResume() {
        super.onResume()
        syncMember()
    }

    fun syncMember() {
        dbReference.child(user.username).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(User::class.java)

                if (data != null) {
                    if (data.password == user.password) {
                        sharedPreference.setCurrentUser(data)
                    } else {
                        sharedPreference.setLogout()
                        openActivity(SignInActivity::class.java)
                        finish()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        })
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_NONE)
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun changeIconNav(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }

    override fun onBackPressed() {
        if (isExit){
            super.onBackPressed()
            finishAffinity()
        }else{
            isExit = true
            showToast("Press once again to close the app..")
        }
        Handler(Looper.getMainLooper()).postDelayed({ isExit = false }, 2000)
    }


}