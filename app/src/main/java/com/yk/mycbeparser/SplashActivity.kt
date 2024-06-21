package com.yk.mycbeparser

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    private val permissions = arrayOf(

        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.READ_SMS
    )
    private val permissionsRequestCode = 123
    var tag:String = "splash_activity_log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (checkPermissions()) {
            startMainActivity()
        } else {
            requestPermissions()
        }
    }

    private fun checkPermissions(): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, permissionsRequestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionsRequestCode) {
            if (checkPermissions()) {
                startMainActivity()
            } else {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun startMainActivity() {

        var intent = Intent(this, MainActivity::class.java)
        startIntent(intent)

    }

    private fun startIntent(intent: Intent){

//        startActivity(intent)
//        finish()

        val splashThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1000) // Wait for 1 seconds
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    if(intent != null){
                        startActivity(intent)
                        finish()
                    }else{
                        Log.e(tag, "Intent Null!")
                    }

                }
            }
        }
        splashThread.start();

    }
}