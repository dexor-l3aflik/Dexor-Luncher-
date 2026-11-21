
package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val LmcSky = Color(0xFFB8D9FF)
private val LmcBlue = Color(0xFF0868F5)
private val LmcText = Color(0xFF405C79)
private val LmcGlass = Color(0xEFFFFFFF)
private val LmcSelected = Color(0xFF0868F5)

@Composable
fun LmcHomeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    sideContent: @Composable () -> Unit
) {
    var selectedItem by remember { mutableStateOf("Play") }

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(LmcSky)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(62.dp),
            shape = RoundedCornerShape(28.dp),
            color = LmcGlass
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LmcRailItem("⌂", "Home", selectedItem) {
                    selectedItem = "Play"
                }
                LmcRailItem("▣", "Game", selectedItem) {
                    selectedItem = "Play"
                }
                LmcRailItem("↓", "Downloads", selectedItem) {
                    selectedItem = "Downloads"
                }
                LmcRailItem("♙", "Accounts", selectedItem) {
                    selectedItem = "Accounts"
                }
                LmcRailItem("▰", "Files", selectedItem) {
                    selectedItem = "Files"
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1.65f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(28.dp),
                    color = LmcGlass
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        content()
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(28.dp),
                    color = Color(0xFFD6EDFF)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        sideContent()
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = RoundedCornerShape(28.dp),
                color = LmcGlass
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 6.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LmcBottomItem("▶", "Play", selectedItem) {
                        selectedItem = "Play"
                    }
                    LmcBottomItem("♙", "Accounts", selectedItem) {
                        selectedItem = "Accounts"
                    }
                    LmcBottomItem("♧", "Wardrobe", selectedItem) {
                        selectedItem = "Wardrobe"
                    }
                    LmcBottomItem("◇", "Mods", selectedItem) {
                        selectedItem = "Mods"
                    }
                    LmcBottomItem("⚙", "Settings", selectedItem) {
                        selectedItem = "Settings"
                    }
                }
            }
        }
    }
}

@Composable
private fun LmcRailItem(
    icon: String,
    label: String,
    selectedItem: String,
    onClick: () -> Unit
) {
    val selected = selectedItem == label ||
        (label == "Home" && selectedItem == "Play") ||
        (label == "Game" && selectedItem == "Play")

    Column(
        modifier = Modifier
            .width(52.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(if (selected) Color(0xFFDCEBFF) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = icon,
            color = if (selected) LmcBlue else LmcText,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = LmcText,
            maxLines = 1
        )
    }
}

@Composable
private fun RowScope.LmcBottomItem(
    icon: String,
    label: String,
    selectedItem: String,
    onClick: () -> Unit
) {
    val selected = selectedItem == label

    Row(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(if (selected) LmcSelected else Color.Transparent)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = icon,
                color = if (selected) Color.White else LmcText,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                color = if (selected) Color.White else LmcText,
                maxLines = 1
            )
        }
    }
}
