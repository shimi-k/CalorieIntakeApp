package com.example.calorieintakeapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.collection("foods")
            .whereEqualTo("name", "カレーうどん")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(FoodsService.TAG, "${document.id} => ${document.data}")
                    findViewById<TextView>(R.id.textView).text = document.getLong("calorie").toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(FoodsService.TAG, "Error getting documents: ", exception)
            }
    }
}
