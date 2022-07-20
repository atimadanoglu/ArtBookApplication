package com.atakanmadanoglu.artbookapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import com.atakanmadanoglu.artbookapplication.repo.ArtRepositoryInterface
import com.atakanmadanoglu.artbookapplication.roomdb.Art
import com.atakanmadanoglu.artbookapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository: ArtRepositoryInterface
) : ViewModel() {

    val artList = repository.getArt()

    private val _images = MutableLiveData<Resource<ImageResponse>>()
    val images: LiveData<Resource<ImageResponse>> get() = _images

    private val _selectedImage = MutableLiveData<String>()
    val selectedImage: LiveData<String> get() = _selectedImage

    private var _insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMsg: LiveData<Resource<Art>> get() = _insertArtMsg

    fun resetImageInsertMsg() {
        _insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url: String) {
        _selectedImage.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch {
        repository.deleteArt(art)
    }

    fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun searchForImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }

        _images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            _images.value = response
        }
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            _insertArtMsg.postValue(Resource.error("Enter name, artist name and year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            _insertArtMsg.postValue(Resource.error("Year must be number", null))
            return
        }
        val art = Art(name, artistName, yearInt, _selectedImage.value ?: "")
        insertArt(art)
        _insertArtMsg.postValue(Resource.success(art))
    }

}