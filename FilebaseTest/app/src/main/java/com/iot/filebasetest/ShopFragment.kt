package com.iot.filebasetest

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.iot.filebasetest.Bowl.Bowl
import com.iot.filebasetest.Interior.Interior
import com.iot.filebasetest.Sofa.Sofa
import com.iot.filebasetest.adapter.ShopListAdapter
import kotlin.collections.ArrayList

class ShopFragment:Fragment() {
    val database = Firebase.database
    val myRef = database.getReference("shop")
    lateinit var adapter: ShopListAdapter
    lateinit var sofas:ArrayList<Interior>
    lateinit var bowls : ArrayList<Interior>


    override fun onCreateView(
        inflater:LayoutInflater, container: ViewGroup?,
        savaeInstanceState : Bundle?

    ):View?{
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        sofas = ArrayList()
        bowls = ArrayList()

        var shopLv = view.findViewById<ListView>(R.id.shopLv)
        adapter = ShopListAdapter(sofas)
        shopLv.adapter = adapter

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                for(ds in snapshot.children){
                    when{
                        "sofa".equals(ds.key)-> {
                            val sofa = snapshot.child("sofa")
                            for (item in sofa.children) {
                                val id: String = item.key.toString()
                                val name: String = item.child("name").value as String
                                val price: Long = item.child("price").value as Long
                                val design: String = item.child("design").value as String
                                val isBuy = item.child("isBuy").value as Boolean
                                val isSet = item.child("isSet").value as Boolean

                                val sofam = Sofa(id, name, design, price, isBuy, isSet)
                                sofas.add(sofam)

                            }
                        }
                            "bowls".equals(ds.key)->{
                                val bowl = snapshot.child("bowl")

                                for (item in bowl.children) {
                                    val id: String = item.key.toString()
                                    val name: String = item.child("name").value as String
                                    val price: Long = item.child("price").value as Long
                                    val design: String = item.child("design").value as String
                                    val isBuy = item.child("isBuy").value as Boolean
                                    val isSet = item.child("isSet").value as Boolean

                                    val bowll = Bowl(id, name, design, price, isBuy, isSet)
                                    bowls.add(bowll)


                                }
                            }
                        }
                    }

                adapter.notifyDataSetChanged()

            }
            override fun onCancelled(error: DatabaseError){
                // 읽어오기 실패 했을때
            }
        })
        return  view
    }
}