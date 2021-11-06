package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric = false
    var lastdot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_clr.setOnClickListener {
            input.setText("")
            lastNumeric = false
            lastdot = false
        }

        btn_result.setOnClickListener {
                onequals(btn_result)
        }
    }

    fun Clickview(view: View) {
        input.append((view as Button).text)
        lastNumeric = true
    }

    fun ondecimal(view: View) {
        if (lastNumeric && !lastdot) {
            input.append(".")
            lastdot = true
        }
    }

    fun onequals(view: View) {

        if (lastNumeric) {

            try {

                var tvinput = input.text.toString()
                var prefix = ""

                try {
                    if (tvinput.startsWith("-")) {
                        prefix = "-"
                        tvinput.substring(1)
                    }

                    if (tvinput.contains("-")) {

                        var splitvalue = tvinput.split("-")
                        var val1 = splitvalue[0]
                        var val2 = splitvalue[1]

                        if (!prefix.isEmpty()) {
                            val1 = prefix + val1
                        }

                        input.text = checkingforZero((val1.toDouble() - val2.toDouble()).toString())
                    }

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
                try {
                    if (tvinput.contains("+")) {

                        var splitvalue = tvinput.split("+")
                        var val1 = splitvalue[0]
                        var val2 = splitvalue[1]

                        input.text = checkingforZero((val1.toDouble() + val2.toDouble()).toString())
                    }

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
                try {
                    if (tvinput.contains("/")) {

                        var splitvalue = tvinput.split("/")
                        var val1 = splitvalue[0]
                        var val2 = splitvalue[1]

                        input.text = checkingforZero((val1.toDouble() / val2.toDouble()).toString())
                    }

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
                try {
                    if (tvinput.contains("*")) {

                        var splitvalue = tvinput.split("*")
                        var val1 = splitvalue[0]
                        var val2 = splitvalue[1]

                        input.text = checkingforZero((val1.toDouble() * val2.toDouble()).toString())


                    }

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }


    }


    fun operator(view: View) {


        if (lastNumeric && !isOperatorAdded(input.text.toString())) {
            input.append((view as Button).text)
            lastNumeric = false
            lastdot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
    }

    private fun checkingforZero(result:String):String{
        var num = result
        if (result.contains(".0"))
            num = result.substring(0,result.length-2)
            return  num
    }
}