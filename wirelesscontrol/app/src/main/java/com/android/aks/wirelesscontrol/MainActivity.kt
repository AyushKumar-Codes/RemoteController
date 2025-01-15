package com.android.aks.wirelesscontrol

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.OutputStream
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var socket: Socket? = null
    private var outputStream: OutputStream? = null
    private var isConnected = false
    private var lastX = 0f
    private var lastY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ipAddress = findViewById<EditText>(R.id.ipAddress)
        val portNumber = findViewById<EditText>(R.id.portNumber)
        val connectButton = findViewById<Button>(R.id.connectButton)
        val touchpad = findViewById<View>(R.id.touchpad)
        val keyboardInput = findViewById<EditText>(R.id.keyboardInput)
        val sendTextButton = findViewById<Button>(R.id.sendTextButton)
        val enterbutton = findViewById<Button>(R.id.Enter)
        val bkspbutton = findViewById<Button>(R.id.backspace)

        connectButton.setOnClickListener {
            val ip = ipAddress.text.toString()
            val port = portNumber.text.toString().toIntOrNull()

            if (ip.isNotEmpty() && port != null) {
                thread {
                    try {
                        socket = Socket(ip, port)
                        outputStream = socket!!.getOutputStream()
                        isConnected = true
                        runOnUiThread {
                            Toast.makeText(this, "Connected to server", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Enter valid IP and port", Toast.LENGTH_SHORT).show()
            }
        }


//        touchpad.setOnTouchListener { _, event ->
//            if (isConnected) {
//                when (event.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        lastX = event.x
//                        lastY = event.y
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                        val dx = event.x - lastX
//                        val dy = event.y - lastY
//                        lastX = event.x
//                        lastY = event.y
//                        thread {
//                            try {
//                                outputStream?.write("MOVE|$dx|$dy\n".toByteArray())
//                            } catch (e: Exception) {
//                                runOnUiThread {
//                                    Toast.makeText(this, "Connection lost", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            true
//        }



        enterbutton.setOnClickListener {
            if (isConnected) {
                thread {
                    try {
                        outputStream?.write("ENTER\n".toByteArray())
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Connection lost", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Not connected to server", Toast.LENGTH_SHORT).show()
            }
        }

        bkspbutton.setOnClickListener {
            if (isConnected) {
                thread {
                    try {
                        outputStream?.write("BKSP\n".toByteArray())
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Connection lost", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Not connected to server", Toast.LENGTH_SHORT).show()
            }
        }


        sendTextButton.setOnClickListener {
            val text = keyboardInput.text.toString()
            if (isConnected && text.isNotEmpty()) {
                thread {
                    try {
                        outputStream?.write("TEXT|$text\n".toByteArray())
                        runOnUiThread {
                            // Clear the input field on the main thread
                            keyboardInput.setText("")
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Connection lost", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket?.close()
    }
}
