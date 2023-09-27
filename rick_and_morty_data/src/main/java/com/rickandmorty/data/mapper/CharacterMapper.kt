package com.rickandmorty.data.mapper

import com.rickandmorty.data.models.CharacterEntity
import javax.inject.Inject
import com.rick_and_morty.domain.models.Character

class CharacterMapper @Inject constructor(
    private val locationMapper: CharacterLocationMapper
) : Mapper<CharacterEntity, Character> {

    override fun mapFromEntity(type: CharacterEntity): Character {
        return Character(
            created = type.created,
            gender = type.gender,
            id = type.id,
            image = type.image,
            characterLocation = locationMapper.mapFromEntity(type.characterLocation),
            name = type.name,
            species = type.species,
            status = type.status,
            type = type.type,
            url = type.url,
            isBookMarked = type.isBookMarked
        )
    }

    override fun mapToEntity(type: Character): CharacterEntity {
        return CharacterEntity(
            created = type.created,
            gender = type.gender,
            id = type.id,
            image = type.image,
            characterLocation = locationMapper.mapToEntity(type.characterLocation),
            name = type.name,
            species = type.species,
            status = type.status,
            type = type.type,
            url = type.url,
            isBookMarked = type.isBookMarked
        )
    }
}
