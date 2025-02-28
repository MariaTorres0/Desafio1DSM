package com.example.desafio1dsm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PromedioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota1 = findViewById<EditText>(R.id.etNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)
        val etNota4 = findViewById<EditText>(R.id.etNota4)
        val etNota5 = findViewById<EditText>(R.id.etNota5)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString()
            val notas = listOf(
                etNota1.text.toString().toDouble(),
                etNota2.text.toString().toDouble(),
                etNota3.text.toString().toDouble(),
                etNota4.text.toString().toDouble(),
                etNota5.text.toString().toDouble()
            )

            if (notas.any { it < 0 || it > 10 }) {
                tvResultado.text = "Las notas deben estar entre 0 y 10"
                tvResultado.visibility = TextView.VISIBLE
            } else {
                val promedio = (notas[0] * 0.15) + (notas[1] * 0.15) + (notas[2] * 0.20) + (notas[3] * 0.25) + (notas[4] * 0.25)
                val estado = if (promedio >= 6) "APROBADO" else "REPROBADO"
                tvResultado.text = "$nombre, tu promedio es $promedio\nEstado: $estado"
                tvResultado.visibility = TextView.VISIBLE
            }
        }
    }
}
