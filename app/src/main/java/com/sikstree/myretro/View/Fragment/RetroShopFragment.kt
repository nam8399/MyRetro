package com.sikstree.myretro.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.sikstree.myretro.Adapter.ListAdapterGrid
import com.sikstree.myretro.Data.ItemData
import com.sikstree.myretro.R
import com.sikstree.myretro.databinding.FragmentRetroShopBinding

class RetroShopFragment : Fragment() {
    lateinit var binding : FragmentRetroShopBinding
    val datas = mutableListOf<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_retro_shop, container, false)

        datas.apply {

        }

        var list = arrayListOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8")
        var listManager = GridLayoutManager(context, 3)
        var listAdapter = ListAdapterGrid(list)

        var recyclerList = binding.recyclerGridView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }


        return binding.root
    }

}