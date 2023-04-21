package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myfirstapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val pref = getSharedPreferences(MainActivity.CREDENTIALS, Context.MODE_PRIVATE)

        // data from intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val language = intent.getStringExtra("language")


        binding.tvName.text = "Hola, ${pref.getString("name", null)}" // (key, valor por defecto)
        binding.tvEmail.text = "Contacto: ${pref.getString("email", null)}"
        binding.tvLanguage.text = "Usando: ${pref.getString("language", null)}"


        binding.btnSalir.setOnClickListener {
            // limpiamos las preferencias
            val editor = pref.edit()
            editor.clear().apply()
            finish() // cierra la activity
        }

    }

}