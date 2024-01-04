package com.example.speerandroidassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.speerandroidassessment.Adapter.UserAdapter
import com.example.speerandroidassessment.ViewModel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val usernameEditText: EditText = findViewById(R.id.username)
        val searchButton: Button = findViewById(R.id.submitbutton)


        searchButton.setOnClickListener {
            Log.d("SubmitButoon", "Button is clicked")
            val username = usernameEditText.text.toString()
            if (username.isNotEmpty()) {
                viewModel.fetchUser(username)
                val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                adapter = UserAdapter(this, emptyList()) // Initially set an empty list
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

                viewModel.userList.observe(this) { userList ->
                    userList?.let {
                        adapter.submitList(it) // Assuming UserAdapter has a method to update the list
                        Log.i("response", it.toTypedArray().contentToString())
                    }
                }
            } else {
                // Handle empty username input
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }

    }
}