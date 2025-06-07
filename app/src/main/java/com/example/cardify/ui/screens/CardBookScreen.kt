package com.example.cardify.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cardify.cardbook.CardBookViewModel
import com.example.cardify.navigation.Screen
import com.example.cardify.ui.components.Base64Image
import com.example.cardify.ui.components.BottomNavBar

@Composable
fun CardBookScreen(navController: NavController, viewModel: CardBookViewModel) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = "cardbook",
                onNavigateToMain = { navController.popBackStack() },
                onNavigateToCardBook = {},
                onNavigateToSettings = {}
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            itemsIndexed(viewModel.cards) { index, card ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (card.imageUrl.isNotEmpty()) {
                        Base64Image(
                            base64 = card.imageUrl,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    ) {
                        Text(text = card.name, color = Color.Black)
                        Text(text = card.phone, color = Color.Gray)
                        Text(text = card.email, color = Color.Gray)
                    }

                    IconButton(onClick = {
                        navController.navigate(Screen.EditCard.createRoute(index))
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            }
        }
    }
}
