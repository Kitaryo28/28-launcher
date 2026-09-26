package com.kitaryo.twentyeightlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Red = Color(0xFFE50914)
private val Dark = Color(0xFF080808)
private val Card = Color(0xFF151515)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LauncherApp() }
    }
}

@Composable
fun LauncherApp() {
    var tab by remember { mutableStateOf("Home") }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Red,
            background = Dark,
            surface = Card
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Dark)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            Text("28 LAUNCHER", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Black)
            Text("Minecraft Java • Mobile", color = Color.Gray, fontSize = 13.sp)

            Spacer(Modifier.height(20.dp))

            when (tab) {
                "Home" -> HomeScreen()
                "Store" -> SimpleScreen("STORE", "Mods • Modpacks • Resource Packs • Shaders")
                "Files" -> SimpleScreen("FILE MANAGER", "Manage mods, resource packs, saves and instances")
                "Settings" -> SimpleScreen("SETTINGS", "Java • RAM • Device • Performance • Accounts")
            }

            Spacer(Modifier.weight(1f))

            NavigationBar(
                containerColor = Card,
                contentColor = Color.White
            ) {
                listOf("Home", "Store", "Files", "Settings").forEach {
                    NavigationBarItem(
                        selected = tab == it,
                        onClick = { tab = it },
                        icon = {},
                        label = { Text(it) }
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun HomeScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Card),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(Modifier.padding(18.dp)) {
                Text("Minecraft 1.21.x", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Fabric • Default Instance", color = Color.Gray)
                Spacer(Modifier.height(14.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Red)
                ) {
                    Text("▶  PLAY", fontWeight = FontWeight.Bold)
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            InfoCard("JAVA", "Not configured")
            InfoCard("RAM", "Auto")
        }

        Text("Quick Access", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            ActionCard("Versions")
            ActionCard("Mods")
            ActionCard("Controls")
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = Card)) {
        Column(Modifier.padding(14.dp)) {
            Text(title, color = Red, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(value, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun ActionCard(label: String) {
    Card(Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = Card)) {
        Box(Modifier.padding(12.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(label, color = Color.White, fontSize = 12.sp)
        }
    }
}

@Composable
fun SimpleScreen(title: String, subtitle: String) {
    Column {
        Text(title, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(subtitle, color = Color.Gray)
        Spacer(Modifier.height(20.dp))
        Text("Coming in the next build.", color = Red)
    }
}
