package DNTClass

import android.content.Context
import android.util.Log
import android.widget.Toast
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState

// writer : Yoon Jae Uk
// date : 2022.05.09 ~ ?
// content : BluetoothSPP 라이브러리 기반으로 작성된 클래스 블루투스 관련 이벤트 코드를 작성한 파일이다.

class DNTBluetooth(context: Context): BluetoothSPP(context){
    /* class, member declaration begin */
    val TAG: String = "DNTBluetooth"
    var receiveTrain: TrainInfo? = null // 블루투스로 통해 받을 변수
    private val receiveTest: OnDataReceivedListener = OnDataReceivedListener(){ data, message ->
        Log.i(TAG, "receive data!!")
        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
    } // 테스트 용
    /* class, member declaration end */

    /* constructor(init) declaration begin */
    init{
        Log.i(TAG,"Create DNTBluetooth")
        super.setupService()
        super.startService(BluetoothState.DEVICE_OTHER)
        super.setOnDataReceivedListener(receiveTest) // 데이터 받았을 때 수행하는 코드

        super.setBluetoothConnectionListener(object: BluetoothSPP.BluetoothConnectionListener{
            override fun onDeviceConnected(name: String?, address: String?) {
                Toast.makeText(context, "Connected to $name\n$address", Toast.LENGTH_SHORT).show()
                Log.i(TAG,"onDeviceConnected : connected?")
            } // 연결 성공 했을 때

            override fun onDeviceDisconnected() {
                Toast.makeText(context, "Connection lost", Toast.LENGTH_SHORT).show()
            } // 연결이 끊어졌을 때

            override fun onDeviceConnectionFailed() {
                Toast.makeText(context, "Unable to connect", Toast.LENGTH_SHORT).show()
            } // 연결 실패 했을 때
        }) // 블루투스 연결 관련 리스너
    } // DNTBluetooth 클래스 초기화
    /* basic constructor(init) declaration end */

    /* class method declaration begin */

    /* class method declaration end */
}