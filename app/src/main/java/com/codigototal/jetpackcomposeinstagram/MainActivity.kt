package com.codigototal.jetpackcomposeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.codigototal.jetpackcomposeinstagram.recivlerview.SuperHeroGridView
import com.codigototal.jetpackcomposeinstagram.recivlerview.SuperHeroViewSpecialControl
import com.codigototal.jetpackcomposeinstagram.recivlerview.SuperHeroViewSticky
import com.codigototal.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeInstagramTheme {
                // A surface container us the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color(0xff161c26)),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroViewSticky()
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
    JetpackComposeInstagramTheme {
        TwiteerScreen()
    }
}