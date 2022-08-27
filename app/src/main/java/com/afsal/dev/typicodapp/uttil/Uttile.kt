package com.afsal.dev.typicodapp.uttilimport android.content.Contextimport android.view.Viewimport android.widget.ImageViewimport androidx.fragment.app.Fragmentimport androidx.recyclerview.widget.RecyclerViewimport com.afsal.dev.typicodapp.Rimport com.afsal.dev.typicodapp.netWork.Resourceimport com.bumptech.glide.Glideimport com.google.android.material.snackbar.Snackbarfun View.showSnackBar(message:String,action:(()->Unit)?=null){    val snackbar=Snackbar.make(this,message,Snackbar.LENGTH_LONG)    action?.let {        snackbar.setAction("Retry"){            it()        }    }    snackbar.show()}fun Fragment.handleApiError(failure:Resource.Failure,retry:(()->Unit)?= null ){    when{        failure.isNetworkError ==true ->requireView().showSnackBar("Pleas check your Internet connection !!",retry)       else ->{           val error= failure.errorBody?.toString().toString()           requireView().showSnackBar(error)       }    }}fun loadImage(imgUrl:String, imageView: ImageView){    Glide.with(imageView.context)        .load(imgUrl)        //.centerCrop()        .placeholder(R.drawable.fish)        .fitCenter()        .into(imageView)}