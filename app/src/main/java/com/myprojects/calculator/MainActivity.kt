package com.myprojects.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {
        textView?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        textView?.text = ""
        lastNumeric = false
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            textView?.append((view as Button).text)
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        var tvValue = textView?.text.toString()
        var prefix = ""

        if(lastNumeric) {
            if(tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }

            if(tvValue.contains("-")) {
                try {
                    val splitArr = tvValue.split("-")
                    var opOne = splitArr[0]
                    val opTwo = splitArr[1]

                    if(prefix.isNotEmpty()) {
                        opOne = prefix + opOne
                    }

                    val result = opOne.toDouble() - opTwo.toDouble()
                    textView?.text = result.toString()

                }catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }else if(tvValue.contains("+")) {
                try {
                    val splitArr = tvValue.split("+")
                    var opOne = splitArr[0]
                    val opTwo = splitArr[1]

                    val result = opOne.toDouble() + opTwo.toDouble()
                    textView?.text = result.toString()

                }catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }else if(tvValue.contains("*")) {
                try {
                    val splitArr = tvValue.split("*")
                    var opOne = splitArr[0]
                    val opTwo = splitArr[1]

                    val result = opOne.toDouble() * opTwo.toDouble()
                    textView?.text = result.toString()

                }catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }else if(tvValue.contains("/")) {
                try {
                    val splitArr = tvValue.split("/")
                    var opOne = splitArr[0]
                    val opTwo = splitArr[1]

                    val result = opOne.toDouble() / opTwo.toDouble()
                    textView?.text = result.toString()

                }catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun onOperator(view: View) {
        textView?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                textView?.append((view as Button).text)
                lastNumeric = false
            }
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")) {
            false
        } else {
            if(value.contains("+") ||  value.contains("*") || value.contains("/") || value.contains("-")){
                true
            }else {
                false
            }
        }
    }
}