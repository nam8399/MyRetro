package com.sikstree.myretro.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeViewModel( application: Application) : AndroidViewModel(application) {
    private val title = "HomeViewModel"

    init{

    }


    fun makeData() {
        val database = Firebase.firestore
        val adocRef = database.collection("retroItem").document("Retro")

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                Log.d(title, snapshot.data!!["title"].toString())
            }
        })
    }
}