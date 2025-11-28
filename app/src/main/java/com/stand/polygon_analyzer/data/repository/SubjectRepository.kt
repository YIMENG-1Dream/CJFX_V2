package com.stand.polygon_analyzer.data.repository

import com.stand.polygon_analyzer.data.dao.SubjectDao
import com.stand.polygon_analyzer.data.entity.Subject
import kotlinx.coroutines.flow.Flow

class SubjectRepository(private val subjectDao: SubjectDao) {
    fun getAllSubjects(): Flow<List<Subject>> = subjectDao.getAll()
    
    suspend fun getSubjectById(id: Long): Subject? = subjectDao.getById(id)
    
    suspend fun insertSubject(subject: Subject): Long = subjectDao.insert(subject)
    
    suspend fun updateSubject(subject: Subject) = subjectDao.update(subject)
    
    suspend fun deleteSubject(subject: Subject) = subjectDao.delete(subject)
    
    suspend fun deleteAllSubjects() = subjectDao.deleteAll()
}