package com.sikstree.myretro.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sikstree.myretro.Adapter.DetailPagerAdapter
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Activity.MainActivity
import com.sikstree.myretro.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {
    private lateinit var binding : FragmentPlaceBinding
    lateinit var viewPagers: ViewPager
    lateinit var tabLayouts: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_place, container, false)

        setUpViewPager()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager()
        tabLayouts.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }


    private fun setUpViewPager() {
        viewPagers = binding.viewPager
        tabLayouts = binding.tabLayout

        var adapter = DetailPagerAdapter(requireFragmentManager())
        adapter.addFragment(UserRetroFragment(), "인기 장소")
        adapter.addFragment(UserRetroFragment(), "최신 추천 장소")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)
    }

    companion object {
    }

}