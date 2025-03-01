package com.example.desafio1dsm

import android.os.Bundle
import android.text.Html
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class DescuentoActivity : AppCompatActivity() {

    private lateinit var edtNombre: EditText
    private lateinit var edtSalario: EditText
    private lateinit var btnCalcular: Button
    private lateinit var txtResultado: TextView
    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descuento)

        edtNombre = findViewById(R.id.edtNombre)
        edtSalario = findViewById(R.id.edtSalario)
        btnCalcular = findViewById(R.id.btnCalcular)
        txtResultado = findViewById(R.id.txtResultado)

        btnCalcular.setOnClickListener {
            calcularDescuentos()
        }
    }
    //Verificar que los datos sean ingresados correctamente
    private fun calcularDescuentos() {
        val nombre = edtNombre.text.toString()
        val salarioTexto = edtSalario.text.toString()

        if (nombre.isEmpty() || salarioTexto.isEmpty()) {
            Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val salario = salarioTexto.toDoubleOrNull()
        if (salario == null) {
            Toast.makeText(this, "Ingrese un salario válido", Toast.LENGTH_SHORT).show()
            return
        }

        //Calculos para obtener descuentos
        val afp = salario * 0.0725
        val isss = salario * 0.03
        val renta = calcularRenta(salario)
        val salarioNeto = salario - afp - isss - renta

        //Mostrar resultados
        val resultado = """
            <b>Nombre:</b> $nombre<br>
            <b>Salario base:</b> $${decimalFormat.format(salario)}<br>
            <b>AFP (7.25%):</b> $${decimalFormat.format(afp)}<br>
            <b>ISSS (3%):</b> $${decimalFormat.format(isss)}<br>
            <b>Renta:</b> $${decimalFormat.format(renta)}<br>
            <b>Salario neto:</b> $${decimalFormat.format(salarioNeto)}
        """.trimIndent()

        txtResultado.text = Html.fromHtml(resultado)
    }

    //Calculos según tramo en renta
    private fun calcularRenta(salario: Double): Double {
        return when {
            salario <= 472 -> 0.0
            salario <= 895.24 -> (salario - 472) * 0.1 + 17.67
            salario <= 2038.10 -> (salario - 895.24) * 0.2 + 60
            else -> (salario - 2038.10) * 0.3 + 288.57
        }
    }
}
