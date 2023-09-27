package com.rickandmorty.cache.fakes

import com.rickandmorty.cache.fakes.FakeValueFactory.randomBoolean
import com.rickandmorty.cache.fakes.FakeValueFactory.randomInt
import com.rickandmorty.cache.fakes.FakeValueFactory.randomString
import com.rickandmorty.data.models.CharacterEntity
import com.rickandmorty.data.models.CharacterLocationEntity

object FakeCacheData {

    fun getFakeCharacterEntity(
        size: Int,
        isRandomId: Boolean = true,
        isBookmarked: Boolean = false
    ): List<CharacterEntity> {
        val characters = mutableListOf<CharacterEntity>()
        repeat(size) {
            characters.add(createCharacterEntity(isRandomId, isBookmarked))
        }
        return characters
    }

    private fun createCharacterEntity(isRandomId: Boolean, isBookmarked: Boolean): CharacterEntity {
        return CharacterEntity(
            created = randomString(),
            gender = randomString(),
            id = if (isRandomId) randomInt() else 1,
            image = randomString(),
            characterLocation = CharacterLocationEntity(
                name = randomString(),
                url = randomString()
            ),
            name = randomString(),
            species = randomString(),
            status = randomString(),
            randomString(),
            url = randomString(),
            isBookMarked = if (isBookmarked) true else randomBoolean()
        )
    }
}
