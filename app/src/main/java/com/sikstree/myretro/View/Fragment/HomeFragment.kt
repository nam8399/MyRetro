package com.sikstree.myretro.View.Fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sikstree.myretro.Adapter.ViewPager2Adater
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Activity.CityPopActivity
import com.sikstree.myretro.View.Activity.MainActivity
import com.sikstree.myretro.View.Activity.WebviewActivity
import com.sikstree.myretro.databinding.FragmentHomeBinding
import com.sikstree.myretro.viewModel.CityPopViewModel
import com.sikstree.myretro.viewModel.HomeViewModel
import com.sikstree.myretro.viewModel.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class HomeFragment() : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel : HomeViewModel
    lateinit var job : Job
    var bannerPosition : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        settingData() // viewmodel에서 데이터 호출
        settingView() // 뷰 관련 작업 세팅

        return binding.root
    }

    private fun settingView() {
        binding.citypopzone.setOnClickListener {
            val intent = Intent(context, CityPopActivity::class.java)
            startActivity(intent)
        }

        viewModel.retro_item_img.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it)
                .into(binding.img1)
        })

        viewModel.retro_item_img2.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it)
                .into(binding.img2)
        })

        viewModel.retro_item_img3.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it)
                .into(binding.img3)
        })


        var list = ArrayList<Int>()

        list.add(Color.parseColor("#ffff00"))
        list.add(Color.parseColor("#bdbdbd"))
        list.add(Color.parseColor("#0f9231"))
        var adapter = ViewPager2Adater(list,activity as MainActivity)

//        binding.viewpager2.offscreenPageLimit=3
        binding.viewpager2.getChildAt(0).overScrollMode=View.OVER_SCROLL_NEVER
        binding.viewpager2.adapter = adapter

        setupOnBoardingIndicators()
        setCurrentOnboardingIndicator(0)

        var transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(8))

        transform.addTransformer(ViewPager2.PageTransformer{ view: View, fl: Float ->
            var v = 1-Math.abs(fl)
            view.scaleY = 0.8f + v * 0.2f
        })

        binding.viewpager2.setPageTransformer(transform)

        adapter.setItemClickListener(object : ViewPager2Adater.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val intent = Intent(context, WebviewActivity::class.java)
                if (position == 0) {
                    intent.putExtra("url",viewModel.retro_item_url.value)
                } else if(position == 1) {
                    intent.putExtra("url", viewModel.retro_item_url2.value)
                } else if(position == 2) {
                    intent.putExtra("url", viewModel.retro_item_url3.value)
                }
                startActivity(intent)
            }
        })

        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position : Int){
                super.onPageSelected(position)
                bannerPosition = position

                setCurrentOnboardingIndicator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE ->{
                        if (!job.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> job.cancel()

                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })
    }

    private fun settingData() {
        viewModel.makeData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun setupOnBoardingIndicators(){ // 건축강의 뷰 인디게이터 구성셋팅
        val indicators =
            arrayOfNulls<ImageView>(3)

        var layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(8,0,8,0)

        for( i in indicators.indices){
            indicators[i] = ImageView(activity as MainActivity)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    activity as MainActivity,
                    R.drawable.onboarding_indicator_inactive
                ))

            indicators[i]?.layoutParams = layoutParams

            binding.indicators?.addView(indicators[i])
        }
    }

    private fun setCurrentOnboardingIndicator( index : Int){ // 건축 강의 인디게이터 뷰 이미지 셋팅
        var childCount = binding.indicators?.childCount
        for(i in  0 until childCount!!){
            var imageView = binding.indicators?.getChildAt(i) as ImageView
            if(i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(activity as MainActivity,
                    R.drawable.onboarding_indicator_active))
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(activity as MainActivity,
                    R.drawable.onboarding_indicator_inactive))
            }
        }
    }

    fun scrollJobCreate() { // auto Scroll을 위한 함수
        job = lifecycleScope.launchWhenResumed {
            delay(2000)
            binding.viewpager2.setCurrentItem(++bannerPosition, true)
            if (bannerPosition == 2) {
                bannerPosition = -1
            }
        }
    }


    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

}