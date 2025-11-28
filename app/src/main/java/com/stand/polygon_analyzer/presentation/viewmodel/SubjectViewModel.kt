package com.stand.polygon_analyzer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stand.polygon_analyzer.data.entity.Subject
import com.stand.polygon_analyzer.data.repository.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubjectViewModel(private val repository: SubjectRepository) : ViewModel() {
    val subjects = repository.getAllSubjects()
    
    private val _uiState = MutableStateFlow<SubjectUiState>(SubjectUiState.Idle)
    val uiState: StateFlow<SubjectUiState> = _uiState.asStateFlow()
    
    fun addSubject(name: String, fullScore: Int, currentScore: Int) {
        viewModelScope.launch {
            try {
                val percentage = (currentScore.toFloat() / fullScore.toFloat()) * 100
                val subject = Subject(
                    name = name,
                    fullScore = fullScore,
                    currentScore = currentScore,
                    percentage = percentage,
                    color = getRandomColor()
                )
                repository.insertSubject(subject)
                _uiState.value = SubjectUiState.Success("科目添加成功")
            } catch (e: Exception) {
                _uiState.value = SubjectUiState.Error("添加失败: ${e.message}")
            }
        }
    }
    
    fun deleteSubject(subject: Subject) {
        viewModelScope.launch {
            try {
                repository.deleteSubject(subject)
                _uiState.value = SubjectUiState.Success("科目删除成功")
            } catch (e: Exception) {
                _uiState.value = SubjectUiState.Error("删除失败: ${e.message}")
            }
        }
    }
    
    private fun getRandomColor(): Int {
        val colors = listOf(
            0xFFF44336, 0xFFE91E63, 0xFF9C27B0, 0xFF673AB7,
            0xFF3F51B5, 0xFF2196F3, 0xFF03A9F4, 0xFF00BCD4,
            0xFF009688, 0xFF4CAF50, 0xFF8BC34A, 0xFFCDDC39,
            0xFFFFEB3B, 0xFFFFC107, 0xFFFF9800, 0xFFFF5722
        )
        return colors.random().toInt()
    }
}

sealed class SubjectUiState {
    object Idle : SubjectUiState()
    data class Success(val message: String) : SubjectUiState()
    data class Error(val message: String) : SubjectUiState()
}