package com.example.mycomposeteach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.mycomposeteach.model.dao.UserInfoDao
import com.example.mycomposeteach.ui.theme.MyComposeTeachTheme
import com.example.mycomposeteach.view.HomePage
import com.example.mycomposeteach.viewmodel.HomeViewModel
import com.example.mycomposeteach.viewmodel.HomeViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var userInfoDao: UserInfoDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userInfoDao = (application as MyComposeTeachApplication).db.userInfoDao
        homeViewModelFactory = HomeViewModelFactory(userInfoDao)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        setContent {
            MyComposeTeachTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(homeViewModel)
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
    MyComposeTeachTheme {
        Greeting("Android")
    }
}