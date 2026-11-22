package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movtery.petlib.PetAnimation
import com.movtery.petlib.PetTapReaction
import com.movtery.zalithlauncher.R
import com.movtery.zalithlauncher.game.account.Account
import com.movtery.zalithlauncher.game.account.AccountsManager
import com.movtery.zalithlauncher.game.account.wardrobe.toArmModel
import com.movtery.zalithlauncher.game.version.installed.Version
import com.movtery.zalithlauncher.game.version.installed.VersionsManager
import com.movtery.zalithlauncher.ui.components.PetModelView
import com.movtery.zalithlauncher.ui.screens.content.elements.PlayerFace
import com.movtery.zalithlauncher.ui.screens.content.elements.VersionIconImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun LmcLaunchPanel(
    account: Account?,
    onAccountClick: () -> Unit,
    onOpenVersions: () -> Unit,
    onVersionSettings: () -> Unit,
    onLaunchGame: (Version?) -> Unit,
    modifier: Modifier = Modifier
) {
    val version by VersionsManager.currentVersion.collectAsStateWithLifecycle()
    val versions by VersionsManager.versions.collectAsStateWithLifecycle()
    val isRefreshing by VersionsManager.isRefreshing.collectAsStateWithLifecycle()
    var showVersionMenu by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 17.dp, vertical = 3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        // ACCOUNT PROFILE
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(18.dp))
                .clickable(onClick = onAccountClick)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            if (account != null) {
                PlayerFace(account = account, avatarSize = 43.dp)
            } else {
                Box(
                    modifier = Modifier
                        .size(43.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LmcColors.SkyLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Add account",
                        tint = LmcColors.Blue,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (account == null) "Welcome" else "Welcome Back,",
                    color = LmcColors.Text,
                    fontSize = 15.sp,
                    maxLines = 1
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = account?.username ?: "Add Account",
                        color = LmcColors.Text,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (account != null) {
                        Box(
                            modifier = Modifier
                                .size(17.dp)
                                .clip(CircleShape)
                                .background(LmcColors.Blue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_check),
                                contentDescription = "Active account",
                                tint = Color.White,
                                modifier = Modifier.size(11.dp)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.94f))
                    .clickable(onClick = onAccountClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_drop_down_rounded),
                    contentDescription = "Account options",
                    tint = LmcColors.Text,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        HorizontalDivider(color = LmcColors.Divider, thickness = 1.dp)

        Text(
            text = "Minecraft Version",
            color = LmcColors.Text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )

        // JAVA IS ACTIVE. RUST IS DISPLAYED AS A FUTURE/UNAVAILABLE OPTION.
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            shape = RoundedCornerShape(22.dp),
            color = LmcColors.SkyLight
        ) {
            Row(
                modifier = Modifier.padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .background(LmcColors.Blue),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_coffee_filled),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Java Edition",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚛",
                        color = LmcColors.TextMuted,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Rust Edition",
                        color = LmcColors.TextMuted,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
            }
        }

        // VERSION PICKER
        Box {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                shape = RoundedCornerShape(18.dp),
                color = LmcColors.SkyLight
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { showVersionMenu = true }
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VersionIconImage(
                        version = version,
                        modifier = Modifier.size(34.dp)
                    )

                    Spacer(modifier = Modifier.width(9.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = version?.getVersionName() ?: "No installed versions",
                            color = LmcColors.Text,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = when {
                                isRefreshing -> "Refreshing versions..."
                                version == null -> "Open version manager to install"
                                else -> version?.let { selectedVersion ->
                                    if (selectedVersion.isValid()) {
                                        selectedVersion.getVersionSummary()
                                    } else {
                                        "Version requires attention"
                                    }
                                } ?: "No installed versions"
                            },
                            color = LmcColors.TextMuted,
                            fontSize = 10.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_drop_down_rounded),
                        contentDescription = "Choose version",
                        tint = LmcColors.Text,
                        modifier = Modifier.size(23.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = showVersionMenu,
                onDismissRequest = { showVersionMenu = false },
                modifier = Modifier.width(275.dp)
            ) {
                if (versions.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text("No installed versions") },
                        onClick = {
                            showVersionMenu = false
                            onOpenVersions()
                        }
                    )
                } else {
                    versions.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(
                                        text = item.getVersionName(),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = item.getVersionSummary(),
                                        fontSize = 12.sp
                                    )
                                }
                            },
                            onClick = {
                                VersionsManager.saveVersion(item)
                                showVersionMenu = false
                            }
                        )
                    }
                }

                HorizontalDivider()

                DropdownMenuItem(
                    text = { Text("Manage versions") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_download_2_filled),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        showVersionMenu = false
                        onOpenVersions()
                    }
                )

                DropdownMenuItem(
                    text = { Text("Version settings") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_settings_filled),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        showVersionMenu = false
                        onVersionSettings()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // FULL-WIDTH LAUNCH BUTTON
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF55BDFF),
                            LmcColors.Blue,
                            LmcColors.BlueDark
                        )
                    )
                )
                .clickable { onLaunchGame(version) },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_play_arrow_filled),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(21.dp)
                )
                Spacer(modifier = Modifier.width(9.dp))
                Text(
                    text = "Launch",
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(9.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_dashboard_filled),
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.82f),
                    modifier = Modifier.size(19.dp)
                )
            }
        }
    }
}

