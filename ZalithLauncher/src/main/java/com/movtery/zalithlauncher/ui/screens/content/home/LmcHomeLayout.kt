
package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LmcPink = Color(0xFFE8A0C5)
private val LmcBlue = Color(0xFFB9DFFF)
private val LmcWhite = Color(0xFFFDFBFF)

@Composable
fun LmcHomeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
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

        Row(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                modifier = Modifier.weight(1.15f).fillMaxHeight(),
                shape = RoundedCornerShape(26.dp),
                color = LmcWhite.copy(alpha = 0.94f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(12.dp)
                ) {
                    content()
                }
            }

            Surface(
                modifier = Modifier.weight(0.85f).fillMaxHeight(),
                shape = RoundedCornerShape(26.dp),
                color = LmcBlue.copy(alpha = 0.75f)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "LMC CRAFT",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF365A80)
                    )

                    Spacer(Modifier.size(12.dp))

                    Text(
                        text = "Character Preview",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF365A80)
                    )

                    Spacer(Modifier.size(8.dp))

                    Text(
                        text = "Minecraft Skin",
                        color = Color(0xFF52799F)
                    )
                }
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
        Text(
            text = icon,
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF52799F)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF52799F)
        )
    }
}
