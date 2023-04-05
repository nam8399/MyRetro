package com.sikstree.myretro.viewModel

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikstree.myretro.R

class MainViewModel( application: Application) : AndroidViewModel(application) {
    private val title = "MainViewModel"

    companion object {
        const val TAG_HOME = "home_fragment"
        const val TAG_RETROSHOP = "retroshop_fragment"
        const val TAG_PLACE = "place_fragment"
        const val TAG_USERRETRO = "userretro_fragment"
        const val TAG_MYPROFILE = "myplace_fragment"
    }

    init {
    }



    fun setFragment(tag: String, fragment: Fragment, fragmentManager: FragmentManager) {
        val manager: FragmentManager = fragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val retroshop = manager.findFragmentByTag(TAG_RETROSHOP)
        val place = manager.findFragmentByTag(TAG_PLACE)
        val userRetro = manager.findFragmentByTag(TAG_USERRETRO)
        val myprofile = manager.findFragmentByTag(TAG_MYPROFILE)

        if (home != null){
            fragTransaction.hide(home)
        }

        if (retroshop != null){
            fragTransaction.hide(retroshop)
        }

        if (place != null) {
            fragTransaction.hide(place)
        }

        if (userRetro != null) {
            fragTransaction.hide(userRetro)
        }

        if (myprofile != null) {
            fragTransaction.hide(myprofile)
        }

        if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        }

        else if (tag == TAG_RETROSHOP) {
            if (retroshop != null) {
                fragTransaction.show(retroshop)
            }
        }

        else if (tag == TAG_PLACE){
            if (place != null){
                fragTransaction.show(place)
            }
        }

        else if (tag == TAG_USERRETRO){
            if (userRetro != null){
                fragTransaction.show(userRetro)
            }
        }

        else if (tag == TAG_MYPROFILE){
            if (myprofile != null){
                fragTransaction.show(myprofile)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }


}