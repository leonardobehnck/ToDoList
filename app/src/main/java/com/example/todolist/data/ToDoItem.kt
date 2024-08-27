package com.example.todolist.data

import java.util.UUID

data class ToDoItem(
  val name: String,
  val id: String = UUID.randomUUID().toString(),
)
