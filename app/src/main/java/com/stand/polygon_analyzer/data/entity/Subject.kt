package com.stand.polygon_analyzer.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val fullScore: Int,
    val currentScore: Int,
    val percentage: Float,
    val color: Int,
    val createdAt: Long = System.currentTimeMillis()
)