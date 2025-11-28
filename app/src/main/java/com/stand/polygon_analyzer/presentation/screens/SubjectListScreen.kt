package com.stand.polygon_analyzer.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stand.polygon_analyzer.data.entity.Subject
import com.stand.polygon_analyzer.presentation.viewmodel.SubjectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListScreen(
    viewModel: SubjectViewModel,
    navController: NavHostController
) {
    val subjects by viewModel.subjects.collectAsState(initial = emptyList())
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("多边形成绩分析") },
                actions = {
                    IconButton(onClick = { navController.navigate("polygon_chart") }) {
                        Icon(Icons.Default.BarChart, contentDescription = "图表")
                    }
                    IconButton(onClick = { navController.navigate("add_subject") }) {
                        Icon(Icons.Default.Add, contentDescription = "添加")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (subjects.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("暂无科目数据，请点击右上角添加")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(subjects) { subject ->
                        SubjectItem(
                            subject = subject,
                            onDelete = { viewModel.deleteSubject(subject) }
                        )
                    }
                }
            }
            
            // 显示状态消息
            when (uiState) {
                is SubjectUiState.Success -> {
                    LaunchedEffect(uiState) {
                        // 3秒后清除成功消息
                    }
                }
                is SubjectUiState.Error -> {
                    // 显示错误消息
                }
                else -> {}
            }
        }
    }
}

@Composable
fun SubjectItem(subject: Subject, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "分数: ${subject.currentScore}/${subject.fullScore}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "百分比: ${"%.1f".format(subject.percentage)}%",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "删除")
            }
        }
    }
}