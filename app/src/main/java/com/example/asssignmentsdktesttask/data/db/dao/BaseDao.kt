package com.example.asssignmentsdktesttask.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.asssignmentsdktesttask.data.db.entity.BaseEntity

interface BaseDao<E : BaseEntity> {
    @Insert
    suspend fun insert(t: E)

    @Insert
    suspend fun insertAll(vararg entity: E)

    @Insert
    suspend fun insertAll(t: List<E>)

    @Update
    suspend fun update(t: E)

    @Delete
    suspend fun delete(t: E)
}