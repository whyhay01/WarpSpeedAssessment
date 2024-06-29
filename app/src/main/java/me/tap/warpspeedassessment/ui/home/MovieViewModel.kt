package me.tap.warpspeedassessment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tap.warpspeedassessment.domain.model.Movie
import me.tap.warpspeedassessment.domain.movie.GetCachedMovieListUseCase
import me.tap.warpspeedassessment.domain.movie.GetMovieListByNameUseCase
import me.tap.warpspeedassessment.domain.network.CheckInternetUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieListByNameUseCase: GetMovieListByNameUseCase,
    private val getCachedMovieListUseCase: GetCachedMovieListUseCase,
    private val checkInternetUseCase: CheckInternetUseCase
) : ViewModel() {

//    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList = getCachedMovieListUseCase(Unit)!!.asLiveData(viewModelScope.coroutineContext)

    private val _error = MutableLiveData<Pair<Boolean, String>>()
    val error: LiveData<Pair<Boolean, String>> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading



    fun getMovieList(name: String) {
        viewModelScope.launch {
            if (!checkInternetUseCase(Unit)) {
                _error.value = Pair(true, "Internet is not Available")
            } else {
                _loading.value = true
                val result = getMovieListByNameUseCase(name)
                _error.value = Pair(false, "")
                if (result.isSuccess()) {
                    _loading.value = false
//                    _movieList.value = result.data!!
                    Timber.d("GetMovieList: ${result.data}")
                } else {
                    _loading.value = false
                    _error.value = Pair(true, result.message)
                    Timber.d("Error response: ${result.message}")
                }
            }
        }
    }

}