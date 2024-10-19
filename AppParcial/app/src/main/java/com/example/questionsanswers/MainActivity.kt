package com.example.questionsanswers

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display (full screen layout that draws behind system bars)
        enableEdgeToEdge()

        // Set the content view to the activity's layout
        setContentView(R.layout.activity_main)

        // Adjust the padding of the root view to accommodate system bars (like the status bar or navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Get system bar insets
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Set padding to prevent content from being obstructed
            insets // Return the insets object after handling
        }
    }
}
