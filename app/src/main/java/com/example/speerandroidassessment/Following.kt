package com.example.speerandroidassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.speerandroidassessment.Adapter.UserAdapter
import com.example.speerandroidassessment.ViewModel.ViewModel

class Following : AppCompatActivity() {
    private lateinit var viewModel: ViewModel // Initialize ViewModel
    private lateinit var username: String
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_following)

        username = intent.getStringExtra("USERNAME_EXTRA") ?: ""

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        adapter = UserAdapter(this, emptyList(), username)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        if (username.isNotEmpty()) {
            viewModel.fetchFollowing(username)

            viewModel.followingList.observe(this) { followingList ->
                followingList?.let {
                    adapter.submitList(it)
                }
            }
        } else {
            Toast.makeText(this, "Empty username", Toast.LENGTH_SHORT).show()
        }
    }
}