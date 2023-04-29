package com.example.mycomposeteach.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mycomposeteach.model.entity.UserInfo
import com.example.mycomposeteach.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: HomeViewModel) {

    val allUser = viewModel.allUser.observeAsState()

    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            placeholder = { Text("What is your name") }
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            placeholder = { Text("What is your password") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
            viewModel.addUser(username.value, password.value)
            username.value = ""
            password.value = ""
        }) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("目前的使用者目錄")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            allUser.value?.let { list ->
                items(list) {
//                    it.UserInfoCard {
//                        viewModel.deleteUser(it)
//                    }
                    // 這個寫法和上面註解的一樣
                    it.UserInfoCard(viewModel::deleteUser)
                }
            }
        }
    }
}

@Composable
private fun UserInfo.UserInfoCard(onClick: ((UserInfo) -> Unit)? = null) {
    this.let { userInfo ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 2.dp)
                .clickable {
                    onClick?.invoke(userInfo)
//                    上面的看不懂，可以寫成底下的
//                    if (onClick != null) {
//                        onClick(userInfo)
//                    }
                }
                .clip(RoundedCornerShape(50))
                .background(Color.Blue)
                .padding(vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
                Text(text = userInfo.id.toString(), color = Color.White)
            }
            Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.CenterStart) {
                Text(text = userInfo.name, color = Color.White)
            }
            Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.CenterStart) {
                Text(text = userInfo.password, color = Color.White)
            }
        }
    }
}