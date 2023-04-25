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

        var listManager = GridLayoutManager(context, 3)
        var listAdapter = ListAdapterGrid()

        datas.apply {
            add(ItemData(img = R.drawable.img_ex1, title = "mary", url = 24))
            add(ItemData(img = R.drawable.img_ex1, title = "jenny", url = 26))
            add(ItemData(img = R.drawable.img_ex1, title = "jhon", url = 27))
            add(ItemData(img = R.drawable.img_ex1, title = "ruby", url = 21))
            add(ItemData(img = R.drawable.img_ex1, title = "yuna", url = 23))

            listAdapter.datas = datas
            listAdapter.notifyDataSetChanged()


            var recyclerList = binding.recyclerGridView.apply {
                setHasFixedSize(true)
                layoutManager = listManager
                adapter = listAdapter
            }

        }



        return binding.root
    }

}