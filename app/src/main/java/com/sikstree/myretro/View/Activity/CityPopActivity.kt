package com.sikstree.myretro.View.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Fragment.*
import com.sikstree.myretro.databinding.ActivityCityPopBinding
import com.sikstree.myretro.databinding.ActivityMainBinding
import com.sikstree.myretro.viewModel.CityPopViewModel
import com.sikstree.myretro.viewModel.MainViewModel
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_HOME
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_PLACE
import com.sikstree.myretro.viewModel.MainViewModel.Companion.TAG_RETROSHOP


class CityPopActivity : AppCompatActivity() {
    private val title = "CityPopActivity"
    private lateinit var binding : ActivityCityPopBinding
    lateinit var viewModel : CityPopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_pop)

        viewModel = ViewModelProvider(this).get(CityPopViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        settingData()
        initview()

    }

    private fun initview() {
        setStatusBarTransparent()
        observeViewModel()
    }

    fun setStatusBarTransparent() {
        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    private fun observeViewModel() {
        val intent = Intent(this, WebviewActivity::class.java)
        viewModel.isMusicPlay.observe(this, Observer {
            if(it) {
                binding.btnPlaymusic.visibility = View.GONE
                binding.btnPausemusic.visibility = View.VISIBLE
            } else {
                binding.btnPlaymusic.visibility = View.VISIBLE
                binding.btnPausemusic.visibility = View.GONE
            }
        })

        viewModel.citypop_item_img.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .into(binding.imgWindowFirst)
        })

        viewModel.citypop_item_img2.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .into(binding.imgWindowSecond)
        })

        viewModel.citypop_item_click.observe(this, Observer {
            if (it) {
                viewModel.citypop_item_click.value = false
                intent.putExtra("url",viewModel.citypop_item_url.value)
                startActivity(intent)
            }
        })

        viewModel.citypop_item_click2.observe(this, Observer {
            if (it) {
                viewModel.citypop_item_click2.value = false
                intent.putExtra("url",viewModel.citypop_item_url2.value)
                startActivity(intent)
            }
        })
    }

    private fun settingData() {
        viewModel.makeData()
    }



    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}