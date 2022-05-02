package Layout.dnt_list_view

import DNTClass.CarriageInfo
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.example.dnt.R

// writer : Yoon Jae Uk
// date : 2022.04.28 ~ ?
// content : DNT List 어댑터



class DNTListAdapter
    /* class, member, basic constructor(init) declaration begin */
    (private val activity:Activity) : BaseAdapter() {
    // constructor(member) : interface
    private var arrayList:ArrayList<DNTListSample> = ArrayList<DNTListSample>()
    /*class and member init declaration end*/

    /* Primitive static type declaration begin */
    val TAG:String = "DNTListAdapter"
    /* Primitive static type declaration end */

    /* class method declaration begin */
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView: View? = view
        if(convertView==null) {
            val inflater: LayoutInflater = activity.layoutInflater
            Log.i(TAG,"getView: called")
            convertView = inflater.inflate(R.layout.train_carriage_item,parent,false)
        }
        val constraintLayout = convertView!!

        val itemData: DNTListSample = arrayList[position]
        val number:String = "${itemData.curNum}/${itemData.resNum}/${itemData.MAX_NUM}"
        val itemUniqueNameTV: AppCompatTextView = constraintLayout.findViewById(R.id.item_UniqueNameTV)
        val itemPeopleNumTV: AppCompatTextView = constraintLayout.findViewById(R.id.item_PeopleNumTV)

        itemUniqueNameTV.text = itemData.UNIQUE_NAME
        itemPeopleNumTV.text = number

        return constraintLayout as View
    }

    fun addItem(carrItem: CarriageInfo){
        val item: DNTListSample = DNTListSample(carrItem.CARR_NAME,carrItem.curNum,carrItem.resNum,carrItem.MAX_NUM)
        arrayList.add(item)
    }
    /* class method declaration end */
}
