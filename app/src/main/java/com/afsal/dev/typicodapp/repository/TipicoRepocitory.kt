package com.afsal.dev.typicodapp.repositoryimport com.afsal.dev.typicodapp.netWork.ApiServiceimport javax.inject.Injectclass TipicoRepocitory @Inject constructor(     private val apiService: ApiService):BaseRepository() {    suspend fun getAllPosts()=safApiCall {        apiService.getAllPosts()    }    suspend fun getCommentsOnPost(id:Int)=safApiCall {        apiService.getCommentOnPost(id)    }    suspend fun getAlbums()=safApiCall {        apiService.getAlbums()    }    suspend fun getPhotosOnAlbum(id:Int)=safApiCall {        apiService.getPhotosOnAlbum(id)    }}