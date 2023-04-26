package com.sikstree.myretro.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sikstree.myretro.Data.ItemData
import com.sikstree.myretro.R
import com.sikstree.myretro.databinding.FragmentRetroShopBinding
import com.sikstree.myretro.viewModel.RetroshopViewModel

class RetroShopFragment : Fragment() {
    lateinit var binding : FragmentRetroShopBinding
    val datas = mutableListOf<ItemData>()
    lateinit var viewModel : RetroshopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_retro_shop, container, false)


        viewModel = ViewModelProvider(this).get(RetroshopViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        initview()




        return binding.root
    }

    private fun initview() {
        var listManager = GridLayoutManager(context, 3)
        var listAdapter = viewModel.ListAdapterGrid()


        var recyclerList = binding.recyclerGridView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

    }

}