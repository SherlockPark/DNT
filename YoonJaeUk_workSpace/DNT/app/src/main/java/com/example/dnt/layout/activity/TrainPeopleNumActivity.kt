package com.example.dnt.layout.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.example.dnt.R
import com.example.dnt.layout.dnt_list_view.DNTListAdapter
import com.example.dnt.layout.dnt_list_view.DNTListView

// writer : Yoon Jae Uk
// date : 2022.04.28 ~ ?
// content : 인원수 체크 화면 코틀린 코드, 'activity_train_people.xml' 파일과 함께 참고

class TrainPeopleNumActivity : AppCompatActivity() {
    /* Layout declaration begin */
    private var tpnUniqueNumTV: AppCompatTextView? = null
    private var tpnCurStationTV: AppCompatTextView? = null
    private var tpnDNTLV: DNTListView? = null
    /* Layout declaration end */

    /* Other Object declaration begin */
    private var tpnDNTListAdapter: DNTListAdapter? = null
    /* Other Object declaration end */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_people_num)
        /* onCreate() writer code begin */
        tpnUniqueNumTV = findViewById(R.id.tpn_UniqueNumTV)
        tpnCurStationTV = findViewById(R.id.tpn_CurStationTV)
        tpnDNTLV = findViewById(R.id.tpn_DNTLV)
        // 레이아웃 커넥팅

        tpnDNTListAdapter = DNTListAdapter(this) // 어댑터 생성
        tpnDNTLV?.adapter = tpnDNTListAdapter // 어댑터 세팅
        // 리스트 세팅

        tpnDNTListAdapter?.addItem("1호차",5,10,20)
        tpnDNTListAdapter?.addItem("2호차",5,10,20)
        tpnDNTListAdapter?.addItem("3호차",5,10,20)
        tpnDNTListAdapter?.addItem("4호차",5,10,20)
        // 임시 방편으로 추가한 리스트들

        tpnDNTListAdapter?.notifyDataSetChanged() // 리스트 업데이트
        /* onCreate() writer code end */
    }
}