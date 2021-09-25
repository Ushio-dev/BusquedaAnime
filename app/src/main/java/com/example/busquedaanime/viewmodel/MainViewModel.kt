package com.example.busquedaanime.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busquedaanime.model.Repository
import com.example.busquedaanime.model.ResponseAnime
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var animeList: MutableLiveData<Response<ResponseAnime>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()


    fun getAnimeList(url: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val myresponse = repository.getAnimeList(url)
            animeList.value = myresponse
            isLoading.postValue(false)
        }
    }
}