package com.syafii.movapps.controller.splashscreen
/*
 * Created by Muhamad Syafii
 * Wednesday, 24/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.syafii.movapps.R
import com.syafii.movapps.controller.main.MainActivity
import com.syafii.movapps.controller.onboarding.OnBoardingActivity
import com.syafii.movapps.controller.signin.SignInActivity
import com.syafii.movapps.util.constant.FIRST_INSTALL
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreference
    private val TAG = SplashScreenActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        sharedPreference = SharedPreference(this)

        val isFirstInstall = sharedPreference.getBoolean(FIRST_INSTALL)
        val isLogin = sharedPreference.isLogin()
        Log.e(TAG, "Install : $isFirstInstall")
        Log.e(TAG, "Login : $isLogin")

        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstInstall == true) {
                if (isLogin) {
                    openActivity(MainActivity::class.java)
                    finish()
                } else {
                    openActivity(SignInActivity::class.java)
                    finish()
                }
            } else {
                openActivity(OnBoardingActivity::class.java)
                finish()
            }

        }, 3000)
    }
}

