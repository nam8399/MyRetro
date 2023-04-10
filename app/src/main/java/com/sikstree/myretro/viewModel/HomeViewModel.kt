package com.sikstree.myretro.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class HomeViewModel( application: Application) : AndroidViewModel(application) {
    init{

    }


    fun makeData() {
        val database = FirebaseFirestore.getInstance()
        database.collection("")
    }
}