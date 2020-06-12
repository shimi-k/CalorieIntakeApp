package com.example.calorieintakeapp

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FoodsService {
    val db = FirebaseFirestore.getInstance()

    fun findFoodsByName(name: String) :Long{
        var calorie = 0L
        db.collection("foods")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    calorie = document.getLong("calorie")!!
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return calorie
    }

    companion object {
        val TAG = FoodsService::class.java.name
    }

    fun test(): Long {
        return 0L
    }

}