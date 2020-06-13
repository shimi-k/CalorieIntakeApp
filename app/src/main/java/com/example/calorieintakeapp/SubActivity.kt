package com.example.calorieintakeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val calorie = intent.getStringExtra("EXTRA_CALORIE")!!
        setUpColorieUI(calorie)
    }
    fun setUpColorieUI(calorie: String){
        findViewById<TextView>(R.id.calorie).text = calorie+"kcal"
    }
}
