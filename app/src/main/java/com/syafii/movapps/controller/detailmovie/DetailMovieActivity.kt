/*
 * Created by Muhamad Syafii
 * Thursday, 25/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.detailmovie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.gson.Gson
import com.syafii.movapps.controller.chooseseat.ChooseSeatActivity
import com.syafii.movapps.databinding.ActivityDetailMovieBinding
import com.syafii.movapps.model.Film
import com.syafii.movapps.model.Plays
import com.syafii.movapps.util.constant.MOVIE_DATA
import com.syafii.movapps.util.openActivity
import com.syafii.movapps.util.showToast

class DetailMovieActivity : AppCompatActivity() {
    private val TAG = DetailMovieActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var databaseReference: DatabaseReference
    private var dataList = ArrayList<Plays>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: Film = Gson().fromJson(intent.getStringExtra(MOVIE_DATA), Film::class.java)

        Log.e(TAG, "Detailnya : " + Gson().toJson(data))

        databaseReference = FirebaseDatabase.getInstance().getReference("Film")
            .child(data.judul.toString())
            .child("play")

        Glide.with(this).load(data.poster).into(binding.imDetailMovie)
        binding.tvNameMovie.text = data.judul
        binding.tvCategoryMovie.text = data.genre
        binding.tvDescMovie.text = data.desc
        binding.tvRatingDetail.text = data.rating

        binding.rvWhoPlayed.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.btnChooseSeat.setOnClickListener {
            openActivity(ChooseSeatActivity::class.java, MOVIE_DATA, Gson().toJson(data))
        }

        binding.imBack.setOnClickListener {
            finish()
        }

        getData()
    }

    private fun getData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (getSnapshot in snapshot.children) {
                    val data = getSnapshot.getValue(Plays::class.java)
                    val adapter = PlaysAdapter()
                    dataList.add(data!!)
                    Log.e(TAG, "PLaysss : " + Gson().toJson(data))
                    adapter.setPlaysAdapter(dataList)
                    binding.rvWhoPlayed.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        })
    }
}