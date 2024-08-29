package com.example.todolist

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.data.ToDoItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
  items: List<ToDoItem>,
  selectedItems: List<ToDoItem>,
  onAddItem: (ToDoItem) -> Unit,
  onToggleItem: (ToDoItem) -> Unit,
  onDeleteItems: () -> Unit,
) {

  val (name, onNameChange) = rememberSaveable { mutableStateOf("") }

  val submitItem = {
    if (name.isNotBlank()) {
      onAddItem(ToDoItem(name))
      onNameChange("")
    }
  }

  Scaffold(modifier = Modifier.fillMaxSize(), topBar = {}) {
    Column(Modifier.fillMaxSize()) {
      TopAppBar(title = { Text(text = "Lista de Tarefas") },
        actions = {
          IconButton(
            onClick = onDeleteItems,
            Modifier.padding(horizontal = 8.dp)
          ) {
            Icon(Icons.Rounded.Delete, contentDescription = "Deletar item")
          }
        }
      )
      HorizontalDivider()
      LazyColumn(
        Modifier
          .fillMaxWidth()
          .weight(1.0f)
      ) {
        items(items = items) { todoItem ->
          val selected = selectedItems.find { it == todoItem } != null
          ToDoRow(toDoItem = todoItem, selected = selected, doToggle = onToggleItem)
        }
      }

      ToDoFieldAndButton(text = name, onTextChange = onNameChange, onAddItem = submitItem)
    }
  }
}


@Composable
fun ToDoRow(
  toDoItem: ToDoItem,
  selected: Boolean,
  doToggle: (ToDoItem) -> Unit

) {
  Column(modifier = Modifier.fillMaxSize()) {
    Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
      Text(
        text = toDoItem.name,
        modifier = Modifier
          .padding(horizontal = 8.dp)
          .weight(1.0f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = if (selected) {
          MaterialTheme.typography.titleMedium.copy(
            textDecoration = TextDecoration.LineThrough
          )
        } else {
          MaterialTheme.typography.titleMedium
        }
      )
      Checkbox(checked = selected, onCheckedChange = { doToggle(toDoItem) })
    }
    HorizontalDivider()
  }
}

@Composable
fun ToDoFieldAndButton(
  text: String,
  onTextChange: (String) -> Unit,
  onAddItem: () -> Unit,
) {

  HorizontalDivider(Modifier.fillMaxWidth())
  Row(Modifier.fillMaxWidth()) {
    TextField(
      value = text,
      onValueChange = onTextChange,
      singleLine = true,
      maxLines = 1,
      placeholder = { Text(text = "Adicione um item") },
      textStyle = MaterialTheme.typography.titleMedium,
      modifier = Modifier
        .weight(1.0f)
    )
    IconButton(
      onClick = onAddItem,
      modifier = Modifier
        .padding(horizontal = 6.dp)
        .align(Alignment.CenterVertically)
    ) {
      Icon(Icons.AutoMirrored.Rounded.Send, contentDescription = "Adicionar")
    }
  }
}

@Preview
@Composable
fun ToDoListScreenPreview() {
  ToDoListScreen(
    items = listOf(ToDoItem("item1"), ToDoItem("item2"), ToDoItem("item3"), ToDoItem("item4")),
    selectedItems = listOf(),
    onAddItem = {},
    onToggleItem = {}
  ) {
  }
}