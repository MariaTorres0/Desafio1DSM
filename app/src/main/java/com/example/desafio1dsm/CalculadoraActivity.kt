package com.example.desafio1dsm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonExponent: Button
    private lateinit var buttonSqrt: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonExponent = findViewById(R.id.buttonExponent)
        buttonSqrt = findViewById(R.id.buttonSqrt)
        textViewResult = findViewById(R.id.textViewResult)

        buttonAdd.setOnClickListener { calculate(Operation.ADD) }
        buttonSubtract.setOnClickListener { calculate(Operation.SUBTRACT) }
        buttonMultiply.setOnClickListener { calculate(Operation.MULTIPLY) }
        buttonDivide.setOnClickListener { calculate(Operation.DIVIDE) }
        buttonExponent.setOnClickListener { calculate(Operation.EXPONENT) }
        buttonSqrt.setOnClickListener { calculateSqrt() }
    }

    private fun calculate(operation: Operation) {
        val input1Text = input1.text.toString()
        val input2Text = input2.text.toString()

        if (input1Text.isEmpty() || input2Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = input1Text.toDouble()
        val num2 = input2Text.toDouble()
        var result = 0.0

        when (operation) {
            Operation.ADD -> result = num1 + num2
            Operation.SUBTRACT -> result = num1 - num2
            Operation.MULTIPLY -> result = num1 * num2
            Operation.DIVIDE -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    return
                }
                result = num1 / num2
            }
            Operation.EXPONENT -> result = Math.pow(num1, num2)
        }

        textViewResult.text = getString(R.string.result) + String.format("%.2f", result)
    }

    private fun calculateSqrt() {
        val input1Text = input1.text.toString()
        val input2Text = input2.text.toString()

        if (input1Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un número", Toast.LENGTH_SHORT).show()
            return
        }

        if (input2Text.isNotEmpty()) {
            Toast.makeText(this, "La operación raíz cuadrada solo necesita el primer valor", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = input1Text.toDouble()
        if (num1 < 0) {
            Toast.makeText(this, "No se puede calcular la raíz cuadrada de un número negativo", Toast.LENGTH_SHORT).show()
            return
        }

        val result = Math.sqrt(num1)
        textViewResult.text = getString(R.string.result) + String.format("%.2f", result)
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, EXPONENT
    }
}
