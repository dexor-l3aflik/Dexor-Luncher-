package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movtery.zalithlauncher.R

object LmcColors {
    val Sky = Color(0xFFB9D9FF)
    val SkyLight = Color(0xFFDCEEFF)
    val Blue = Color(0xFF0868F5)
    val BlueDark = Color(0xFF0757D5)
    val Text = Color(0xFF405C79)
    val TextMuted = Color(0xFF7189A2)
    val Glass = Color(0xEFFFFFFF)
    val Panel = Color(0xE8F5FAFF)
    val Divider = Color(0xFFD3E3F4)
    val TopBar = Color(0xFFAED2FA)
}

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

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.lmc_light_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    end = 12.dp,
                    top = 2.dp,
                    bottom = 8.dp
                )
        ) {
            // COMPACT BRAND PILL: target reference is approximately 138 x 35 dp.
            Surface(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(138.dp)
                    .height(35.dp),
                shape = RoundedCornerShape(25.dp),
                color = Color.White.copy(alpha = 0.93f),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.lmc_craft_logo),
                        contentDescription = "LMC CRAFT logo",
                        modifier = Modifier.size(29.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(7.dp))

                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "LMC CRAFT",
                            color = LmcColors.Text,
                            fontSize = 15.sp,
                            lineHeight = 17.sp,
                            maxLines = 1
                        )
                        Text(
                            text = "Java Launcher",
                            color = LmcColors.TextMuted,
                            fontSize = 10.sp,
                            lineHeight = 11.sp,
                            maxLines = 1
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // LEFT NAVIGATION RAIL
                Surface(
                    modifier = Modifier
                        .width(54.dp)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(30.dp),
                    color = Color.White.copy(alpha = 0.90f),
                    shadowElevation = 2.dp
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

                // MAIN CONTENT AREA
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .weight(1.5f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(31.dp),
                            color = LmcColors.Glass,
                            shadowElevation = 2.dp
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
                            shape = RoundedCornerShape(31.dp),
                            color = LmcColors.Panel,
                            shadowElevation = 2.dp
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

                    // BOTTOM NAVIGATION: target height approximately 66 dp.
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(66.dp),
                        shape = RoundedCornerShape(31.dp),
                        color = Color.White.copy(alpha = 0.93f),
                        shadowElevation = 2.dp
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
                            LmcBottomDivider()
                            LmcBottomItem(
                                icon = R.drawable.ic_person_filled,
                                label = "Accounts",
                                selected = selectedItem == "Accounts",
                                onClick = {
                                    selectedItem = "Accounts"
                                    onAccountsClick()
                                }
                            )
                            LmcBottomDivider()
                            LmcBottomItem(
                                icon = R.drawable.ic_checkroom,
                                label = "Wardrobe",
                                selected = selectedItem == "Wardrobe",
                                onClick = {
                                    selectedItem = "Wardrobe"
                                    onWardrobeClick()
                                }
                            )
                            LmcBottomDivider()
                            LmcBottomItem(
                                icon = R.drawable.ic_extension_outlined,
                                label = "Mods",
                                selected = selectedItem == "Mods",
                                onClick = {
                                    selectedItem = "Mods"
                                    onModsClick()
                                }
                            )
                            LmcBottomDivider()
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
    }
}

@Composable
private fun LmcRailIcon(
    icon: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(if (selected) LmcColors.Blue else Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(23.dp),
            tint = if (selected) Color.White else LmcColors.Text
        )
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
            .clip(RoundedCornerShape(24.dp))
            .background(if (selected) LmcColors.Blue else Color.Transparent)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(21.dp),
            tint = if (selected) Color.White else LmcColors.Text
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            color = if (selected) Color.White else LmcColors.Text,
            fontSize = 13.sp,
            maxLines = 1
        )
    }
}

@Composable
private fun LmcBottomDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(29.dp)
            .background(LmcColors.Divider)
    )
}
