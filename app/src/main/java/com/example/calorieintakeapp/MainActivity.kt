package com.example.calorieintakeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    fun setUpSearchButtonUI() {
        findViewById<Button>(R.id.searchButton).setOnClickListener {
            getFoods(findViewById<EditText>(R.id.text).text.toString())
        }

    }

    fun getFoods(name: String) {
        db.collection("foods")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { documents ->
                when (documents.size()) {
                    0 -> {
                        Toast.makeText(this, "料理名が存在しません", Toast.LENGTH_SHORT).show()

                    }

                    1 -> {
                        documents.forEach {
                            val intent = Intent(this, SubActivity::class.java).apply {
                                putExtra("EXTRA_CALORIE", it.getLong("calorie").toString())
                            }
                            startActivity(intent)
                        }
                    }
                    else -> {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(FoodsService.TAG, "Error getting documents: ", exception)
                Toast.makeText(this, "接続失敗", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSearchButtonUI()

    }
}
