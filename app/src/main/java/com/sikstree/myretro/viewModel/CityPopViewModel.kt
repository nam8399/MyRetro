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
import com.sikstree.myretro.R

class CityPopViewModel( application: Application) : AndroidViewModel(application) {
    private val title = "CityPopViewModel"

    private var mediaPlayer: MediaPlayer? = null

    var isMusicPlay = MutableLiveData<Boolean>()



    init {
        isMusicPlay.value = false
    }

    fun playmusic() {
        mediaPlayer = MediaPlayer.create(getApplication(), R.raw.myretro_citypop)
        mediaPlayer?.start()
        isMusicPlay.value = true
    }

    fun pausemusic() {
        mediaPlayer?.pause()
        isMusicPlay.value = false
    }

    fun onStop() {
        mediaPlayer?.release()
        mediaPlayer = null
    }


}