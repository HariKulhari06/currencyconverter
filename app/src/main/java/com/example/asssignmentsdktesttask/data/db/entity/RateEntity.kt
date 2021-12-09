package com.example.asssignmentsdktesttask.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate")
data class RateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "rate") val rate: Double? = null,
) : BaseEntity
