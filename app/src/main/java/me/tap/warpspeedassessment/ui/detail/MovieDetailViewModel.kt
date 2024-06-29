package me.tap.warpspeedassessment.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tap.warpspeedassessment.domain.model.MovieDetails
import me.tap.warpspeedassessment.domain.movie.GetMovieDetailsUseCase
import me.tap.warpspeedassessment.domain.network.CheckInternetUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val checkInternetUseCase: CheckInternetUseCase
): ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetails>()
    val movieDetail: LiveData<MovieDetails> = _movieDetail

    private val _error = MutableLiveData<Pair<Boolean, String>>()
    val error: LiveData<Pair<Boolean, String>> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        if (!checkInternetUseCase(Unit)){
            _error.value = Pair(true, "Internet is required")
        }else{

            _error.value = Pair(false, "")
        }
    }

    fun getMovieDetail(movieID: String){
        viewModelScope.launch {
            _loading.value = true
            val result = getMovieDetailsUseCase(movieID)

            if (result.isSuccess()){
                _loading.value = false
                _movieDetail.value = result.data!!
                Timber.d("Response: ${result.data}")
            }else{
                _loading.value = false
                _error.value = Pair(true, result.message)
            }

        }
    }
}