@Composable
fun LmcCharacterPreview(
    account: Account?,
    onWardrobeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val refreshWardrobe by AccountsManager.refreshWardrobe.collectAsStateWithLifecycle()
    val armModel = account?.skinModelType?.toArmModel()
    val skinStamp = account?.getSkinFile()?.lastModified()
    val capeStamp = account?.getCapeFile()?.lastModified()

    val skinBytes by produceState<ByteArray?>(
        initialValue = null,
        account,
        refreshWardrobe,
        skinStamp,
        armModel
    ) {
        value = withContext(Dispatchers.IO) {
            account?.getSkinFile()
                ?.takeIf { it.exists() }
                ?.inputStream()
                ?.use { it.readBytes() }
        }
    }

    val capeBytes by produceState<ByteArray?>(
        initialValue = null,
        account,
        refreshWardrobe,
        capeStamp
    ) {
        value = withContext(Dispatchers.IO) {
            account?.getCapeFile()
                ?.takeIf { it.exists() }
                ?.inputStream()
                ?.use { it.readBytes() }
        }
    }

    var showFullscreen by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .padding(start = 7.dp, end = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(19.dp),
                color = Color(0xFFDCEEFF)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_checkroom),
                        contentDescription = null,
                        tint = LmcColors.Text,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Character",
                        color = LmcColors.Text,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .clickable { showFullscreen = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_fullscreen_exit),
                    contentDescription = "Expand character",
                    tint = LmcColors.Text,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        // PetLib scales from viewport width. This height-first 44:35 viewport
        // fits the player's projected height without cropping the head or feet.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 3.dp, vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            PetModelView(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(
                        ratio = 44f / 35f,
                        matchHeightConstraintsFirst = true
                    ),
                skin = skinBytes,
                cape = capeBytes,
                arm = armModel,
                yaw = Math.toRadians(35.0).toFloat(),
                animation = PetAnimation.Idle,
                tapReaction = PetTapReaction.Interact,
                dragYaw = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LmcCharacterAction(
                icon = R.drawable.ic_refresh,
                description = "Refresh skin",
                onClick = { AccountsManager.refreshWardrobe() }
            )
            Spacer(modifier = Modifier.width(13.dp))
            LmcCharacterAction(
                icon = R.drawable.ic_checkroom,
                description = "Open wardrobe",
                onClick = onWardrobeClick
            )
        }
    }

    if (showFullscreen) {
        Dialog(onDismissRequest = { showFullscreen = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(490.dp),
                shape = RoundedCornerShape(27.dp),
                color = LmcColors.Glass
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Character Preview",
                            color = LmcColors.Text,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { showFullscreen = false }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = "Close",
                                tint = LmcColors.Text
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        PetModelView(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(
                                    ratio = 44f / 35f,
                                    matchHeightConstraintsFirst = true
                                ),
                            skin = skinBytes,
                            cape = capeBytes,
                            arm = armModel,
                            yaw = Math.toRadians(35.0).toFloat(),
                            animation = PetAnimation.Idle,
                            tapReaction = PetTapReaction.Interact,
                            dragYaw = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LmcCharacterAction(
    icon: Int,
    description: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(39.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.94f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = description,
            tint = LmcColors.Text,
            modifier = Modifier.size(22.dp)
        )
    }
}
