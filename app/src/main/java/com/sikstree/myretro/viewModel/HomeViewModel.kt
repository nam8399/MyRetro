package com.sikstree.myretro.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeViewModel( application: Application) : AndroidViewModel(application) {
    private val title = "HomeViewModel"

    var retro_item_content = MutableLiveData<String>()
    var retro_item_content2 = MutableLiveData<String>()
    var retro_item_content3 = MutableLiveData<String>()
    var retro_item_url = MutableLiveData<String>()
    var retro_item_url2 = MutableLiveData<String>()
    var retro_item_url3 = MutableLiveData<String>()

    init{
        retro_item_content.value = ""
        retro_item_content2.value = ""
        retro_item_content3.value = ""
        retro_item_url.value = ""
        retro_item_url2.value = ""
        retro_item_url3.value = ""
    }


    fun makeData() {
        val database = Firebase.firestore
        val adocRef = database.collection("retroItem").document("Retro")

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                retro_item_content.value = snapshot.data!!["cnt_title"].toString()
                retro_item_content2.value = snapshot.data!!["cnt2_title"].toString()
                retro_item_content3.value = snapshot.data!!["cnt3_title"].toString()
                retro_item_url.value = snapshot.data!!["cnt_img"].toString()
                retro_item_url2.value = snapshot.data!!["cnt2_img"].toString()
                retro_item_url3.value = snapshot.data!!["cnt3_img"].toString()
            }
        })
    }
}