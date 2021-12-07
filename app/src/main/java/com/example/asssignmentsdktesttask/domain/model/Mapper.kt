package com.example.asssignmentsdktesttask.domain.model

interface Mapper<F, T> {
    suspend fun map(from: F): T
}

interface IndexedMapper<F, T> {
    suspend fun map(index: Int, from: F): T
}
