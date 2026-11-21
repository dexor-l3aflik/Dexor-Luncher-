/*
 * Zalith Launcher 2
 * Copyright (C) 2025-2026 MovTery and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 */

package com.movtery.zalithlauncher.ui.screens.content.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.movtery.cardgrid.model.CardLimits
import com.movtery.cardgrid.model.CardType
import com.movtery.zalithlauncher.ui.screens.content.home.server.ServerCardContent
import com.movtery.zalithlauncher.ui.screens.content.home.version.VersionCardContent

class SystemCard(
    val id: String,
    val content: @Composable () -> Unit
)

val CardIconShape = androidx.compose.foundation.shape.RoundedCornerShape(
    percent = 28
)

object HomeCards {
    const val VERSION_CARD_TYPE_ID = "version_card"
    const val SERVER_CARD_TYPE_ID = "server_card"

    private val versionCardType = CardType(
        typeId = VERSION_CARD_TYPE_ID,
        defaultSpan = IntOffset(10, 6),
        limits = CardLimits(
            minWidth = 10,
            minHeight = 4,
            maxHeight = 12
        ),
        content = { cardId ->
            VersionCardContent(cardId)
        }
    )

    private val serverCardType = CardType(
        typeId = SERVER_CARD_TYPE_ID,
        defaultSpan = IntOffset(10, 6),
        limits = CardLimits(
            minWidth = 10,
            minHeight = 4,
            maxHeight = 12
        ),
        content = { cardId ->
            ServerCardContent(cardId)
        }
    )

    fun versionCardType(): CardType = versionCardType

    fun serverCardType(): CardType = serverCardType

    val userCardTypes: List<CardType> = listOf(
        versionCardType,
        serverCardType
    )

    // LMC CRAFT home par debug warning nahi dikhana.
    fun systemCards(): List<SystemCard> = emptyList()
}