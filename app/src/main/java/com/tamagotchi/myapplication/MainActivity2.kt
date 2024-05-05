package com.tamagotchi.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var petImageView: ImageView
    private lateinit var hungerText: TextView
    private lateinit var lifeText: TextView
    private lateinit var groomText: TextView
    private lateinit var feedButton: Button
    private lateinit var playButton: Button
    private lateinit var cleanButton: Button

    private val initialHealth = 60
    private val initialHunger = 60
    private val initialCleanliness = 60
    private var health = initialHealth
    private var cleanliness = initialCleanliness
    private var hunger = initialHunger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        petImageView = findViewById(R.id.petImageView)
        hungerText = findViewById(R.id.hungerText)
        lifeText = findViewById(R.id.lifeText)
        groomText = findViewById(R.id.groomText)
        feedButton = findViewById(R.id.feedButton)
        playButton = findViewById(R.id.playButton)
        cleanButton = findViewById(R.id.cleanButton)

        updateStatus()

        feedButton.setOnClickListener {
            feedPet()
            updateStatus()
        }

        playButton.setOnClickListener {
            playWithPet()
            updateStatus()
        }

        cleanButton.setOnClickListener {
            cleanPet()
            updateStatus()
        }
    }

    private fun feedPet() {
        hunger += 10
        if (hunger > 100) hunger = 100
    }

    private fun playWithPet() {
        health += 10
        if (health > 100) health = 100
        hunger -= 5
        if (hunger < 0) hunger = 0
        cleanliness -= 5
        if (cleanliness < 0) cleanliness = 0
    }

    private fun cleanPet() {
        cleanliness += 10
        if (cleanliness > 100) cleanliness = 100
    }

    @SuppressLint("SetTextI18n")
    private fun updateStatus() {
        petImageView.setImageResource(getPetImage())
        hungerText.text = "Hunger: $hunger"
        lifeText.text = "Life: $health"
        groomText.text = "Cleanliness: $cleanliness"
    }

    private fun getPetImage(): Int {
        val hungerLevel = getHungerLevel()
        val healthLevel = getHealthLevel()
        val cleanlinessLevel = getCleanlinessLevel()

        // Choose image based on pet's status
        return when {
            healthLevel <= 3 -> R.drawable.sick_pet
            cleanlinessLevel <= 3 -> R.drawable.dirty_pet
            hungerLevel >= 7 -> R.drawable.hungry_pet
            else -> R.drawable.happy_pet
        }
    }

    private fun getHungerLevel(): Int {
        return (hunger / 10) // Assuming hunger ranges from 0 to 100
    }

    private fun getHealthLevel(): Int {
        return (health / 10) // Assuming health ranges from 0 to 100
    }

    private fun getCleanlinessLevel(): Int {
        return (cleanliness / 10) // Assuming cleanliness ranges from 0 to 100
    }
}
