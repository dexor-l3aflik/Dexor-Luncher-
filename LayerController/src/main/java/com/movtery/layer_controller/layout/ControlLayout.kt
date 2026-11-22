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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.movtery.layer_controller.layout

import com.movtery.layer_controller.EDITOR_VERSION
import com.movtery.layer_controller.data.ButtonStyle
import com.movtery.layer_controller.data.JoystickStyle
import com.movtery.layer_controller.data.lang.EmptyTranslatableString
import com.movtery.layer_controller.data.lang.TranslatableString
import com.movtery.layer_controller.data.legacy.LegacySpecial
import com.movtery.layer_controller.layout.ControlLayout.Info
import com.movtery.layer_controller.observable.Modifiable
import com.movtery.layer_controller.observable.isModified
import com.movtery.layer_controller.updateLayoutToNew
import com.movtery.layer_controller.utils.layoutJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

/**
 * Describes a control layout structure.
 * @param info Basic layout information
 * @param layers Control layers
 * @param editorVersion Editor version used
 */
@Serializable
data class ControlLayout(
    @SerialName("info")
    val info: Info,
    @SerialName("layers")
    val layers: List<ControlLayer> = emptyList(),
    @SerialName("styles")
    val styles: List<ButtonStyle> = emptyList(),
    @SerialName("joystickStyles")
    val joystickStyles: List<JoystickStyle> = emptyList(),
    @SerialName("editorVersion")
    val editorVersion: Int
) : Modifiable<ControlLayout> {
    @Serializable
    data class Info(
        @SerialName("name")
        val name: TranslatableString,
        @SerialName("author")
        val author: TranslatableString,
        @SerialName("description")
        val description: TranslatableString,
        @SerialName("versionCode")
        val versionCode: Int,
        @SerialName("versionName")
        val versionName: String
    ) : Modifiable<Info> {
        override fun isModified(other: Info): Boolean {
            return other.name.isModified(this.name) ||
                    other.author.isModified(this.author) ||
                    other.description.isModified(this.description) ||
                    this.versionCode != other.versionCode ||
                    this.versionName != other.versionName
        }
    }

    override fun isModified(other: ControlLayout): Boolean {
        return this.info.isModified(other.info) ||
                this.layers.isModified(other.layers) ||
                this.styles.isModified(other.styles) ||
                this.joystickStyles.isModified(other.joystickStyles) ||
                this.editorVersion != other.editorVersion
    }
}

val EmptyLayoutInfo = Info(
    name = EmptyTranslatableString,
    author = EmptyTranslatableString,
    description = EmptyTranslatableString,
    versionCode = 0,
    versionName = ""
)

val EmptyControlLayout = ControlLayout(
    editorVersion = EDITOR_VERSION,
    info = EmptyLayoutInfo,
    layers = emptyList(),
    styles = emptyList(),
    joystickStyles = emptyList()
)

/**
 * Loads a control layout from file and checks its editor version.
 */
fun loadLayoutFromFile(file: File): ControlLayout {
    val jsonString = file.readText()
    return loadLayoutFromString(jsonString)
}

fun loadLayoutFromString(jsonString: String): ControlLayout {
    val jsonObject = layoutJson.decodeFromString<JsonObject>(jsonString)

    // Some layout files omit editorVersion. Assume the current format
    // and add the missing field so deserialization can proceed.
    val version = jsonObject["editorVersion"]?.jsonPrimitive?.int ?: EDITOR_VERSION

    if (version <= EDITOR_VERSION) {
        val normalizedJsonObject = if (jsonObject["editorVersion"] == null) {
            JsonObject(jsonObject + ("editorVersion" to JsonPrimitive(version)))
        } else {
            jsonObject
        }

        val legacyJoystickStyle = if (version < 12) {
            jsonObject["special"]?.let { specialElement ->
                try {
                    layoutJson.decodeFromJsonElement(
                        LegacySpecial.serializer(),
                        specialElement
                    ).joystickStyle?.toJoystickStyle()
                } catch (_: Exception) {
                    null
                }
            }
        } else {
            null
        }

        // FIX: pass both serializer and JSON element to the API.
        var layout = layoutJson.decodeFromJsonElement(
            ControlLayout.serializer(),
            normalizedJsonObject
        )
        if (version < EDITOR_VERSION) {
            layout = updateLayoutToNew(layout)
        }

        if (legacyJoystickStyle != null) {
            layout = layout.copy(
                joystickStyles = layout.joystickStyles + legacyJoystickStyle
            )
        }

        return layout
    } else {
        throw IllegalArgumentException("Control layout versions are not supported!")
    }
}

/**
 * Loads a control layout from file without checking the version.
 */
fun loadLayoutFromFileUncheck(file: File): ControlLayout {
    val jsonString = file.readText()
    return layoutJson.decodeFromString<ControlLayout>(jsonString)
}
