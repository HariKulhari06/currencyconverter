package com.example.asssignmentsdktesttask.data.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "symbol")
data class SymbolEntity(
    @PrimaryKey @NonNull val symbol: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "is_selected") val isSelected: Boolean = false,
) : BaseEntity