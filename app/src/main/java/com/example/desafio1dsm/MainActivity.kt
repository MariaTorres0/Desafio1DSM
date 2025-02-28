package com.example.desafio1dsm

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPromedio = findViewById<Button>(R.id.btnPromedio)
        val btnDescuento = findViewById<Button>(R.id.btnDescuento)
        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)

        val buttonClickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click)

        btnPromedio.setOnClickListener {
            btnPromedio.startAnimation(buttonClickAnimation)
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }

        btnDescuento.setOnClickListener {
            btnDescuento.startAnimation(buttonClickAnimation)
            val intent = Intent(this, DescuentoActivity::class.java)
            startActivity(intent)
        }

        btnCalculadora.setOnClickListener {
            btnCalculadora.startAnimation(buttonClickAnimation)
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
    }
}
