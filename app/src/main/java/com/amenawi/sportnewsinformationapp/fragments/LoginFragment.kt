package com.amenawi.sportnewsinformationapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amenawi.sportnewsinformationapp.MainActivity
import com.amenawi.sportnewsinformationapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.email)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val goToRegisterButton = view.findViewById<Button>(R.id.goToRegisterButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            activity?.let {
                                it.startActivity(Intent(it, MainActivity::class.java))
                                it.finish()
                            }
                        } else {
                            Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        goToRegisterButton.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, RegistrationFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }
}
