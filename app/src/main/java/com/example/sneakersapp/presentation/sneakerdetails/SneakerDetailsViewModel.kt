package com.example.sneakersapp.presentation.sneakerdetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.domain.usecases.AddSneakerToCartUseCase
import com.example.sneakersapp.domain.usecases.GetSneakerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerDetailsViewModel @Inject constructor(
    private val getSneakerByIdUseCase: GetSneakerByIdUseCase,
    private val addSneakerToCartUseCase: AddSneakerToCartUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SneakerDetailsState())
    val state : State<SneakerDetailsState> = _state

    init {
        state.value.onAddToCart = {
            addSneakerToCart(it,state.value.selectedSize.value)
        }
    }

    suspend fun getSneakerDetailsById(id:Int) {
        _state.value.isLoading.value = true
        val job = viewModelScope.launch {
            _state.value.sneakerDetails.value = getSneakerByIdUseCase.invoke(id)
        }
        job.join()
        _state.value.isLoading.value = false
    }

    private fun addSneakerToCart(sneaker: Sneaker, size : Double) {
        viewModelScope.launch {
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
                    quantity = 0,
                    size = size
                )
                addSneakerToCartUseCase(sneakerCart)
            }
        }
    }
}