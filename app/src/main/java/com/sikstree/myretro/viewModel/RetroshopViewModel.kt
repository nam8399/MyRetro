package com.sikstree.myretro.viewModel

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
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
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.BootExtra
import kr.co.bootpay.android.models.BootItem
import kr.co.bootpay.android.models.BootUser
import kr.co.bootpay.android.models.Payload


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
//                    datas.clear()
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



    fun goRequest(fragmentManager: FragmentManager, pgName : String, price : Double) {
        val user = BootUser().setPhone("010-2666-6951") // 구매자 정보
        val extra = BootExtra()
            .setCardQuota("0,2,3") // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)

        val method = "카드"

        val items: MutableList<BootItem> = ArrayList()
        val item1 = BootItem().setName("마우스").setId("ITEM_CODE_MOUSE").setQty(1).setPrice(500.0)
        val item2 = BootItem().setName("키보드").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(500.0)
        items.add(item1)
        items.add(item2)

        val payload = Payload()
        payload.setApplicationId("6461aa773049c8001b96868b")
            .setOrderName("부트페이 결제테스트")
            .setPg(pgName)
            .setOrderId("1234")
            .setMethod(method)
            .setPrice(price)
            .setUser(user)
            .setExtra(extra).items = items

        val map: MutableMap<String, Any> = HashMap()
        map["1"] = "abcdef"
        map["2"] = "abcdef55"
        map["3"] = 1234
        payload.metadata = map

        Bootpay.init(fragmentManager, getApplication())
            .setPayload(payload)
            .setEventListener(object : BootpayEventListener {
                override fun onCancel(data: String) {
                    Log.d("bootpay", "cancel: $data")
                }

                override fun onError(data: String) {
                    Log.d("bootpay", "error: $data")
                }

                override fun onClose(data: String) {
                    Log.d("bootpay", "close: $data")
                    Bootpay.removePaymentWindow()
                }

                override fun onIssued(data: String) {
                    Log.d("bootpay", "issued: $data")
                }

                override fun onConfirm(data: String): Boolean {
                    Log.d("bootpay", "confirm: $data")
                    //                        Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
                    return true //재고가 있어서 결제를 진행하려 할때 true (방법 2)
                    //                        return false; //결제를 진행하지 않을때 false
                }

                override fun onDone(data: String) {
                    Log.d("done", data)
                }
            }).requestPayment()
    }
}