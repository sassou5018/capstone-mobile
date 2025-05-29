package com.example.crypto.ui.coin_list.ui_elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crypto.domain.models.Coin
import com.example.crypto.ui.Screen
import com.example.crypto.ui.coin_list.state.CoinListViewModel

@Composable
fun CoinListScreen(
    onItemClick: (Coin) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Box(

    ){

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(50.dp)
                .clickable {
                navController.navigate(Screen.WeatherScreen.route)
            }
        ) {
            Text("Go To Weather portion")
        }
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(state.coins) { coin ->
//                CoinListItem(
//                    coin = coin,
//                    onItemClick = { onItemClick(coin) }
//                )
//            }
//        }
//        if (state.error.isNotBlank()) {
//            Text(
//                text = state.error,
//                color = MaterialTheme.colorScheme.error,
//                textAlign = TextAlign.Center
//            )
//        }
//        if (state.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }
    }

}