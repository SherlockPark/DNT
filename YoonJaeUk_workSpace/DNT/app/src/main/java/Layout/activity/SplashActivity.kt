package Layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.dnt.R

// writer : Yoon Jae Uk
// date : 2022.05.01
// content : DNT 어플리케이션 스플래시 화면 코틀린 코드, 'activity_splash.xml' 파일과 함께 참고

class SplashActivity : AppCompatActivity() {
    /* Other Object declaration begin */
    private val runnable: Runnable = Runnable {
        val intent: Intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    } // 다음 페이지로 넘어가는 Runnable 인터페이스 변수
    /* Other Object declaration end */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /* onCreate() writer code begin */
        val handler: Handler = Handler(Looper.getMainLooper()) // 핸들러 선언
        handler.postDelayed(runnable,3000) // 4초후에 넘어간다.
        /* onCreate() writer code end */
    }
}