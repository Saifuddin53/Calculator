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