package com.example.desafio1dsm

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.Toast

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


        btnCalcular.setOnClickListener {

            val nombre = etNombre.text.toString()
            val notas = listOf(
                etNota1.text.toString().toDoubleOrNull(),
                etNota2.text.toString().toDoubleOrNull(),
                etNota3.text.toString().toDoubleOrNull(),
                etNota4.text.toString().toDoubleOrNull(),
                etNota5.text.toString().toDoubleOrNull()
            )

            // Verificar si todas las notas son válidas
            if (notas.any { it == null || it < 0 || it > 10 }) {
                Toast.makeText(this, "Las notas deben estar entre 0 y 10.", Toast.LENGTH_SHORT).show()
            } else {
                // Calculos para obtener el promedio
                val promedio = (notas[0]!! * 0.15) + (notas[1]!! * 0.15) + (notas[2]!! * 0.20) + (notas[3]!! * 0.25) + (notas[4]!! * 0.25)

                // Aproximar a un solo decimal
                val promedioRedondeado = String.format("%.1f", promedio)

                val estado = if (promedio >= 6) "Aprobado" else "Reprobado"

                val mensaje = "Hola $nombre\nTu promedio es $promedioRedondeado\nEstado: $estado"

                MaterialAlertDialogBuilder(this)
                    .setTitle("Resultado")
                    .setMessage(mensaje)
                    .setPositiveButton("Cerrar") { dialog, _ ->
                        // Limpiar los inputs al presionar "Cerrar"
                        etNombre.setText("")
                        etNota1.setText("")
                        etNota2.setText("")
                        etNota3.setText("")
                        etNota4.setText("")
                        etNota5.setText("")
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
