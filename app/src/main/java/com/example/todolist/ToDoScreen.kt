package com.example.todolist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
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
  onAddItem: () -> Unit,
  onToggleItem: (ToDoItem) -> Unit,
  onDeleteItems: () -> Unit,
) {

  Scaffold(topBar = {
    TopAppBar(title = { Text(text = "Lista de Tarefas") },
      actions = {
        IconButton(
          onClick = { onDeleteItems }, Modifier.padding(horizontal = 8.dp)
        ) {
          Icon(Icons.Rounded.Delete, contentDescription = "Deletar item")
        }
      }
    )
  }) { }
}


@Composable
fun ToDoScreen(
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
      value = text, onValueChange = onTextChange,
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
    items = listOf(),
    selectedItems = listOf(),
    onAddItem = {},
    onToggleItem = {}
  ) {

  }
}


@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
  ToDoScreen(toDoItem = ToDoItem("item de exemplo"), true, {})
}

@Preview(showBackground = true)
@Composable
fun ToDoFieldAndButtonPreview() {
  ToDoFieldAndButton(text = "", onTextChange = {}, onAddItem = {})
}