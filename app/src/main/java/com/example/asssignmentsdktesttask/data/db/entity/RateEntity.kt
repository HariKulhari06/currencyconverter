package com.example.asssignmentsdktesttask.data.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate")
data class RateEntity(
    @PrimaryKey @NonNull val symbol: String,
    @ColumnInfo(name = "rate") val rate: Double? = null,
) : BaseEntity
