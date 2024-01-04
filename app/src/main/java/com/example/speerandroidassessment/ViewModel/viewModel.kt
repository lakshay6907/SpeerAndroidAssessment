package com.example.speerandroidassessment.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speerandroidassessment.Data.GithubUser
import com.example.speerandroidassessment.Network.GitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    private val _userList = MutableLiveData<List<GithubUser>?>()
    val userList: MutableLiveData<List<GithubUser>?> = _userList

    private val _followersList = MutableLiveData<List<GithubUser>?>()
    val followersList: MutableLiveData<List<GithubUser>?> = _followersList

    private val _followingList = MutableLiveData<List<GithubUser>?>()
    val followingList: MutableLiveData<List<GithubUser>?> = _followingList

    private val service = GitService.userInstance
    fun fetchUser(username: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = GitService.userInstance.getUser(username)
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        _userList.postValue(listOf(it)) // Post a list containing a single non-null user
                    } ?: run {
                        _userList.postValue(emptyList()) // If user is null, post an empty list
                    }
                } else {
                    // Handle unsuccessful response (e.g., user not found, server error, etc.)
                    _userList.postValue(emptyList()) // Post an empty list
                }
            } catch (e: Exception) {
                Log.i("exception", e.message.toString())
            }
        }
    }

    fun fetchFollowers(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getFollowers(username)
                if (response.isSuccessful) {
                    val followers = response.body()
                    _followersList.postValue(followers)
                } else {
                    _followersList.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("Exception", e.message.toString())
            }
        }
    }

    fun fetchFollowing(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getFollowing(username)
                if (response.isSuccessful) {
                    val following = response.body()
                    _followingList.postValue(following)
                } else {
                    _followingList.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("Exception", e.message.toString())
            }
        }
    }
}