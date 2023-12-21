package com.example.sneakersapp.presentation.sneakerlist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.sneakersapp.common.ASCENDING_YEAR
import com.example.sneakersapp.common.DESCENDING_YEAR
import com.example.sneakersapp.common.NONE_YEAR
import com.example.sneakersapp.common.Resource
import com.example.sneakersapp.common.SortType
import com.example.sneakersapp.data.local.dto.toSneaker
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.domain.usecases.AddSneakerToCartUseCase
import com.example.sneakersapp.domain.usecases.GetSneakersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SneakerListViewModel @Inject constructor(
    private val getSneakersUseCase: GetSneakersUseCase,
    private val addSneakerToCartUseCase: AddSneakerToCartUseCase
) : ViewModel(){

    private val _state = mutableStateOf(SneakerListState())
    val state : State<SneakerListState> = _state

    private val _showSortLoader = mutableStateOf(false)
    val showSortLoader : State<Boolean> = _showSortLoader

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    init {
        getSneakers()
    }

    fun changeSortDirection(sortString : String) {
        if (sortString.isBlank()){
            return
        }
        if(sortString.contains("Ascending",ignoreCase = true)){
            sortSneakers(sortType = ASCENDING_YEAR).onEachElement()
        } else if (sortString.contains("Descending", ignoreCase = true)){
            sortSneakers(sortType = DESCENDING_YEAR).onEachElement()
        } else {
            getSneakers()
        }
    }

    fun onSearchTextChange(text : String) {
        _searchText.value = text
    }

    private fun sortSneakers(sortType: SortType) : Flow<Resource<List<Sneaker>>> = flow {
//            emit(Resource.Loading<List<Sneaker>>())
            _showSortLoader.value = true
            delay(2000) //just to show loader
            when(sortType){
                ASCENDING_YEAR -> {
                    emit(Resource.Success<List<Sneaker>>(_state.value.sneakers.sortedBy { it.release_year }))
                    _showSortLoader.value = false
                }
                DESCENDING_YEAR -> {
                    val sortedList = _state.value.sneakers.sortedByDescending { it.release_year }
                    emit(Resource.Success<List<Sneaker>>(sortedList))
                    _showSortLoader.value = false
                }
                NONE_YEAR -> {
                    emit(Resource.Success<List<Sneaker>>(_state.value.sneakers))
                    _showSortLoader.value = false
                }
            }
    }

    private fun Flow<Resource<List<Sneaker>>>.onEachElement() {
        this.onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = SneakerListState(error = result.message ?: "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = SneakerListState(isLoading = true)
                }
                is Resource.Success -> {
                    result.data?.let {
                        var filteredList : MutableList<Sneaker> = mutableListOf()
                        if(_searchText.value.isNotBlank()) {
                            result.data.forEach { sneaker ->
                                if(sneaker.doesMatchSearchQuery(_searchText.value)){
                                    filteredList.add(sneaker)
                                }
                            }
                        } else {
                            filteredList = it.toMutableList()
                        }
                        _state.value = SneakerListState(sneakers = filteredList)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSneakers() {
        getSneakersUseCase().onEachElement()
    }

    suspend fun addSneakerToCart(sneaker: Sneaker) : Boolean {
        val job = viewModelScope.launch {
            sneaker.let {
                val sneakerCart  =   SneakerCart(
                    brand_name = it.brand_name,
                    color = it.color,
                    designer = it.designer,
                    details = it.details,
                    grid_picture_url = it.grid_picture_url,
                    has_picture = it.has_picture,
                    name = it.name,
                    nickname = it.nickname,
                    original_picture_url = it.original_picture_url,
                    release_date = it.release_date,
                    release_year = it.release_year,
                    retail_price_cents = it.retail_price_cents,
                    id = it.id,
                    quantity = 0
                )
                addSneakerToCartUseCase(sneakerCart)
            }
        }
        job.join()
        return true
    }
}