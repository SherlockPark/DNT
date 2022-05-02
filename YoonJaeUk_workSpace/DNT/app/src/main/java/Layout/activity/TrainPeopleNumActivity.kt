package Layout.activity

import DNTClass.TrainInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.example.dnt.R
import Layout.dnt_list_view.DNTListAdapter
import Layout.dnt_list_view.DNTListView
import android.annotation.SuppressLint
import android.util.Log

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

    /* Primitive static type declaration begin */
    private val TAG:String = "TrainPeopleNumActivity"
    /* Primitive static type declaration end */

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_people_num)
        /* onCreate() writer code begin */
        val train:TrainInfo = intent.getSerializableExtra("info") as TrainInfo // 가져온 기차 정보

        tpnUniqueNumTV = findViewById(R.id.tpn_UniqueNumTV)
        tpnCurStationTV = findViewById(R.id.tpn_CurStationTV)
        tpnDNTLV = findViewById(R.id.tpn_DNTLV)
        // 레이아웃 커넥팅

        tpnUniqueNumTV?.text = "${train.UNIQUE_NUMBER}호"
        tpnCurStationTV?.text = train.currentStation
        // 레이아웃 텍스트 세팅

        tpnDNTListAdapter = DNTListAdapter(this) // 어댑터 생성
        tpnDNTLV?.adapter = tpnDNTListAdapter // 어댑터 세팅
        // 리스트 세팅

        for(item in 0 until train.carriage.size)
            tpnDNTListAdapter?.addItem(train.carriage[item]!!)
        // 임시 방편으로 추가한 리스트들

        tpnDNTListAdapter?.notifyDataSetChanged() // 리스트 업데이트
        /* onCreate() writer code end */
    }

    override fun onPause() {
        super.onPause()
        /* onPause() writer code start */
        finish() // 나가면 액티비티 바로 종료
        /* onPause() writer code end */
    }

    override fun onStop() {
        super.onStop()
        /* onStop() writer code start */
        //Log.i(TAG,"call by onStop function") // for debug
        /* onStop() writer code end */
    }
}