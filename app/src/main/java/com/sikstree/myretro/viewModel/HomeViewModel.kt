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

    // 추천 핫-템 관련 변수
    var retro_item_content = MutableLiveData<String>()
    var retro_item_content2 = MutableLiveData<String>()
    var retro_item_content3 = MutableLiveData<String>()
    var retro_item_click = MutableLiveData<Boolean>()
    var retro_item_url = MutableLiveData<String>()
    var retro_item_url2 = MutableLiveData<String>()
    var retro_item_url3 = MutableLiveData<String>()
    var retro_item_click2 = MutableLiveData<Boolean>()
    var retro_item_img = MutableLiveData<String>()
    var retro_item_img2 = MutableLiveData<String>()
    var retro_item_img3 = MutableLiveData<String>()
    var retro_item_click3 = MutableLiveData<Boolean>()

    // 추천 장소 관련 변수
    var retro_place_content = MutableLiveData<String>()
    var retro_place_content2 = MutableLiveData<String>()
    var retro_place_content3 = MutableLiveData<String>()
    var retro_place_url = MutableLiveData<String>()
    var retro_place_url2 = MutableLiveData<String>()
    var retro_place_url3 = MutableLiveData<String>()
    var retro_place_img = MutableLiveData<String>()
    var retro_place_img2 = MutableLiveData<String>()
    var retro_place_img3 = MutableLiveData<String>()


    init{
        retro_item_content.value = ""
        retro_item_content2.value = ""
        retro_item_content3.value = ""
        retro_item_click.value = false
        retro_item_click2.value = false
        retro_item_click3.value = false
        retro_item_url.value = ""
        retro_item_url2.value = ""
        retro_item_url3.value = ""
        retro_item_img.value = ""
        retro_item_img2.value = ""
        retro_item_img3.value = ""

        retro_place_content.value = ""
        retro_place_content2.value = ""
        retro_place_content3.value = ""
        retro_place_img.value = ""
        retro_place_img2.value = ""
        retro_place_img3.value = ""
        retro_item_url.value = ""
        retro_item_url2.value = ""
        retro_item_url3.value = ""
    }


    fun makeData() {
        val database = Firebase.firestore
        val adocRef = database.collection("retroItem").document("Retro")
        val adocRefPlc = database.collection("retroPlace").document("Retro")

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                retro_item_content.value = snapshot.data!!["cnt_title"].toString()
                retro_item_content2.value = snapshot.data!!["cnt2_title"].toString()
                retro_item_content3.value = snapshot.data!!["cnt3_title"].toString()
                retro_item_img.value = snapshot.data!!["cnt_img"].toString()
                retro_item_img2.value = snapshot.data!!["cnt2_img"].toString()
                retro_item_img3.value = snapshot.data!!["cnt3_img"].toString()
                retro_item_url.value = snapshot.data!!["cnt_url"].toString()
                retro_item_url2.value = snapshot.data!!["cnt2_url"].toString()
                retro_item_url3.value = snapshot.data!!["cnt3_url"].toString()
            }
        })

        adocRefPlc.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                retro_place_url.value = snapshot.data!!["cnt_url"].toString()
                retro_place_url2.value = snapshot.data!!["cnt2_url"].toString()
                retro_place_url3.value = snapshot.data!!["cnt3_url"].toString()
            }
        })
    }

    fun clickRetroItem(itemindex : Int) {
        when (itemindex) {
            1 -> retro_item_click.value = true
            2 -> retro_item_click2.value = true
            3 -> retro_item_click3.value = true
        }
    }
}