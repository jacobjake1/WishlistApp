package com.example.wishlistapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var wishlistAdapter: WishlistAdapter
    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.wishlistRecyclerView)
        wishlistAdapter = WishlistAdapter(wishlistItems)
        recyclerView.adapter = wishlistAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addItemButton: Button = findViewById(R.id.addItemButton)
        addItemButton.setOnClickListener {
            showAddItemDialog()
        }
    }

    private fun showAddItemDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_item, null)
        val nameEditText = dialogView.findViewById<TextInputEditText>(R.id.nameEditText)
        val priceEditText = dialogView.findViewById<TextInputEditText>(R.id.priceEditText)
        val urlEditText = dialogView.findViewById<TextInputEditText>(R.id.urlEditText)

        AlertDialog.Builder(this)
            .setTitle("Add Wishlist Item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEditText.text.toString()
                val price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0
                val url = urlEditText.text.toString()

                val newItem = WishlistItem(wishlistItems.size, name, price, url)
                wishlistItems.add(newItem)
                wishlistAdapter.notifyItemInserted(wishlistItems.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}