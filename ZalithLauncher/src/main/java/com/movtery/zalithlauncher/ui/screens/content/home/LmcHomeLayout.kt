package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LmcPink = Color(0xFFE8A0C5)
private val LmcWhite = Color(0xFFFDFBFF)

@Composable
fun LmcHomeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    sideContent: @Composable () -> Unit
) {
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
                .width(64.dp),
            shape = RoundedCornerShape(24.dp),
            color = LmcWhite
        ) {
            Column(
                modifier = Modifier.padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                LmcNavItem("⌂", "Home")
                LmcNavItem("♙", "Accounts")
                LmcNavItem("◇", "Wardrobe")
                LmcNavItem("▣", "Mods")

                Spacer(Modifier.weight(1f))

                LmcNavItem("⚙", "Settings")
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
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        androidx.compose.material3.Text(
            text = icon,
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF52799F)
        )

        androidx.compose.material3.Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF52799F)
        )
    }
}