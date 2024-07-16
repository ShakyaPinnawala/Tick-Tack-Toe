package com.example.ticktacktoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ticktacktoe.databinding.ActivityHome2Binding
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
// HomeActivity2 represents the main activity for the Tic-Tac-Toe game
class HomeActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()// Enables edge-to-edge display to utilize the entire screen.
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_home2)

            // Sets an OnClickListener on the 'startButton'. When clicked, it navigates to the 'Start' activity.
        val btnPlayGame = findViewById<Button>(R.id.startButton)
        btnPlayGame.setOnClickListener {
            val intent = Intent(this, Start::class.java)// Creates an Intent to switch to the Start activity.
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}