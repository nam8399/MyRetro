package com.sikstree.myretro.View.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Fragment.*
import com.sikstree.myretro.databinding.ActivityMainBinding
import com.sikstree.myretro.viewModel.MainViewModel
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_HOME
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_MYPROFILE
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_PLACE
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_RETROSHOP
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_USERRETRO


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    private val fragmentManager : FragmentManager = supportFragmentManager
    var backPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.setFragment(TAG_HOME, HomeFragment(), fragmentManager)

        onClick()

//        Glide.with(this)
//            .load(R.drawable.logo)
//            .into(binding.logo)
    }

    fun onClick() {
        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> viewModel.setFragment(TAG_HOME, HomeFragment(), fragmentManager)
                R.id.retroshopFragment -> viewModel.setFragment(TAG_RETROSHOP, RetroShopFragment(), fragmentManager)
                R.id.placeFragment-> viewModel.setFragment(TAG_PLACE, PlaceFragment(), fragmentManager)
                R.id.userretroFragment-> viewModel.setFragment(TAG_USERRETRO, UserRetroFragment(), fragmentManager)
                R.id.myprofileFragment-> viewModel.setFragment(TAG_MYPROFILE, MyProfileFragment(), fragmentManager)
            }
            true
        }

    }

    fun moveFragment(position : Int) {
        when(position) {
            1 -> binding.navigationView.selectedItemId = R.id.homeFragment
            2 -> binding.navigationView.selectedItemId = R.id.retroshopFragment
            3 -> binding.navigationView.selectedItemId = R.id.placeFragment
            4 -> binding.navigationView.selectedItemId = R.id.userretroFragment
            5 -> binding.navigationView.selectedItemId = R.id.myprofileFragment
        }
    }


    override fun onBackPressed() {
        //2.5초이내에 한 번 더 뒤로가기 클릭 시
        if (System.currentTimeMillis() - backPressedTime < 2500) {
            super.onBackPressed()
            return
        }
        Toast.makeText(this, "한번 더 클릭 시 홈으로 이동됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }


}