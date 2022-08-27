package com.afsal.dev.typicodapp.repositoryimport com.afsal.dev.typicodapp.netWork.Resourceimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.withContextimport retrofit2.HttpExceptionabstract class BaseRepository {        suspend fun <T> safApiCall(apiCall:suspend ()->T):Resource<T>{        return withContext(Dispatchers.IO){            try {                Resource.Success(apiCall.invoke())            }catch (throwable:Throwable){                when(throwable){                    is HttpException ->{                        Resource.Failure(true,throwable.code(),throwable.response()?.errorBody())                    }                    else ->{                        Resource.Failure(true,null,null)                    }                }            }        }    }}