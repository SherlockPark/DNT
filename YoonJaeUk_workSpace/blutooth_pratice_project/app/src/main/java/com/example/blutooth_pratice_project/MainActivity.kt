package com.example.blutooth_pratice_project

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView


class MainActivity : AppCompatActivity() {

    private val receiver = object : BroadcastReceiver() {

        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name
                    val deviceHardwareAddress = device?.address // MAC address
                }
            }
        }
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainBTReceiveTV: AppCompatTextView = findViewById(R.id.main_BTReceiveTV)
        val mainSearchBTN: AppCompatButton = findViewById(R.id.main_SearchBTN)
        mainBTReceiveTV.text = ""
        // 레이아웃 커넥팅

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_CONNECT
                ),
                1
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.BLUETOOTH
                ),
                1
            )
        } // android 12 이상 사용자 승인 코드


        val bluetoothManager =
            baseContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "블루투스를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show()
        } // 블루투스가 없으면 실행하는 코드

        if (bluetoothAdapter?.isEnabled == false) {
            Toast.makeText(this, "블루투스가 꺼져 있습니다.", Toast.LENGTH_SHORT).show()
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            //startActivityForResult(enableBtIntent, 1) // deprecated
            val bluetoothOn: ActivityResultLauncher<Intent> = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()){ result ->
                    if(result.resultCode == RESULT_OK){ }
            }
            bluetoothOn.launch(enableBtIntent)
        } // 블루투스가 꺼져있으면 실행하는 코드

        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        mainBTReceiveTV.text = "<페어링된 기기>\n"
        pairedDevices?.forEach { device ->
            var deviceInfo = ""
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            deviceInfo = "$deviceName \n $deviceHardwareAddress"
            mainBTReceiveTV.text = mainBTReceiveTV.text.toString() + deviceInfo
            mainBTReceiveTV.text = "${mainBTReceiveTV.text}\n\n"
        } // 페어링된 기기를 출력

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}