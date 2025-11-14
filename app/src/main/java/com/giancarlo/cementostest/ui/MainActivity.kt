package com.giancarlo.cementostest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.giancarlo.cementostest.domain.Movie
import com.giancarlo.cementostest.ui.theme.CementosTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.compareTo

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CementosTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = {
                        Text(
                            "Cementos Pacasmayo Test",
                            style = MaterialTheme.typography.titleLarge
                        )
                    })
                }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding))  {
                        MoviesScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CementosTestTheme {
        Greeting("Android")
    }
}

@Composable
fun MoviesScreen(vm: MoviesViewModel = hiltViewModel()) {
    val movies by vm.movies.collectAsState()

    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie) { }
        }
    }
}

@Composable
private fun MovieRow(movie: Movie, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(movie.title) },
        supportingContent = {
            Text(movie.plot.take(100) + movie.plot.length.let { if (it > 100) "…" else "" })
        },
        leadingContent = {
            /* Box(Modifier
                 .size(56.dp)
                 .background(MaterialTheme.colorScheme.surfaceVariant))*/
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
        },
        modifier = Modifier.clickable(onClick = onClick)
    )
}