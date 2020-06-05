package com.example.first_coroutine

import android.graphics.DiscretePathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = async { doNetworkCall1() }
                val answer2 = async { doNetworkCall2() }

                Log.d(TAG, "Answer1 is ${answer1.await()}")
                Log.d(TAG, "Answer2 is ${answer2.await()}")
            }

            Log.d(TAG, "Request took $time")

        }
    }

    private suspend fun doNetworkCall1(): String {
        delay(3000)
        return "Answer from doNetworkCall 1"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000)
        return "Answer from doNetworkCall 2"
    }
}


