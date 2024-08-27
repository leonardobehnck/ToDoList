package com.example.todolist.repository

import java.util.UUID

data class ToDoItem(
  val name: String,
  val id: String = UUID.randomUUID().toString(),
)
