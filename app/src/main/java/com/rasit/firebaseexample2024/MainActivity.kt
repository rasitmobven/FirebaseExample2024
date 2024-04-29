package com.rasit.firebaseexample2024

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.rasit.firebaseexample2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.apply {
            button.setOnClickListener{
                val userName = nameEditText.text.toString()
                val userSurname = surnameEditText.text.toString()
                val userAge = ageEditText.text.toString().toInt()

                val user = User(
                    name = userName,
                    surname = userSurname,
                    age = userAge
                )

                addUser(user)

            }
        }

    }

    private fun addUser(user: User){
        db.collection("Users").add(user)
            .addOnSuccessListener { documentReference ->
            Log.d("SuccesFirebase", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("FailFirebase", "Error adding document", e)
            }
    }
}