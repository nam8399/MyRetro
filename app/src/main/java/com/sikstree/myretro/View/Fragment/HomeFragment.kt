package com.sikstree.myretro.View.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Activity.CityPopActivity
import com.sikstree.myretro.databinding.FragmentHomeBinding
import com.sikstree.myretro.viewModel.CityPopViewModel
import com.sikstree.myretro.viewModel.HomeViewModel
import com.sikstree.myretro.viewModel.MainViewModel
import kotlinx.coroutines.Job

class HomeFragment() : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel : HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.imgEx1.clipToOutline = true
        binding.imgEx2.clipToOutline = true
        binding.imgEx3.clipToOutline = true

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.makeData()

        binding.citypopzone.setOnClickListener {
            val intent = Intent(context, CityPopActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}