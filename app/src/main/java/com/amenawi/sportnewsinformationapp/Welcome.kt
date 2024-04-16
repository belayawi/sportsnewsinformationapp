package com.amenawi.sportnewsinformationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amenawi.sportnewsinformationapp.fragments.LoginFragment
import com.google.firebase.auth.FirebaseAuth

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        if (FirebaseAuth.getInstance().currentUser == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        } else {
            val intent:Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}