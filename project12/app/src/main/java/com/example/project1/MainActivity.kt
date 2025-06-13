package com.example.project1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var quoteText: TextView
    private lateinit var refreshBtn: Button
    private lateinit var shareBtn: Button
    private lateinit var favBtn: Button

    private val quotes = arrayOf(
        "The biggest adventure you can take is to live the life of your dreams.",
        "Four wheels move the body two wheels move the soul.",
        "Life is short, buy the motorcycle, have a ride, live your dreams.",
        "You don't stop riding when you get old you get old when you stop riding.",
        "Riding a motorcycle is like flying.",
    )

    private var currentQuote: String = ""

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign views to class variables (don't redeclare with 'val')
        quoteText = findViewById(R.id.quoteText)
        refreshBtn = findViewById(R.id.refreshBtn)
        shareBtn = findViewById(R.id.shareBtn)
        favBtn = findViewById(R.id.favBtn)

        sharedPref = getSharedPreferences("QuotesApp", MODE_PRIVATE)

        showRandomQuote()

        refreshBtn.setOnClickListener {
            showRandomQuote()
        }

        shareBtn.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, currentQuote)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share quote via"))
        }

        favBtn.setOnClickListener {
            sharedPref.edit().putString("favorite_quote", currentQuote).apply()
            Toast.makeText(this, "Saved to Favorites!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRandomQuote() {
        val index = Random.nextInt(quotes.size)
        currentQuote = quotes[index]
        quoteText.text = currentQuote
    }
}