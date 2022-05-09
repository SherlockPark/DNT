package Layout.activity

import DNTClass.DNTBluetooth
import DNTClass.TrainInfo
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothSPP.OnDataReceivedListener
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.DeviceList
import com.example.dnt.R
import java.lang.NullPointerException

// writer : Yoon Jae Uk
// date : 2022.04.28 ~ ?
// content : 메인 액티비티 코틀린 코드, 'activity_main.xml' 파일과 함께 참고

class MainActivity : AppCompatActivity() {
    /* Layout declaration begin */
    private var mainUniqueNumTV: AppCompatTextView? = null
    private var mainCurrentStationTV: AppCompatTextView? = null
    private var mainNextStationTV: AppCompatTextView? = null
    private var mainTrainPeopleTV: AppCompatTextView? = null
    private var mainStateMessageTV: AppCompatTextView? = null
    private var mainArrowTV: AppCompatTextView? = null
    private var mainLogoIV: AppCompatImageView? = null
    private var mainImageFL: FrameLayout? = null
    private var mainBluetoothBTN: AppCompatButton? = null
    /* Layout declaration end */

    /* Thread code declaration begin */
    inner class AllowThread: Thread(), Runnable{
        val TAG: String = "AllowThread"
        var work: Boolean = true
        var stop: Boolean = false

        override fun run() {
            super.run()
            Log.i(TAG,"start")
            mainArrowTV = findViewById(R.id.main_MoveArrowTV)
            var timerCount: Int = 0
            var allow: String = ""
            while(!stop){
                if(work){
                    when(timerCount){
                        0 -> {
                            allow += "-"
                            ++timerCount
                        }
                        in 1..6 -> {
                            allow += " -"
                            ++timerCount
                        }
                        7 -> {
                            allow += ">"
                            ++timerCount
                        }
                        8 -> {
                            allow = ""
                            timerCount = 0
                        }
                        else -> {
                            allow = ""
                            timerCount = 0
                        }
                    }
                    runOnUiThread(Runnable{
                        mainArrowTV?.text = allow
                    })
                    Thread.sleep(1000)
                } else{
                    yield()
                }
            }
            // while문을 빠져 나가면 스레드 종료
        } // 1초마다 진행 방향 가리키기
        fun threadPause(){ work = false } // 스레드 일시 정지
        fun threadResume() { work = true } // 스레드 다시 시작
        fun threadStop(){ stop = true } // 스레드 종료
    }
    val allowThread: AllowThread = AllowThread()
    /* Thread code declaration end */

    /* About bluetooth code declaration begin */
    private var testBt: DNTBluetooth? = null
    private var bluetoothSPP: BluetoothSPP? = null
    private val deviceChoose: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            Log.i(TAG,"function deviceChoose()")
            val intent = result.data
            if(result.resultCode == RESULT_OK)
                testBt?.connect(intent)
        }
    /* About bluetooth code declaration end */

    /* Custom Object declaration begin */
    private var DBReceiveTrain: Array<TrainInfo?> = arrayOfNulls<TrainInfo>(1)
    private var thisTrain:TrainInfo? = null
    /* Custom Object declaration end */

    /* Primitive type declaration begin */
    private val TAG:String = "MainActivity"
    /* Primitive type declaration end */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* onCreate() writer code begin */
        mainUniqueNumTV = findViewById(R.id.main_UniqueNumTV)
        mainCurrentStationTV = findViewById(R.id.main_CurrentStationTV)
        mainTrainPeopleTV = findViewById(R.id.main_TrainPeopleNumTV)
        mainNextStationTV = findViewById(R.id.main_NextStationTV)
        mainStateMessageTV = findViewById(R.id.main_StateMessageTV)
        mainArrowTV = findViewById(R.id.main_MoveArrowTV)
        mainBluetoothBTN = findViewById(R.id.main_BluetoothBTN)
        mainLogoIV = findViewById(R.id.main_LogoIV)
        mainImageFL = findViewById(R.id.main_ImageFL)
        // 레이아웃 커넥션

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermissions(arrayOf(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_CONNECT
                ), 1
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(
                    Manifest.permission.BLUETOOTH
                ), 1
            )
        }
        // android 12 이상 사용자 승인 코드
        testBt = DNTBluetooth(this) // 블루투스 객체 생성

        /* 이 코드에서 블루투스 장비로부터 데이터 값을 받아와야 한다. */
        DBReceiveTrain[0] = TrainInfo( 1880, R.drawable.ktx_logo, 3,
                                        "천안역","대전역",10)
        thisTrain = DBReceiveTrain[0]
        // 기차 정보 생성
        layoutSetting(thisTrain!!) //레이아웃 세팅
        mainImageFL!!.setOnClickListener(View.OnClickListener { view ->
            val intent: Intent = Intent(applicationContext, TrainPeopleNumActivity::class.java)
            intent.putExtra("info",thisTrain)
            startActivity(intent)
        }) // 이미지뷰 클릭 이벤트
        allowThread.start() // 화살표 스레드 시작
        mainBluetoothBTN?.setOnClickListener(View.OnClickListener { view ->
            if(testBt!!.serviceState == BluetoothState.STATE_CONNECTED){
                testBt!!.disconnect()
            } else {
                val intent: Intent = Intent(applicationContext,DeviceList::class.java)
                deviceChoose.launch(intent)
            }
        })
        /* onCreate() writer code end */
    }

    override fun onStart() {
        super.onStart()
        /* onRestart() writer code begin */
        Log.i(TAG,"onStart")
        /* onRestart() writer code end */
    }

    override fun onResume() {
        super.onResume()
        /* onResume() writer code begin */
        Log.i(TAG,"onResume")
        allowThread.threadResume()
        /* onResume() writer code end */
    }

    override fun onPause() {
        super.onPause()
        /* onPause() writer code begin */
        Log.i(TAG,"onPause")
        allowThread.threadPause() // 화살표 스레드 일시정지
        /* onPause() writer code end */
    }

    override fun onDestroy() {
        super.onDestroy()
        /* onPause() writer code begin */
        Log.i(TAG,"onDestroy")
        allowThread.threadStop() // 앱 종료시, 화살표 스레드 정지한다.
        testBt!!.stopService() // 앱 종료시, 블루투스 정지지
       /* onPause() writer code end */
    }

    /* writer function begin */
    @SuppressLint("DefaultLocale", "ResourceAsColor", "SetTextI18n")
    private fun layoutSetting(curTrain: TrainInfo) {
        mainLogoIV!!.setImageResource(curTrain.TYPE_ID) // 이미지 세팅
        mainUniqueNumTV!!.text = "${curTrain.UNIQUE_NUMBER}호" // 호수 세팅
        mainCurrentStationTV!!.text = curTrain.currentStation // 현재역 세팅
        mainNextStationTV!!.text = curTrain.nextStation // 다음역 세팅

        mainTrainPeopleTV!!.text = curTrain.getNumMessage() // 현재인원수 세팅
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

    fun receiveFromBT(){

    }
    /* writer function end */
}