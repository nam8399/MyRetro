package com.sikstree.myretro.viewModel

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sikstree.myretro.Data.ItemData
import com.sikstree.myretro.R
import com.sikstree.myretro.View.Activity.MainActivity


class RetroshopViewModel(application: Application) : AndroidViewModel(application) {
    private val title = "RetroshopViewModel"

//    private var firestore : FirebaseFirestore? = null
    private var uid : String? = null
    private var firestore : FirebaseFirestore? = null

    var itemOnclickEvent = MutableLiveData<String>()

    init {
        itemOnclickEvent.value = ""
    }



    inner class ListAdapterGrid(): RecyclerView.Adapter<ListAdapterGrid.ViewHolder>() {

//    var datas = mutableListOf<ItemData>()

        var datas : ArrayList<ItemData> = arrayListOf()


        init {
            firestore = FirebaseFirestore.getInstance()
            firestore?.collection("retroItems")?.orderBy("title", Query.Direction.DESCENDING)
                ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    datas.clear()
                    if (querySnapshot == null) return@addSnapshotListener

                    // 데이터 받아오기
                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(ItemData::class.java)
                        datas.add(item!!)
                    }
                    notifyDataSetChanged()
                }
        }


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val itemTitle: TextView = itemView.findViewById(R.id.item_title)
            private val itemUrl: String = ""
            private val itemImg: ImageView = itemView.findViewById(R.id.item_img)

            fun bind(item: ItemData) {
                itemTitle.text = item.title
//            itemUrl = item.age.toString()
                Glide
                    .with(itemView)
                    .load(item.img)
                    .centerCrop()
                    .into(itemImg)


                itemImg.setOnClickListener { // 아이템 클릭 시 LiveData 값을 변경하여 Fragment에서 Observe 하다가 변경 시 웹뷰 띄우도록 설정
                    Log.d(title, "Item Onclick! " + item.url)
                    itemOnclickEvent.value = item.url
                }

            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.list_grid_item, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(datas[position])


//        holder.layout.layoutListItem.setOnClickListener {
//            Toast.makeText(holder.layout.context, "${list[position]} Click!", Toast.LENGTH_SHORT).show()
//        }
        }

        override fun getItemCount(): Int {
            return datas.size
        }

    }
}