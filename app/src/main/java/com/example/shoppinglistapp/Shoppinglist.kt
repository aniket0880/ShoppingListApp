package com.example.shoppinglistapp

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Define a data class to represent a shopping item
data class ShoppingItem(
    var id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)


// Annotation to opt in for experimental Material3 API
@OptIn(ExperimentalMaterial3Api::class)
// Composable function to build the shopping list app UI
@Composable
fun ShoppingListApp() {
    // Mutable state to hold the list of shopping items
    var shoppingItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    // Mutable state to control the visibility of the dialog for adding new items
    var showDialog by remember { mutableStateOf(false) }
    // Mutable state to hold the name of the item being added
    var itemName by remember { mutableStateOf("") }
    // Mutable state to hold the quantity of the item being added
    var itemQuantity by remember { mutableStateOf("") }

    // Column composable to arrange UI vertically
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // Button to trigger showing the dialog for adding new items
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        // LazyColumn to display the list of shopping items
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(shoppingItems) {
                // Display each shopping item
            }
        }
    }

    // Dialog for adding new shopping items
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Button to add the new item
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            // Create a new ShoppingItem and add it to the list
                            val newItem = ShoppingItem(
                                id = shoppingItems.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            shoppingItems = shoppingItems + newItem
                            // Reset states and hide the dialog
                            showDialog = false
                            itemName = ""
                        }
                    }) {
                        Text(text = "Add")
                    }
                    // Button to cancel adding the new item
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            },
            title = { Text(text = "Add Shopping Item") },
            text = {
                Column {
                    // Text field for entering the name of the item
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    // Text field for entering the quantity of the item
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )
    }
}
