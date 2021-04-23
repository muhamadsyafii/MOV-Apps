/*
 * Created by Muhamad Syafii
 * , 7/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.syafii.movapps.databinding.FragmentTicketBinding
import com.syafii.movapps.model.Film
import com.syafii.movapps.util.ItemClickListener
import com.syafii.movapps.util.db.SharedPreference
import com.syafii.movapps.util.showToast

class TicketFragment : Fragment() {

    private lateinit var binding : FragmentTicketBinding
    private lateinit var sharedPreference : SharedPreference
    private lateinit var dbReference : DatabaseReference
    private var filmList = ArrayList<Film>()


    private val adapter = TodayAdapter(object : ItemClickListener<Film>{
        override fun onItemClick(data: Film) {
            showToast("Clikced ${data.judul}")
        }

    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTicketBinding.inflate(layoutInflater, container, false)
        sharedPreference = SharedPreference(requireContext())
        dbReference = FirebaseDatabase.getInstance().getReference("Film")

        getData()

        binding.rvToday.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    private fun getData() {
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                filmList.clear()

                for (getSnapShot in snapshot.children) {
                    val film = getSnapShot.getValue(Film::class.java)
                    filmList.add(film!!)

                    if (filmList.size > 0) {
                        adapter.setTodayAdapter(filmList)
                        binding.rvToday.adapter = adapter
                        binding.tvTotal.text = "${filmList.size} Movies"
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        })
    }

}