/*
 * Created by Muhamad Syafii
 * , 5/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.util

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.*

/* Toast Activity*/
fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/*Toast Fragment*/
fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
/*Open Activity with data*/
fun <T> Fragment.openActivityDetail(destination : Class<T>, bundleKey : String, id : String){
    val intent = Intent(requireContext(), destination)
    intent.putExtra(bundleKey, id)
    startActivity(intent)
}
/*Open Activity from fragment*/
fun <T> Fragment.openActivity(destination : Class<T>){
    val intent = Intent(requireContext(), destination)
    startActivity(intent)
}

/*Open Activity*/
fun <T> Activity.openActivity(destination : Class<T>){
    val intent = Intent(this, destination)
    startActivity(intent)
}

/*Open Activity with data*/
fun <T> Activity.openActivity(destination : Class<T>, bundleKey : String, id : String){
    val intent = Intent(this, destination)
    intent.putExtra(bundleKey, id)
    startActivity(intent)
}

/*Currency IDR*/
fun currency(price: Double, textView: TextView){
    val localID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localID)
    textView.text = format.format(price)
}