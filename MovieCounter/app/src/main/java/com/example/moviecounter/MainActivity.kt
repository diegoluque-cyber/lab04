package com.example.moviecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviecounter.ui.theme.MovieCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llamamos a nuestro contador de películas
                    MovieCounter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MovieCounter(modifier: Modifier = Modifier) {
    // Estado para el contador de películas
    var count by remember { mutableStateOf(0) }
    // Estado para el nombre de la película (TextField)
    var movieName by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You have added $count movies.")
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el nombre de la película
        TextField(
            value = movieName,
            onValueChange = { movieName = it },
            label = { Text("Movie Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que incrementa el contador si el campo no está vacío
        Button(onClick = {
            if (movieName.isNotEmpty()) {
                count++
                movieName = "" // Limpia el campo de texto
            }
        }) {
            Text("Add Movie")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieCounter() {
    MovieCounterTheme {
        MovieCounter()
    }
}