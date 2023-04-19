package com.sikstree.myretro.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Activity.MainActivity
import com.sikstree.myretro.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {
    private lateinit var binding : FragmentPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_place, container, false)

        initview()

        return binding.root
    }

    private fun initview() {
        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout
//
//        val viewpagerFragmentAdapter = activity.ViewpagerFragmentAdapter(this)
//
//        // ViewPager2의 adapter 설정
//        viewPager.adapter = viewpagerFragmentAdapter
//
//
//        // ###### TabLayout과 ViewPager2를 연결
//        // 1. 탭메뉴의 이름을 리스트로 생성해둔다.
//        val tabTitles = listOf<String>("첫번째", "두번째", "세번째")
//
//        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정한다.
//        TabLayoutMediator(tabLayout, viewPager, {tab, position -> tab.text = tabTitles[position]}).attach()
        // FragmentStateAdapter 생성

    }

}