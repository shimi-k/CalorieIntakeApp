package com.example.calorieintakeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class MainFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchButtonUI(view)
        setupFragmentFirstButton(view)
    }

    //    private fun setUpFlagmentSubFirst(view: View){
//        view.findViewById<Button>(R.id.firstButton).setOnClickListener{
//            replace
//        }
//    }
    private fun setupSearchButtonUI(view: View) {
        view.findViewById<Button>(R.id.searchButton).setOnClickListener {
            getFoods(view.findViewById<EditText>(R.id.text).text.toString())
        }
    }

    private fun setupFragmentFirstButton(view: View) {
        view.findViewById<Button>(R.id.firstButton).setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(android.R.id.content, FragmentSubFirst())
                .addToBackStack(FragmentSubFirst::class.java.simpleName)
                .commitAllowingStateLoss()
        }
    }

    private fun getFoods(name: String) {
        db.collection("foods")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener {
                when (it.count()) {
                    0 -> {
                        Toast.makeText(context, "料理名が存在しません", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        val intent = Intent(context, SubActivity::class.java).apply {
                            putExtra("EXTRA_CALORIE", it.first().getLong("calorie").toString())
                        }
                        startActivityForResult(intent, 1)
                    }
                    else -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(FoodsService.TAG, "Error getting documents: ", exception)
                // Toast.makeText(this, "接続失敗", Toast.LENGTH_SHORT).show()
            }
    }
}
