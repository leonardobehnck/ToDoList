package com.example.todolist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todolist.repository.ToDoItem

class ToDoViewModel : ViewModel() {

  val todoItems = mutableStateListOf<ToDoItem>()

  val selectedItems = mutableStateListOf<ToDoItem>()

  fun addItem(newItem: ToDoItem) {
    todoItems.add(newItem)
  }

  fun toggleItem(toggledItem: ToDoItem) {
    val foundItem = selectedItems.find { it == toggledItem }
    if (foundItem == null) {
      selectedItems.remove(toggledItem)
    }
    else {
      selectedItems.add(toggledItem)
    }
  }
}
