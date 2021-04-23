/*
 * Created by Muhamad Syafii
 * Thursday, 25/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.uploadphoto

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.syafii.movapps.R
import com.syafii.movapps.controller.main.MainActivity
import com.syafii.movapps.databinding.ActivityUploadPhotoBinding
import com.syafii.movapps.util.constant.NAME
import com.syafii.movapps.util.constant.URL
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast
import java.util.*

class UploadPhotoActivity : AppCompatActivity(), PermissionListener {

    private lateinit var binding: ActivityUploadPhotoBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private var statusAdd = false
    private lateinit var filePath: Uri
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var progressDialog: ProgressDialog
    private lateinit var sharedPreference: SharedPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()
        progressDialog = ProgressDialog(this)
        sharedPreference = SharedPreference(this)

        binding.tvName.text = intent.getStringExtra(NAME)

        binding.ivAdd.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                binding.btnSavePhoto.visibility = View.VISIBLE
                binding.ivAdd.setImageResource(R.drawable.ic_add_upload)
                binding.ivProfile.setImageResource(R.drawable.ic_profile)
            } else {
                Dexter.withContext(this)
                    .withPermission(android.Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()
            }
        }

        binding.btnUploadLater.setOnClickListener {
            finishAffinity()

            openActivity(MainActivity::class.java)
        }

        binding.btnSavePhoto.setOnClickListener {
            progressDialog.setTitle("Uploading....")
            progressDialog.show()

            val ref = storageReference.child("images/" + UUID.randomUUID().toString())
            ref.putFile(filePath).addOnCompleteListener {
                progressDialog.dismiss()
                showToast("Uploaded")

                ref.downloadUrl.addOnSuccessListener {
                    sharedPreference.saveString(URL, it.toString())
                }

                finishAffinity()
                openActivity(MainActivity::class.java)
            }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    showToast("Failed")
                }

                .addOnProgressListener { taskSnapShot ->
                    val progress =
                        100.0 * taskSnapShot.bytesTransferred / taskSnapShot.totalByteCount
                    progressDialog.setTitle("Upload " + progress.toInt() + " %")
                }
        }

    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        showToast("Anda tidak bisa menambahkan photo profile")
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {

    }

    override fun onBackPressed() {
        showToast("Tergesah? klik tombol upload nannti saja")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data")
            statusAdd = false

            filePath = data?.data!!

            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfile)

            binding.btnSavePhoto.visibility = View.VISIBLE
            binding.ivAdd.setImageResource(R.drawable.ic_delete)

        }
    }
}