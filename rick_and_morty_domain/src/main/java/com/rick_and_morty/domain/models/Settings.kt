package com.rick_and_morty.domain.models

data class Settings(
    val id: Int,
    val type: SettingType,
    val settingLabel: String,
    val settingValue: String,
    var selectedValue: Boolean = false
)

enum class SettingType {
    SWITCH,
    TEXT,
    EMPTY
}
