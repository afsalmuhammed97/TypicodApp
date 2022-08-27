package com.afsal.dev.typicodapp.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afsal.dev.typicodapp.model.AlbumsItem
import com.afsal.dev.typicodapp.model.CommentsItem
import com.afsal.dev.typicodapp.model.Photos
import com.afsal.dev.typicodapp.model.PostsModel
import com.afsal.dev.typicodapp.netWork.Resource
import com.afsal.dev.typicodapp.repository.TipicoRepocitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import java.lang.Exception
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(private val repocitory: TipicoRepocitory) : ViewModel() {

    private val _photosList=MutableLiveData<Resource<Response<Photos>>>()
    val photosList:LiveData<Resource<Response<Photos>>> get()= _photosList

    private val _albumsList=MutableLiveData<Resource<Response<List<AlbumsItem>>>>()
    val  albumsList:LiveData<Resource<Response<List<AlbumsItem>>>> get() =  _albumsList

    private val _commentList=MutableLiveData<Resource<Response<List<CommentsItem>>>>()
    val commentList:LiveData<Resource<Response<List<CommentsItem>>>> get() = _commentList


    private val _postList = MutableLiveData<Resource<Response<List<PostsModel>>>>()
    val postList: LiveData<Resource<Response<List<PostsModel>>>> get() = _postList

    init {
        getAllPosts()
    }

    private fun getAllPosts(){


        viewModelScope.launch (Dispatchers.IO){

       val result =repocitory.getAllPosts()


                _postList.postValue(result)




        }
    }

    fun getCommentsOnPost(commentId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repocitory.getCommentsOnPost(commentId)


                   _commentList.postValue(result)




        }
    }


    fun getAllAlbums(){
        viewModelScope.launch (Dispatchers.IO){

            val result=repocitory.getAlbums()


                 _albumsList.postValue(result)
             Log.d("RRR","albums in viewmodel $result")


        }
    }


    fun getPhotosOnAlbum(albumId:Int){
        viewModelScope.launch {
            val result=repocitory.getPhotosOnAlbum(albumId)

            _photosList.postValue(result)
        }
    }


}