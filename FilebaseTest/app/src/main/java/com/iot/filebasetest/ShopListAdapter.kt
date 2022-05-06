package com.iot.filebasetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.iot.filebasetest.R
import com.iot.filebasetest.Interior.Interior


open class ShopListAdapter(_items:ArrayList<Interior>) : BaseAdapter() {
    var items: ArrayList<Interior> = _items
    override fun getCount() : Int{
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View? {
        var view = convertView
        val context = parent.context

        if (view == null) {
            var vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = vi.inflate(R.layout.custom_shoplv, parent, false)

        }
        val itemNm = view?.findViewById(R.id.itemNm) as TextView
        itemNm.text = items[position].name
        val itemPrc = view?.findViewById(R.id.itemPrc) as TextView
        itemPrc.text = items[position].price.toString()
        val itemDsn = view?.findViewById(R.id.itemDsn) as ImageView
        return  view
    }
    }
