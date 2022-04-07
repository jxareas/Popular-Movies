package com.jonareas.android.popularmovies.viewmodel.actors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Actor
import com.jonareas.android.popularmovies.model.repository.ActorRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import com.jonareas.android.popularmovies.view.movies.ApiLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularActorsViewModel @Inject constructor(
    private val actorRepository: ActorRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private var _popularActors = MutableLiveData<List<Actor>>()
    val popularActors: LiveData<List<Actor>> = _popularActors

    private var _actorsLoadingState = MutableLiveData<ApiLoadingState>()
    val actorsLoadingState : LiveData<ApiLoadingState> = _actorsLoadingState

    init {
        fetchPopularActors()
    }

     fun onFragmentReady() = fetchPopularActors()

    private fun fetchPopularActors() {

        viewModelScope.launch(dispatchers.io) {
            try {

                _actorsLoadingState.postValue(ApiLoadingState.LOADING)

                actorRepository.fetchPopularActors().collectLatest { listOfActors ->
                    _popularActors.postValue(listOfActors)
                }

                _actorsLoadingState.postValue(ApiLoadingState.DONE)
            } catch (throwable: Throwable) {
                _actorsLoadingState.postValue(ApiLoadingState.ERROR)
                throwable.printStackTrace()
            }
        }

    }

}