package com.rick_and_morty.domain.fakes

import kotlinx.coroutines.flow.Flow
import com.rick_and_morty.domain.models.Character
import com.rick_and_morty.domain.models.CharacterLocation
import com.rick_and_morty.domain.models.SettingType
import com.rick_and_morty.domain.models.Settings
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getCharacters(): Flow<List<Character>> = flow {
        val characters = listOf(
            Character(
                "01/02/2021",
                "Male",
                1,
                "https://dummyurl.png",
                CharacterLocation("Earth", "https://dummy.url"),
                "Rick",
                "Human",
                "Alive",
                "",
                "",
                false
            ),
            Character(
                "01/02/2021",
                "Male",
                2,
                "https://dummyurl.png",
                CharacterLocation("Earth", "https://dummy.url"),
                "Morty",
                "Human",
                "Alive",
                "",
                "",
                false
            )
        )
        emit(characters)
    }

    fun getCharacter(): Flow<Character> = flow {
        emit(
            Character(
                "01/02/2021",
                "Male",
                1,
                "https://dummyurl.png",
                CharacterLocation("Earth", "https://dummy.url"),
                "Rick",
                "Human",
                "Alive",
                "",
                "",
                false
            )
        )
    }

    fun getSettings(isNightMode: Boolean): Flow<List<Settings>> = flow {
        val settings = listOf(
            Settings(1, SettingType.SWITCH, "Theme mode", "", isNightMode),
            Settings(2, SettingType.EMPTY, "Clear cache", ""),
            Settings(2, SettingType.TEXT, "App version", "1.0")
        )
        emit(settings)
    }
}
