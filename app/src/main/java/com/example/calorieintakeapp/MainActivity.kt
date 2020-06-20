package com.example.calorieintakeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val TAG = MainFragment::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        Log.d(TAG, "in onCreate")


        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, MainFragment())
            .addToBackStack(MainFragment::class.java.simpleName)
            .commitAllowingStateLoss()

        Log.d(TAG, "out onCreate")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println()
    }

}
