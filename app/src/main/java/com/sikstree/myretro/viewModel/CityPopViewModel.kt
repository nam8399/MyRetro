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
    var position_music = MutableLiveData<Int>()



    init {
        isMusicPlay.value = false
        position_music.value = 0
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
//        mediaPlayer?.release()
//        mediaPlayer = null
    }


}