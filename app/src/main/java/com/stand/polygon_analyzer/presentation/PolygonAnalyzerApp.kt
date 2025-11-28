package com.stand.polygon_analyzer.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stand.polygon_analyzer.presentation.screens.*
import com.stand.polygon_analyzer.presentation.viewmodel.SubjectViewModel

@Composable
fun PolygonAnalyzerApp(viewModel: SubjectViewModel) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "subject_list"
    ) {
        composable("subject_list") {
            SubjectListScreen(viewModel, navController)
        }
        composable("add_subject") {
            AddSubjectScreen(viewModel, navController)
        }
        composable("polygon_chart") {
            PolygonChartScreen(viewModel)
        }
    }
}

sealed class Screen(val route: String) {
    object SubjectList : Screen("subject_list")
    object AddSubject : Screen("add_subject")
    object PolygonChart : Screen("polygon_chart")
}