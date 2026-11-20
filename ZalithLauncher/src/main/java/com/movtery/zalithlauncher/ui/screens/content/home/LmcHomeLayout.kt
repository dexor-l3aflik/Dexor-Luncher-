
package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LmcPink = Color(0xFFE8A0C5)
private val LmcWhite = Color(0xFFFDFBFF)
private val LmcBlue = Color(0xFF52799F)

@Composable
fun LmcHomeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    sideContent: @Composable () -> Unit
) {
    var selectedItem by remember { mutableStateOf("Home") }

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(LmcPink)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(72.dp),
            shape = RoundedCornerShape(24.dp),
            color = LmcWhite
        ) {
            Column(
                modifier = Modifier.padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LmcNavItem("⌂", "Home", selectedItem) {
                    selectedItem = "Home"
                }

                LmcNavItem("♙", "Accounts", selectedItem) {
                    selectedItem = "Accounts"
                }

                LmcNavItem("◇", "Wardrobe", selectedItem) {
                    selectedItem = "Wardrobe"
                }

                LmcNavItem("▣", "Mods", selectedItem) {
                    selectedItem = "Mods"
                }

                Spacer(Modifier.weight(1f))

                LmcNavItem("⚙", "Settings", selectedItem) {
                    selectedItem = "Settings"
                }
            }
        }

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(26.dp),
            color = LmcWhite
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
                .width(260.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(26.dp),
            color = Color(0xFFCCE8FF)
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
}

@Composable
private fun LmcNavItem(
    icon: String,
    label: String,
    selectedItem: String,
    onClick: () -> Unit
) {
    val selected = selectedItem == label

    Column(
        modifier = Modifier
            .width(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (selected) Color(0xFFFFE1EF)
                else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.titleLarge,
            color = if (selected) LmcPink else LmcBlue
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = LmcBlue
        )
    }
}
