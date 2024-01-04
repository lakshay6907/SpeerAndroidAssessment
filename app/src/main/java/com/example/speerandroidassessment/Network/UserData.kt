package com.example.speerandroidassessment.Network

import android.widget.EditText
import com.example.speerandroidassessment.Data.GithubUser
import com.example.speerandroidassessment.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

interface GitInterface {
    //End point to get user
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<GithubUser>

    // Endpoint to fetch followers of a user
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<List<GithubUser>>

    // Endpoint to fetch users followed by a user
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Response<List<GithubUser>>
}


object GitService{
    val userInstance: GitInterface

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        userInstance = retrofit.create(GitInterface::class.java)
    }
}