if (!inLauncherScreen) {
    TopBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        mainScreenKey = mainScreenKey,
        inLauncherScreen = inLauncherScreen,
        taskRunning = taskRunning,
        isTasksExpanded = isTaskMenuExpanded,
        contentColor = onBackgroundColor(),
        onScreenBack = {
            screenBackStackModel.mainScreen.backStack.removeFirstOrNull()
        },
        toMainScreen = toMainScreen,
        toSettingsScreen = {
            screenBackStackModel.mainScreen.removeAndNavigateTo(
                removes = screenBackStackModel.clearBeforeNavKeys,
                screenKey = screenBackStackModel.settingsScreen
            )
        },
        toDownloadScreen = {
            screenBackStackModel.navigateToDownload()
        },
        toMultiplayerScreen = {
            screenBackStackModel.mainScreen.removeAndNavigateTo(
                removes = screenBackStackModel.clearBeforeNavKeys,
                screenKey = NormalNavKey.Multiplayer
            )
        },
        openFileManager = {
            eventViewModel.sendEvent(
                EventViewModel.Event.OpenFileManager(
                    rootPath = PathManager.DIR_FILES_EXTERNAL.absolutePath
                )
            )
        },
        changeExpandedState = {
            changeTasksExpandedState()
        },
    )
}