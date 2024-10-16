package com.example.happybirthday

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentNumber: String = ""
    private var previousNumber: String = ""
    private var operation: String? = null
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Khởi tạo các nút
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEqual: Button = findViewById(R.id.buttonEqual)
        val buttonC: Button = findViewById(R.id.buttonC)
        val buttonCE: Button = findViewById(R.id.buttonCE)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)

        // Gán sự kiện cho các nút số
        val numberClickListener = { number: String ->
            if (isNewOperation) {
                currentNumber = number
                isNewOperation = false
            } else {
                currentNumber += number
            }
            tvResult.text = currentNumber
        }

        button0.setOnClickListener { numberClickListener("0") }
        button1.setOnClickListener { numberClickListener("1") }
        button2.setOnClickListener { numberClickListener("2") }
        button3.setOnClickListener { numberClickListener("3") }
        button4.setOnClickListener { numberClickListener("4") }
        button5.setOnClickListener { numberClickListener("5") }
        button6.setOnClickListener { numberClickListener("6") }
        button7.setOnClickListener { numberClickListener("7") }
        button8.setOnClickListener { numberClickListener("8") }
        button9.setOnClickListener { numberClickListener("9") }

        // Gán sự kiện cho các nút phép toán
        buttonAdd.setOnClickListener { setOperation("+") }
        buttonSubtract.setOnClickListener { setOperation("-") }
        buttonMultiply.setOnClickListener { setOperation("*") }
        buttonDivide.setOnClickListener { setOperation("/") }

        buttonEqual.setOnClickListener { calculate() }

        // Gán sự kiện cho nút xóa
        buttonC.setOnClickListener {
            currentNumber = ""
            previousNumber = ""
            operation = null
            tvResult.text = "0"
        }

        buttonCE.setOnClickListener {
            currentNumber = ""
            tvResult.text = "0"
        }

        // Gán sự kiện cho nút dấu thập phân
        buttonDecimal.setOnClickListener {
            if (!currentNumber.contains(".")) {
                currentNumber += "."
                tvResult.text = currentNumber
            }
        }
    }

    private fun setOperation(op: String) {
        if (currentNumber.isNotEmpty()) {
            previousNumber = currentNumber
            currentNumber = ""
            operation = op
            isNewOperation = true
        }
    }

    private fun calculate() {
        if (operation != null && previousNumber.isNotEmpty() && currentNumber.isNotEmpty()) {
            val result = when (operation) {
                "+" -> previousNumber.toDouble() + currentNumber.toDouble()
                "-" -> previousNumber.toDouble() - currentNumber.toDouble()
                "*" -> previousNumber.toDouble() * currentNumber.toDouble()
                "/" -> previousNumber.toDouble() / currentNumber.toDouble()
                else -> 0.0
            }
            tvResult.text = result.toString()
            currentNumber = result.toString()
            operation = null
            isNewOperation = true
        }
    }
}
