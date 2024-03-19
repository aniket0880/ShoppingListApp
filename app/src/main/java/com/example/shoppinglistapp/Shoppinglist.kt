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

data class shoppingitem(
    var id:Int,
    var name:String,
    var quantity:Int,
    var isEditing:Boolean=false

)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun shoppinglisapp() {
    var sItems by remember { mutableStateOf(listOf<shoppingitem>()) }
    var showdialog by remember { mutableStateOf(false) }
    var itemname by remember { mutableStateOf("") }
    var itemquantity by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = { showdialog=true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")

        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) {

            }

        }

    }

    if(showdialog){
      AlertDialog(onDismissRequest = { showdialog=false},
          confirmButton = {
                     Row(modifier = Modifier
                         .fillMaxWidth()
                         .padding(8.dp),
                         horizontalArrangement = Arrangement.SpaceBetween) {
                         Button(onClick = {
                             if(itemname.isNotBlank()){
                                 val newitem=shoppingitem(
                                     id=sItems.size+1,
                                     name=itemname,
                                     quantity = itemquantity.toInt())
                                 sItems=sItems+newitem
                                 showdialog=false
                                 itemname=""
                             }

                         }) {
                             Text(text = "Add")
                         }
                         Button(onClick = { showdialog=false }) {
                             Text(text = "Cancel")
                             
                         }

                     }

      },
        title={ Text(text = "Add Shopping Item") },
         text = {
             Column {
                 OutlinedTextField(
                     value =itemname,
                     onValueChange ={itemname=it},
                     singleLine = true,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(8.dp)
                 )
                 OutlinedTextField(
                     value =itemquantity,
                     onValueChange ={itemquantity=it},
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