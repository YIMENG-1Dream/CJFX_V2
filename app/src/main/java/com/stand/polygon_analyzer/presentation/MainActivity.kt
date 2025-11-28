package com.stand.polygon_analyzer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.stand.polygon_analyzer.data.database.AppDatabase
import com.stand.polygon_analyzer.data.repository.SubjectRepository
import com.stand.polygon_analyzer.presentation.theme.PolygonAnalyzerTheme
import com.stand.polygon_analyzer.presentation.viewmodel.SubjectViewModel

class MainActivity : ComponentActivity() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { SubjectRepository(database.subjectDao()) }
    private val viewModel: SubjectViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return SubjectViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PolygonAnalyzerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PolygonAnalyzerApp(viewModel)
                }
            }
        }
    }
}