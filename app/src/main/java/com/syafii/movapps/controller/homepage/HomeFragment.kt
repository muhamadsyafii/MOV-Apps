/*
 * Created by Muhamad Syafii
 * Thursday, 25/3/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.homepage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.google.gson.Gson
import com.syafii.movapps.R
import com.syafii.movapps.controller.detailmovie.DetailMovieActivity
import com.syafii.movapps.controller.main.MainActivity
import com.syafii.movapps.databinding.FragmentHomeBinding
import com.syafii.movapps.model.Film
import com.syafii.movapps.util.ItemClickListener
import com.syafii.movapps.util.constant.MOVIE_DATA
import com.syafii.movapps.util.currency
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.openActivityDetail
import com.syafii.movapps.util.showToast
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private val TAG = HomeFragment::class.java.simpleName
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreference: SharedPreference
    private lateinit var dbReference: DatabaseReference
    private var filmList = ArrayList<Film>()


    //this is object in recyclerview adapter for after click item then move to detail movie
    private val nowPlayingAdapter = NowPlayingAdapter(object : ItemClickListener<Film> {
        override fun onItemClick(data: Film) {
            openActivityDetail(DetailMovieActivity::class.java, MOVIE_DATA, Gson().toJson(data))
        }

    })

    //this is object in recyclerview adapter for after click item then move to detail movie
    private val comingSoonAdapter = ComingSoonAdapter(object : ItemClickListener<Film> {
        override fun onItemClick(data: Film) {
            openActivityDetail(DetailMovieActivity::class.java, MOVIE_DATA, Gson().toJson(data))
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        sharedPreference = SharedPreference(requireContext())
        dbReference = FirebaseDatabase.getInstance().getReference("Film")

        updateProfile()
        getData()

        //this is add color load for swipe refresh6
        binding.swipeRefresh
            .setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.colorPink),
                ContextCompat.getColor(requireContext(), R.color.colorYellow),
                ContextCompat.getColor(requireContext(), R.color.colorBlue)
            )
        //this is swipeRefresh for load again data
        binding.swipeRefresh.setOnRefreshListener {
            (Objects.requireNonNull(activity) as MainActivity).syncMember()
            updateProfile()
            getData()
            binding.shimmerNowPlaying.visibility = View.VISIBLE
            binding.shimmerNowPlaying.startShimmer()
            binding.shimmerComingSoon.visibility = View.VISIBLE
            binding.shimmerComingSoon.startShimmer()
            binding.rvNowPlaying.visibility = View.GONE
            binding.shimmerComingSoon.visibility = View.GONE
        }

        //this is setup layout manager for recyclerview
        binding.rvNowPlaying.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvComingSoon.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        //this is button profile for show image after click
        binding.btnProfile.setOnClickListener {
            val data = sharedPreference.getCurrentUser()
            val builder = AlertDialog.Builder(requireContext())
            val layoutInflater = LayoutInflater.from(requireContext())
            val view = layoutInflater.inflate(R.layout.dialog_image, null)
            val image = view.findViewById<ImageView>(R.id.im_pic_dialog)
            Glide.with(requireContext()).load(data?.url).into(image)
            builder.setView(view)
            builder.setPositiveButton("Close") { dialog, which ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()

            //this is for change the color in text color button
            val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            with(button) {
                setTextColor(ContextCompat.getColor(requireContext(),R.color.colorBlue))
            }
        }

        return binding.root
    }

    //this method for get data from firebase
    private fun getData() {
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                filmList.clear()
                binding.swipeRefresh.isRefreshing = true

                for (getSnapShot in snapshot.children) {
                    val film = getSnapShot.getValue(Film::class.java)
                    filmList.add(film!!)

                    if (filmList.size > 0) {
                        nowPlayingAdapter.setNowPlayingAdapter(filmList)
                        binding.rvNowPlaying.adapter = nowPlayingAdapter
                        binding.shimmerNowPlaying.stopShimmer()
                        binding.swipeRefresh.isRefreshing = false
                        binding.shimmerNowPlaying.visibility = View.GONE
                        binding.rvNowPlaying.visibility = View.VISIBLE

                        binding.rvComingSoon.adapter = comingSoonAdapter
                        comingSoonAdapter.setComingSoonAdapter(filmList)
                        binding.shimmerComingSoon.stopShimmer()
                        binding.shimmerComingSoon.visibility = View.GONE
                        binding.rvComingSoon.visibility = View.VISIBLE

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        })
    }

    //this is method for update profile
    private fun updateProfile() {
        val user = sharedPreference.getCurrentUser()

        binding.tvName.text = user!!.nama
        binding.tvSaldo.text = user.saldo
        currency(user.saldo.toDouble(), binding.tvSaldo)

        //check condition for url have value or not
        if (user.url == "") {
            binding.btnProfile.setImageResource(R.drawable.ic_profile)
        } else {
            Glide.with(this)
                .load(user.url)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.btnProfile)
        }
    }
}