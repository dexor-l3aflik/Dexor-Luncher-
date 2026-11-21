/*
 * Zalith Launcher 2
 * Copyright (C) 2025-2026 MovTery and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 */

package com.movtery.zalithlauncher.ui.screens.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movtery.zalithlauncher.game.account.AccountsManager
import com.movtery.zalithlauncher.game.version.installed.Version
import com.movtery.zalithlauncher.game.version.installed.VersionsManager
import com.movtery.zalithlauncher.path.PathManager
import com.movtery.zalithlauncher.ui.base.BaseScreen
import com.movtery.zalithlauncher.ui.guide.GuideKeys
import com.movtery.zalithlauncher.ui.screens.NestedNavKey
import com.movtery.zalithlauncher.ui.screens.NormalNavKey
import com.movtery.zalithlauncher.ui.screens.navigateOnce
import com.movtery.zalithlauncher.ui.screens.content.home.LmcHomeLayout
import com.movtery.zalithlauncher.ui.screens.content.home.LmcLaunchPanel
import com.movtery.zalithlauncher.ui.screens.content.home.LmcCharacterPreview
import com.movtery.zalithlauncher.viewmodel.ScreenBackStackViewModel

@Suppress("UNUSED_PARAMETER")
@Composable
fun LauncherScreen(
    backStackViewModel: ScreenBackStackViewModel,
    navigateToVersions: (Version) -> Unit,
    onLaunchGame: (Version?) -> Unit,
    onQuickPlayServer: (Version?, String) -> Unit,
    onOpenLink: (String) -> Unit,
    startGuideOnce: (GuideKeys.Keys) -> Unit
) {
    BaseScreen(
        screenKey = NormalNavKey.LauncherMain,
        currentKey = backStackViewModel.mainScreen.currentKey
    ) {
        val account by AccountsManager.currentAccountFlow
            .collectAsStateWithLifecycle()

        val toAccountManageScreen: () -> Unit = {
            backStackViewModel.mainScreen.navigateTo(
                screenKey = NormalNavKey.AccountManager(
                    FirstLoginMenu.NONE
                )
            )
        }

        val toVersionManageScreen: () -> Unit = {
            backStackViewModel.mainScreen.removeAndNavigateTo(
                remove = NestedNavKey.VersionSettings::class,
                screenKey = NormalNavKey.VersionsManager
            )
        }

        val toVersionSettingsScreen: () -> Unit = {
            VersionsManager.currentVersion.value?.let { version ->
                navigateToVersions(version)
            }
        }

        val toSettingsScreen: () -> Unit = {
            backStackViewModel.mainScreen.removeAndNavigateTo(
                removes = backStackViewModel.clearBeforeNavKeys,
                screenKey = backStackViewModel.settingsScreen
            )
        }

        val toGamepadScreen: () -> Unit = {
            toSettingsScreen()

            backStackViewModel.settingsScreen.backStack.navigateOnce(
                NormalNavKey.Settings.Gamepad
            )
        }

        val toModsScreen: () -> Unit = {
            val version = VersionsManager.currentVersion.value

            if (version == null) {
                toVersionManageScreen()
            } else {
                navigateToVersions(version)

                val versionSettings =
                    backStackViewModel.mainScreen.backStack.lastOrNull()
                        as? NestedNavKey.VersionSettings

                versionSettings?.backStack?.navigateOnce(
                    NormalNavKey.Versions.ModsManager
                )
            }
        }

        val toDownloadsScreen: () -> Unit = {
            backStackViewModel.mainScreen.removeAndNavigateTo(
                removes = backStackViewModel.clearBeforeNavKeys,
                screenKey = backStackViewModel.downloadScreen
            )
        }

        val toFilesScreen: () -> Unit = {
            backStackViewModel.mainScreen.backStack.navigateToFileSelector(
                startPath = PathManager.DIR_FILES_EXTERNAL.absolutePath,
                selectFile = false,
                saveKey = NormalNavKey.LauncherMain,
                onSelected = {}
            )
        }

        CompositionLocalProvider(
            LocalUriHandler provides object : UriHandler {
                override fun openUri(uri: String) {
                    onOpenLink(uri)
                }
            }
        ) {
            LmcHomeLayout(
                modifier = Modifier.fillMaxSize(),

                // LEFT: PLAYER + JAVA VERSION + LAUNCH
                content = {
                    LmcLaunchPanel(
                        account = account,
                        onAccountClick = toAccountManageScreen,
                        onOpenVersions = toVersionManageScreen,
                        onVersionSettings = toVersionSettingsScreen,
                        onLaunchGame = onLaunchGame,
                        modifier = Modifier.fillMaxSize()
                    )
                },

                // RIGHT: LIVE MINECRAFT PLAYER SKIN
                sideContent = {
                    LmcCharacterPreview(
                        account = account,
                        onWardrobeClick = toAccountManageScreen,
                        modifier = Modifier.fillMaxSize()
                    )
                },

                // LEFT SIDEBAR
                onHomeClick = {
                    backStackViewModel.mainScreen.clearWith(
                        NormalNavKey.LauncherMain
                    )
                },

                onGameClick = toGamepadScreen,

                onDownloadsClick = toDownloadsScreen,

                onAccountsClick = toAccountManageScreen,

                onFilesClick = toFilesScreen,

                // BOTTOM NAVIGATION
                onPlayClick = {
                    backStackViewModel.mainScreen.clearWith(
                        NormalNavKey.LauncherMain
                    )
                },

                onWardrobeClick = toAccountManageScreen,

                onModsClick = toModsScreen,

                onSettingsClick = toSettingsScreen
            )
        }
    }
}