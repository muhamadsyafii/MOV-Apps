/*
 * Created by Muhamad Syafii
 * , 26/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.util.db

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.syafii.movapps.R
import com.syafii.movapps.model.User

class DialogUtil {
    fun showDialog(context : Context, user : User){
        val dialog = Dialog(context, R.style.FullHeightDialog)
        dialog.setContentView(R.layout.dialog_show_qr_code)
        val img = dialog.findViewById<ImageView>(R.id.im_qr_code)
        val btnAction = dialog.findViewById<Button>(R.id.btn_action)

        Glide.with(context)
            .load(user.url)
            .error(R.drawable.ic_profile)
            .into(img)

        btnAction.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun showQrCode(context: Context){
        val dialog = Dialog(context, R.style.FullHeightDialog)
        dialog.setContentView(R.layout.dialog_show_qr_code)
        val img = dialog.findViewById<ImageView>(R.id.im_qr_code)
        val btnAction = dialog.findViewById<Button>(R.id.btn_action)

        Glide.with(context)
            .load(R.drawable.sample_qr)
            .error(R.drawable.sample_qr)
            .into(img)

        btnAction.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}