package Layout.activity

import DNTClass.TrainInfo
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.dnt.R

// writer : Yoon Jae Uk
// date : 2022.04.28 ~ ?
// content : 메인 액티비티 코틀린 코드, 'activity_main.xml' 파일과 함께 참고

class MainActivity : AppCompatActivity() {
    /* Layout declaration begin */
    private var mainUniqueNumTV: AppCompatTextView? = null
    private var mainCurrentStationTV: AppCompatTextView? = null
    private var mainTrainPeopleTV: AppCompatTextView? = null
    private var mainStateMessageTV: AppCompatTextView? = null
    private var mainLogoIV: AppCompatImageView? = null
    private var mainImageFL: FrameLayout? = null
    /* Layout declaration end */

    /* Custom Object declaration begin */
    private var train:TrainInfo? = null
    /* Custom Object declaration end */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* onCreate() writer code begin */
        mainUniqueNumTV = findViewById(R.id.main_UniqueNumTV)
        mainCurrentStationTV = findViewById(R.id.main_CurrentStationTV)
        mainTrainPeopleTV = findViewById(R.id.main_TrainPeopleNumTV)
        mainStateMessageTV = findViewById(R.id.main_StateMessageTV)
        mainLogoIV = findViewById(R.id.main_LogoIV)
        mainImageFL = findViewById(R.id.main_ImageFL)
        // 레이아웃 커넥션

        /*
        이 코드에서 블루투스 장비로부터 데이터 값을 받아와야 한다.
        */
        train = TrainInfo( 1880, R.drawable.ktx_logo, 3, "천안역",10)
        // 기차 정보 생성

        layoutSetting(train!!) //레이아웃 세팅
        mainImageFL!!.setOnClickListener(View.OnClickListener { view ->
            val intent: Intent = Intent(applicationContext, TrainPeopleNumActivity::class.java)
            intent.putExtra("info",train)
            startActivity(intent)
        }) // 이미지뷰 클릭 이벤트
        /* onCreate() writer code end */
    }

    /* writer function begin */
    @SuppressLint("DefaultLocale", "ResourceAsColor")
    private fun layoutSetting(curTrain: TrainInfo) {
        mainLogoIV!!.setImageResource(curTrain.TYPE_ID)
        mainUniqueNumTV!!.text = curTrain.UNIQUE_NUMBER.toString()
        mainCurrentStationTV!!.text = curTrain.currentStation
        mainTrainPeopleTV!!.text = curTrain.getNumMessage()
        if (curTrain.getResNumTotal() < curTrain.getCurNumTotal()) {
            mainStateMessageTV!!.setTextColor(ContextCompat.getColor(applicationContext,R.color.red))
            mainStateMessageTV!!.setText(R.string.train_error_message)
        } // 현재 인원수가 예약된 인원수보다 많을 때 문제 메세지를 출력하고 색상은 빨강으로 출력합니다.
        else if(curTrain.getResNumTotal() == 0){
            mainStateMessageTV!!.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow))
            mainStateMessageTV!!.setText(R.string.train_nullException_message)
        } // 예약 인원수가 없을 때 없다는 메세지를 출력하고 색상은 노랑으로 출력합니다.
        else {
            mainStateMessageTV!!.setTextColor(ContextCompat.getColor(applicationContext,R.color.blue))
            mainStateMessageTV!!.setText(R.string.train_no_error_message)
        } // 현재 인원수가 정상일 때 정상 메세지를 출력하고 색상은 파랑으로 출력합니다.
    } // 전체적인 레이아웃 값 세팅
    /* writer function end */
}