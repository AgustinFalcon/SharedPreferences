package com.example.myfirstapp

import android.view.View
import android.widget.AdapterView
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myfirstapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var language: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpinnerData()

        loadPreferences()
        onLoginClick()
    }

    private fun setSpinnerData() {
        val lenguajes = resources.getStringArray(R.array.LanguagesProgramacion)
        val spinner = binding.spinner

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lenguajes)
            spinner.adapter = adapter
        }


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                language = lenguajes[position].toString()
                val pref = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("language", language)
                editor.apply()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }


        }
    }


    private fun loadPreferences() {
        val pref = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE)

        val name = pref.getString("name", null)
        val email = pref.getString("email", null)
        val language = pref.getString("language", null)

        if (name != null && email != null && language != null) {
            goToResultActivity()

        }

    }

    private fun onLoginClick() {
        binding.btnLogin.setOnClickListener() {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (name.isNotBlank() && email.isNotBlank() && language != null) {
                val pref = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("name", name)
                editor.putString("email", email)

                editor.apply()
                loadPreferences()

            } else {
                Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
            }



        }
    }

    private fun goToResultActivity(user: String="", email: String="", language: String="") {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("email", email)
        intent.putExtra("language", language)
        startActivity(intent)
    }

    companion object {
        const val CREDENTIALS = "Credenciales"
    }


}