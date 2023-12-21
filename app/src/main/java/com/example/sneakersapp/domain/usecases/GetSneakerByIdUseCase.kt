package com.example.sneakersapp.domain.usecases

import com.example.sneakersapp.data.local.dto.toSneaker
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.domain.repository.Repository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetSneakerByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Sneaker? {
        try {
            delay(2000)
            val sneakerdto = repository.getSneakerById(id)
            if (sneakerdto != null) {
                return sneakerdto.toSneaker()
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }
}
