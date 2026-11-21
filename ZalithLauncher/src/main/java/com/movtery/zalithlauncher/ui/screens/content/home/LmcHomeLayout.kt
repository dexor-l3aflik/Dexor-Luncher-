
package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.movtery.zalithlauncher.R

private val LmcBlue = Color(0xFF0868F5)
private val LmcSky = Color(0xFFB8D9FF)
private val LmcText = Color(0xFF405C79)
private val LmcGlass = Color(0xF2FFFFFF)
private val LmcPanel = Color(0xFFDCEEFF)

@Composable
fun LmcHomeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    sideContent: @Composable () -> Unit,
    onHomeClick: () -> Unit = {},
    onGameClick: () -> Unit = {},
    onDownloadsClick: () -> Unit = {},
    onAccountsClick: () -> Unit = {},
    onFilesClick: () -> Unit = {},
    onPlayClick: () -> Unit = {},
    onWardrobeClick: () -> Unit = {},
    onModsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    var selectedItem by remember { mutableStateOf("Play") }

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(LmcSky)
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.dp),
            shape = RoundedCornerShape(28.dp),
            color = LmcGlass
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                LmcRailIcon(
                    icon = R.drawable.ic_home_filled,
                    selected = true,
                    onClick = onHomeClick
                )

                LmcRailIcon(
                    icon = R.drawable.ic_sports_esports_filled,
                    selected = false,
                    onClick = onGameClick
                )

                LmcRailIcon(
                    icon = R.drawable.ic_download_2_filled,
                    selected = false,
                    onClick = onDownloadsClick
                )

                LmcRailIcon(
                    icon = R.drawable.ic_group_filled,
                    selected = false,
                    onClick = onAccountsClick
                )

                LmcRailIcon(
                    icon = R.drawable.ic_folder_filled,
                    selected = false,
                    onClick = onFilesClick
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(7.dp)
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
                            .padding(9.dp)
                    ) {
                        content()
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(28.dp),
                    color = LmcPanel
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(9.dp)
                    ) {
                        sideContent()
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                shape = RoundedCornerShape(26.dp),
                color = LmcGlass
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LmcBottomItem(
                        icon = R.drawable.ic_play_arrow_filled,
                        label = "Play",
                        selected = selectedItem == "Play",
                        onClick = {
                            selectedItem = "Play"
                            onPlayClick()
                        }
                    )

                    LmcBottomItem(
                        icon = R.drawable.ic_person_filled,
                        label = "Accounts",
                        selected = selectedItem == "Accounts",
                        onClick = {
                            selectedItem = "Accounts"
                            onAccountsClick()
                        }
                    )

                    LmcBottomItem(
                        icon = R.drawable.ic_checkroom,
                        label = "Wardrobe",
                        selected = selectedItem == "Wardrobe",
                        onClick = {
                            selectedItem = "Wardrobe"
                            onWardrobeClick()
                        }
                    )

                    LmcBottomItem(
                        icon = R.drawable.ic_extension_outlined,
                        label = "Mods",
                        selected = selectedItem == "Mods",
                        onClick = {
                            selectedItem = "Mods"
                            onModsClick()
                        }
                    )

                    LmcBottomItem(
                        icon = R.drawable.ic_settings_filled,
                        label = "Settings",
                        selected = selectedItem == "Settings",
                        onClick = {
                            selectedItem = "Settings"
                            onSettingsClick()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LmcRailIcon(
    icon: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = if (selected) LmcBlue else Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(23.dp),
                tint = if (selected) Color.White else LmcText
            )
        }
    }
}

@Composable
private fun RowScope.LmcBottomItem(
    icon: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(22.dp))
            .background(
                if (selected) LmcBlue
                else Color.Transparent
            )
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(21.dp),
            tint = if (selected) Color.White else LmcText
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = label,
            color = if (selected) Color.White else LmcText
        )
    }
}
