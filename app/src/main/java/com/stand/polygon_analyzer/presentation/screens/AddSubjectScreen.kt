package com.stand.polygon_analyzer.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stand.polygon_analyzer.presentation.viewmodel.SubjectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubjectScreen(
    viewModel: SubjectViewModel,
    navController: NavHostController
) {
    var subjectName by remember { mutableStateOf("") }
    var fullScore by remember { mutableStateOf("") }
    var currentScore by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("添加科目") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (subjectName.isNotBlank() && fullScore.isNotBlank() && currentScore.isNotBlank()) {
                        viewModel.addSubject(
                            subjectName,
                            fullScore.toInt(),
                            currentScore.toInt()
                        )
                        navController.popBackStack()
                    }
                }
            ) {
                Text("保存")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = subjectName,
                onValueChange = { subjectName = it },
                label = { Text("科目名称") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = fullScore,
                onValueChange = { fullScore = it },
                label = { Text("满分") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = currentScore,
                onValueChange = { currentScore = it },
                label = { Text("当前分数") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}