package com.example.todolist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.data.ToDoItem
import com.example.todolist.domain.ToDoViewModel
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<ToDoViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ToDoListTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MainScreen(viewModel)
        }
      }
    }
  }

  @Composable
  fun MainScreen(toDoViewModel: ToDoViewModel) {
    ToDoListScreen(
      items = toDoViewModel.todoItems,
      selectedItems = toDoViewModel.selectedItems,
      onAddItem =  toDoViewModel::addItem,
      onToggleItem =  toDoViewModel::toggleItem,
      onDeleteItems =  toDoViewModel::deleteItems,
    )
  }
}

