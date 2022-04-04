package com.jonareas.android.popularmovies.viewmodel.actors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Actor
import com.jonareas.android.popularmovies.model.repository.ActorRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
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

    init {
        fetchPopularActors()
    }

    private fun fetchPopularActors() {

        viewModelScope.launch(dispatchers.io) {
            try {
                actorRepository.fetchPopularActors().collectLatest { listOfActors ->
                    _popularActors.postValue(listOfActors)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }

}