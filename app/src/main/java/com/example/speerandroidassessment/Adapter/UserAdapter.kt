package com.example.speerandroidassessment.Adapter

import android.content.Context
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.speerandroidassessment.Data.GithubUser
import com.example.speerandroidassessment.R

class UserAdapter(private val context: Context, private var dataset: List<GithubUser>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val avatar:ImageView = view.findViewById(R.id.avatarImageView)
        val username:TextView = view.findViewById(R.id.usernameTextView)
        val name:TextView = view.findViewById(R.id.nameTextView)
        val Description:TextView = view.findViewById(R.id.descriptionTextView)
        val followers:TextView = view.findViewById(R.id.followersTextView)
        val following:TextView = view.findViewById(R.id.followingTextView)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = dataset[position]
        val viewholder = holder as ItemViewHolder
        currentItem.let {
            it.avatar_url?.let { it1 -> setImage(context, it1, viewholder.avatar) }
            viewholder.username.text = it.login
            viewholder.name.text = it.name
            viewholder.Description.text = it.bio
            viewholder.followers.text = it.followers.toString()
            viewholder.following.text = it.following.toString()
        }
    }
    private fun setImage(context: Context, thumbnail: String, imageView: ImageView) {
        val request = ImageRequest.Builder(context)
            .data(thumbnail) // Assuming thumbnail is the URL string
            .target(imageView)
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .build()
        imageLoader.enqueue(request)
    }

    fun submitList(it: List<GithubUser>) {
        this.dataset = it
        notifyDataSetChanged()
    }

}