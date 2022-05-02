package com.example.dnt.layout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.dnt.R

// writer : Yoon Jae Uk
// date : 2022.04.28 ~ ?
// content : 메인 액티비티 자바 코드, 'activity_main.xml' 파일과 함께 참고

class MainActivity : AppCompatActivity() {
    /* Layout declaration begin */
    private var mainUniqueNumTV: AppCompatTextView? = null
    private var mainCurrentStationTV: AppCompatTextView? = null
    private var mainTrainPeopleTV: AppCompatTextView? = null
    private var mainStateMessageTV: AppCompatTextView? = null
    private var mainLogoIV: AppCompatImageView? = null
    private var mainImageFL: FrameLayout? = null
    /* Layout declaration end */

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

        // 레이아웃 커넥션
        layoutSetting(R.drawable.ktx_logo, 1880, "천안역", 10, 20, 30)
        mainImageFL!!.setOnClickListener(View.OnClickListener { view ->
            val intent: Intent = Intent(applicationContext,TrainPeopleNumActivity::class.java)
            startActivity(intent)
        })
        //레이아웃 세팅
        /* onCreate() writer code end */
    }

    /* writer function begin */
    @SuppressLint("DefaultLocale")
    private fun layoutSetting( typeId: Int, unique_number: Int, cur_station: String, cur_num: Int,
                                res_num: Int, MAX_NUM: Int) {
        mainLogoIV!!.setImageResource(typeId)
        mainUniqueNumTV!!.text = unique_number.toString()
        mainCurrentStationTV!!.text = cur_station
        mainTrainPeopleTV!!.text = String.format("%d/%d/%d", cur_num, res_num, MAX_NUM)
        if (res_num < cur_num)
            mainStateMessageTV!!.setText(R.string.error_message)
        else
            mainStateMessageTV!!.setText(R.string.no_error_message)
    }
    /* writer function end */
}