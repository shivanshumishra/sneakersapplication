package com.example.sneakersapp.presentation.cart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.domain.usecases.DeleteSneakerFromCartUseCase
import com.example.sneakersapp.domain.usecases.GetSneakersInCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getSneakersInCartUseCase: GetSneakersInCartUseCase,
    private val deleteSneakerFromCartUseCase: DeleteSneakerFromCartUseCase
) : ViewModel() {
    private var _state : MutableState<CartViewState?> = mutableStateOf(CartViewState(
        removeItemFromCart = {
            removeItemFromCart(it)
        }
    ))
    val state = _state

    init {
        getCartItems()
    }

    private fun getCartTotalAmount() {
        var total = 0
        state.value?.cartItem?.forEach {
            total += it.retail_price_cents
        }
        _state.value?.total?.value = total
    }

    private fun removeItemFromCart(sneakerCart : SneakerCart) {
        _state.value?.isLoading?.value = true
        viewModelScope.launch {
            delay(1000) //just to show loader
            deleteSneakerFromCartUseCase(sneakerCart)
            _state.value?.isLoading?.value = false
        }
    }

    private fun getCartItems() {
        _state.value?.isLoading?.value = true
        viewModelScope.launch {
            try {
                getSneakersInCartUseCase().collectLatest {
                    _state.value?.cartItem = it.toMutableList()
                    _state.value?.isLoading?.value = false
                    getCartTotalAmount()
                }
            } catch (e : Exception) {
                _state.value?.isLoading?.value = false
            }
        }
    }
}