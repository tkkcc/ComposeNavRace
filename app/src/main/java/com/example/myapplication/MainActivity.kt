package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
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
                    val offsetDivider = 2
                    NavHost(navController = navController, startDestination = "a",
                        exitTransition = {
                            // smooth
//                            ExitTransition.None

                            // lag
//                            fadeOut()

                            // lag
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                                targetOffset = { it / 2 })
                        }, enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                                initialOffset = { it / 2 })
                        }, popExitTransition = {
                            ExitTransition.None
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
                                }, modifier = Modifier.fillMaxWidth()) {
                                    Text("b")
                                }
                                Button({
                                    navController.navigate("c") {
                                        popUpTo("a")
                                        launchSingleTop = true
                                    }
                                }, modifier = Modifier.fillMaxWidth()) {
                                    Text("c")
                                }

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("b")
                                    }) {
                                    Text("b")
                                }
                                IconButton(onClick = {
                                    navController.navigate("b")
                                }) {
                                    Text("b")
                                }

                            }
                        }
                        composable("b") {
                            Surface(color = Color.White) {
                                Column {
                                    Text(
                                        "ABC",
                                        style = MaterialTheme.typography.headlineLarge
                                    )
                                    Text(
                                        "ABC",
                                        style = MaterialTheme.typography.headlineLarge,
                                        color = Color.White,
                                        modifier = Modifier.background(color = Color.Black)
                                    )
                                    Text(
                                        "ABC",
                                        style = MaterialTheme.typography.headlineLarge
                                    )
                                    Text(
                                        "ABC",
                                        style = MaterialTheme.typography.headlineLarge,
                                        color = Color.White,
                                        modifier = Modifier.background(color = Color.Black)
                                    )
                                    Text(
                                        "ABC",
                                        style = MaterialTheme.typography.headlineLarge
                                    )
                                    Text(
                                        "ABC",
                                        style = MaterialTheme.typography.headlineLarge,
                                        color = Color.White,
                                        modifier = Modifier.background(color = Color.Black)
                                    )
                                }

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