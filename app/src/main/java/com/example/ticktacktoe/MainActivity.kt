package com.example.ticktacktoe


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ticktacktoe.databinding.ActivityMainBinding
import android.media.MediaPlayer
class MainActivity : AppCompatActivity() {

    private lateinit var textusername1 :TextView
    private lateinit var textusername2 :TextView
    private lateinit var mediaPlayer: MediaPlayer // Declare a MediaPlayer instance
    private lateinit var binding: ActivityMainBinding
    private var currentPlayer = "X" // Player X starts first
    private val board = Array(3) { arrayOfNulls<String>(3) } // Tic-Tac-Toe board
    private var playerXWins = 0
    private var playerOWins = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)  // Inflate the view binding
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.music) // Initialize MediaPlayer
        mediaPlayer.isLooping = true // Loop the music
        mediaPlayer.start()
        setupGameBoard()  // Initialize the game board
        setupResetButton()  // Initialize the reset button

        textusername1 =findViewById(R.id.Tv_Player1) //player names
        textusername2 =findViewById(R.id.Tv_Player2)

        val userName1 =intent.getStringExtra("player1")
        val userName2 =intent.getStringExtra("player2")

        textusername1.text ="Player X is " +userName1; //getting the names of the players
        textusername2.text ="Player O is " +userName2;

    }
    override fun onPause() {
        super.onPause()
        mediaPlayer.pause() // Pause the music when the activity is paused
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start() // Resume the music when the activity is resumed
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Release the MediaPlayer resource
    }



    private fun setupGameBoard() { //buttons of the game
        val buttons = listOf(
            binding.button00,
            binding.button01,
            binding.button02,
            binding.button10,
            binding.button11,
            binding.button12,
            binding.button20,
            binding.button21,
            binding.button22
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                onPlayerMove(button)  // Handle player moves
            }
        }
    }

    private fun onPlayerMove(button: Button) {
        if (button.text.isNotBlank()) {
            return  // If the button is already filled, do nothing
        }

        button.text = currentPlayer  // Set the player's move
        val position = button.resources.getResourceEntryName(button.id)  // Get button ID
        val row = position[6].digitToInt()  // Extract row from ID
        val column = position[7].digitToInt()  // Extract column from ID

        board[row][column] = currentPlayer  // Update the board with the move

        if (checkWin(currentPlayer)) {
            if (currentPlayer == "X") {
                playerXWins++
            } else {
                playerOWins++
            }
            binding.gameStatus.text = "Player $currentPlayer wins! Total wins: Player X - $playerXWins, Player O - $playerOWins"
            disableGameBoard() // Game over, disable the board
        } else if (isBoardFull()) {
            binding.gameStatus.text = "It's a draw!"
        } else {
            // Switch to the next player
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            binding.gameStatus.text = "Player $currentPlayer's turn"
        }
    }


    private fun checkWin(player: String): Boolean {
        // Check rows
        for (i in 0..2) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true
            }
        }

        // Check columns
        for (i in 0..2) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        ) {
            return true
        }

        return false
    }
    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it != null } }  // Check if the board is full
    }

    private fun disableGameBoard() {
        listOf(
            binding.button00,
            binding.button01,
            binding.button02,
            binding.button10,
            binding.button11,
            binding.button12,
            binding.button20,
            binding.button21,
            binding.button22
        ).forEach { it.isEnabled = false }  // Disable all buttons on the board
    }

    private fun setupResetButton() {
        binding.resetButton.setOnClickListener {
            resetGame()  // Resets the game board
        }
    }

    private fun resetGame() {
        listOf(
            binding.button00,
            binding.button01,
            binding.button02,
            binding.button10,
            binding.button11,
            binding.button12,
            binding.button20,
            binding.button21,
            binding.button22
        ).forEach {
            it.text = ""
            it.isEnabled = true
        }

        // Clear the game board array and reset game status
        for (i in 0..2) {
            board[i] = arrayOfNulls<String>(3)
        }

        currentPlayer = "X" // Reset to Player X
        binding.gameStatus.text = "Player X's turn" // Reset game status
    }
}

