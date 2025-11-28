package com.stand.polygon_analyzer.data.dao

import androidx.room.*
import com.stand.polygon_analyzer.data.entity.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Subject>>
    
    @Query("SELECT * FROM subjects WHERE id = :id")
    suspend fun getById(id: Long): Subject?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject): Long
    
    @Update
    suspend fun update(subject: Subject)
    
    @Delete
    suspend fun delete(subject: Subject)
    
    @Query("DELETE FROM subjects")
    suspend fun deleteAll()
}