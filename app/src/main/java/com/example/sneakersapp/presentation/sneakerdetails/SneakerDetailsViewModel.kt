package com.example.sneakersapp.presentation.sneakerdetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.domain.usecases.GetSneakerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerDetailsViewModel @Inject constructor(
    private val getSneakerByIdUseCase: GetSneakerByIdUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SneakerDetailsState())
    val state : State<SneakerDetailsState> = _state


    suspend fun getSneakerDetailsById(id:Int) {
        Log.i("shiv","fun called")
        _state.value.isLoading.value = true
        val job = viewModelScope.launch {
            Log.i("shiv","fun launch")
            _state.value.sneakerDetails.value = getSneakerByIdUseCase.invoke(id)
            Log.i("shiv",_state.value.sneakerDetails.value?.name.toString())
        }
        job.join()
        Log.i("shiv","launch complete")
        _state.value.isLoading.value = false
    }
}