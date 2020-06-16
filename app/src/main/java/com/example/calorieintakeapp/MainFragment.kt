package com.example.calorieintakeapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    val db = FirebaseFirestore.getInstance()

//    fun setUpSearchButtonUI() {
//        findViewById<Button>(R.id.searchButton).setOnClickListener {
//            getFoods(findViewById<EditText>(R.id.text).text.toString())
//        }
//    }

    fun getFoods(name: String) {
        db.collection("foods")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { documents ->
                when (documents.size()) {
                    0 -> {
                        // Toast.makeText(this, "料理名が存在しません", Toast.LENGTH_SHORT).show()
                    }

                    1 -> {
//                        documents.forEach {
//                            val intent = Intent(this, SubActivity::class.java).apply {
//                                putExtra("EXTRA_CALORIE", it.getLong("calorie").toString())
//                            }
//                            startActivityForResult(intent,1)
//                        }
                    }
                    else -> {
                        // Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(FoodsService.TAG, "Error getting documents: ", exception)
                // Toast.makeText(this, "接続失敗", Toast.LENGTH_SHORT).show()
            }
    }


}
