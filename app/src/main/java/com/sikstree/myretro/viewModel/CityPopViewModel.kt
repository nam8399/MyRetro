package com.sikstree.myretro.viewModel

import android.app.Application
import android.media.MediaPlayer
import android.os.Build
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sikstree.myretro.R

class CityPopViewModel( application: Application) : AndroidViewModel(application) {
    private val title = "CityPopViewModel"

    private var mediaPlayer: MediaPlayer? = null

    var isMusicPlay = MutableLiveData<Boolean>()
    var position_music = MutableLiveData<Int>()

    // 시티팝 추천템 관련 변수
    var citypop_item_content = MutableLiveData<String>()
    var citypop_item_content2 = MutableLiveData<String>()
    var citypop_item_url = MutableLiveData<String>()
    var citypop_item_url2 = MutableLiveData<String>()
    var citypop_item_click = MutableLiveData<Boolean>()
    var citypop_item_click2 = MutableLiveData<Boolean>()
    var citypop_item_img = MutableLiveData<String>()
    var citypop_item_img2 = MutableLiveData<String>()




    init {
        isMusicPlay.value = false
        position_music.value = 0

        citypop_item_content.value = ""
        citypop_item_content2.value = ""
        citypop_item_url.value = ""
        citypop_item_url2.value = ""
        citypop_item_img.value = ""
        citypop_item_img2.value = ""
        citypop_item_click.value = false
        citypop_item_click2.value = false
    }

    fun makeData() {
        val database = Firebase.firestore
        val adocRef = database.collection("retroItem").document("Citypop")

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                citypop_item_content.value = snapshot.data!!["cnt_title"].toString()
                citypop_item_content2.value = snapshot.data!!["cnt2_title"].toString()
                citypop_item_img.value = snapshot.data!!["cnt_img"].toString()
                citypop_item_img2.value = snapshot.data!!["cnt2_img"].toString()
                citypop_item_url.value = snapshot.data!!["cnt_url"].toString()
                citypop_item_url2.value = snapshot.data!!["cnt2_url"].toString()
            }
        })
    }

    fun clickCitypopItem(itemindex : Int) {
        when (itemindex) {
            1 -> citypop_item_click.value = true
            2 -> citypop_item_click2.value = true
        }
    }


    fun playmusic() {
        mediaPlayer = MediaPlayer.create(getApplication(), R.raw.myretro_citypop)
        if (position_music.value != 0) {
            position_music.value?.let { mediaPlayer?.seekTo(it) }
        }
        mediaPlayer?.start()
        isMusicPlay.value = true
        mediaPlayer?.setOnCompletionListener{
            isMusicPlay.value = false
            replaymusic()
        }
    }

    fun pausemusic() {
        mediaPlayer?.pause()
        isMusicPlay.value = false
        position_music.value = mediaPlayer?.currentPosition
    }

    fun replaymusic() {
        if (isMusicPlay.value == true) {
            position_music.value = 0
            position_music.value?.let { mediaPlayer?.seekTo(it) }
            mediaPlayer?.start()
        } else {
            position_music.value = 0
        }
    }

    fun onStop() {
        mediaPlayer?.release()
        mediaPlayer = null
    }


}