/*
 * Zalith Launcher 2
 * Copyright (C) 2025-2026 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.movtery.zalithlauncher.utils.animation

import com.movtery.zalithlauncher.setting.LauncherSettings

/**
 * Faster page-switch timeline.
 *
 * The old page no longer holds the new screen behind a long exit + gap delay.
 * Small enter staggering is retained so navigation does not appear abrupt.
 */
object SwapTimeline {
    /** Old-page item exit duration. */
    const val EXIT_ITEM = 20

    /** Old-page fade-out duration. */
    const val EXIT_ALPHA = 35

    /** Old-page item staggering. */
    const val EXIT_STAGGER = 0

    /** Maximum old-page staggering window. */
    const val EXIT_STAGGER_CAP = 0

    /** Total old-page exit window. */
    const val EXIT_WINDOW = EXIT_ITEM + EXIT_STAGGER_CAP

    /** No extra blank delay between old and new pages. */
    const val GAP = 0

    /** New-page item movement duration. */
    const val ENTER_ITEM = 180

    /** New-page fade-in duration. */
    const val ENTER_ALPHA = 120

    /** Shorter staggering between new-page items. */
    const val ENTER_STAGGER = 8

    /** Maximum new-page staggering window. */
    const val ENTER_STAGGER_CAP = 56

    /** General content animation duration. */
    const val CONTENT_BASE = 160

    /** Exit displacement ratio. */
    const val EXIT_DISTANCE_SCALE = 6f / 16f

    fun isSwapClosed(): Boolean = !LauncherSettings.launcherSwapAnimEnabled.state

    fun speedScale(): Float {
        return (LauncherSettings.launcherAnimateSpeedLevel.state / 10f).coerceIn(0.1f, 3f)
    }

    fun time(ms: Int): Int = (ms / speedScale()).toInt()

    fun swapTime(ms: Int): Int = if (isSwapClosed()) 0 else time(ms)

    fun enterStagger(index: Int): Int =
        swapTime(minOf(index * ENTER_STAGGER, ENTER_STAGGER_CAP))

    fun exitStagger(index: Int): Int =
        swapTime(minOf(index * EXIT_STAGGER, EXIT_STAGGER_CAP))
}

/**
 * Delay window for one navigation transition.
 */
class SwapWindow(
    val exitDelayMillis: Int,
    val enterDelayMillis: Int
)

object SwapClock {
    @Volatile
    private var swapInitial: Any? = null

    @Volatile
    private var swapTarget: Any? = null

    @Volatile
    private var exitEndAtMillis: Long = 0

    @Volatile
    var enterAtMillis: Long = 0
        private set

    @Synchronized
    fun record(initial: Any?, target: Any?): SwapWindow {
        val now = System.nanoTime() / 1_000_000
        if (initial != target) {
            val continuing = now < enterAtMillis &&
                (initial == swapInitial || initial == swapTarget ||
                    target == swapInitial || target == swapTarget)

            if (!continuing) {
                swapInitial = initial
                swapTarget = target
                exitEndAtMillis = now + SwapTimeline.swapTime(SwapTimeline.EXIT_WINDOW)
                enterAtMillis = exitEndAtMillis + SwapTimeline.swapTime(SwapTimeline.GAP)
            }
        }

        return SwapWindow(
            exitDelayMillis = (exitEndAtMillis - now).coerceAtLeast(0).toInt(),
            enterDelayMillis = (enterAtMillis - now).coerceAtLeast(0).toInt()
        )
    }
}
