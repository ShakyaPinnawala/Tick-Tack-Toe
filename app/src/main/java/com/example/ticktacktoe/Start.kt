package com.example.ticktacktoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Start : AppCompatActivity() {

    private lateinit var playGameBtn  : Button
    private lateinit var edtPlayer1Name : EditText
    private lateinit var edtPlayer2Name :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_start)
// Find and initialize EditText fields for entering player names
        edtPlayer1Name = findViewById(R.id.player1)// Input field for Player 1's name
        edtPlayer2Name = findViewById(R.id.player2)// Input field for Player 2's name
        // Find and initialize the play game button, which will start the main game activity
        playGameBtn = findViewById(R.id.playBtn)

        // Set an OnClickListener on the play game button
        playGameBtn.setOnClickListener {

            // Create an intent to start the MainActivity, passing the names of the two players as extras.
            startActivity(Intent(this,MainActivity::class.java)
                .putExtra("player1",edtPlayer1Name.text.toString())// Adds Player 1's name to the intent.
                .putExtra("player2",edtPlayer2Name.text.toString()))// Adds Player 2's name to the intent.
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}