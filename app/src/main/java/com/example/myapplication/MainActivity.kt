package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "a",
                        exitTransition = {
                            fadeOut()
                        }, enterTransition = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start)
                        }, popExitTransition = {
                            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.End)
                        }, popEnterTransition = {
                            EnterTransition.None
                        }

                    ) {
                        composable("a") {
                            Column {
                                Button({
                                    navController.navigate("b") {
                                        popUpTo("a")
                                        launchSingleTop = true
                                    }
                                }) {
                                    Text("b")
                                }
                                Button({
                                    navController.navigate("c") {
                                        popUpTo("a")
                                        launchSingleTop = true
                                    }
                                }) {
                                    Text("c")
                                }
                            }
                        }
                        composable("b") {
                            Surface(color = Color.Blue) {
                            }
                        }
                        composable("c") {
                            Surface(color = Color.Red) {
                            }
                        }
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
    MyApplicationTheme {
        Greeting("Android")
    }
}