package com.rick_and_morty.domain.interactor

import com.rick_and_morty.domain.fakes.FakeData
import com.rick_and_morty.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rick_and_morty.domain.repository.CharacterRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetBookmarkCharacterListTestDomain : DomainBaseTest() {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var sut: GetBookmarkCharacterListUseCase

    @Before
    fun setUp() {
        sut = GetBookmarkCharacterListUseCase(characterRepository)
    }

    @Test
    fun `get bookmark character should return success result with bookmark character list`() =
        dispatcher.runTest {
            // Arrange (Given)
            whenever(characterRepository.getBookMarkedCharacters()) doReturn FakeData.getCharacters()

            // Act (When)
            val characters = sut(Unit).single()

            // Assert (Then)
            assertEquals(characters.size, 2)
            verify(characterRepository, times(1)).getBookMarkedCharacters()
        }

    @Test
    fun `get bookmark characters should return error result with exception`() =
        dispatcher.runTest {
            // Arrange (Given)
            whenever(characterRepository.getBookMarkedCharacters()) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut(Unit).single()
            }catch (exception:IOException){

                // Assert (Then)
                assertThat(
                    exception,
                    instanceOf(IOException::class.java)
                )
            }


            verify(characterRepository, times(1)).getBookMarkedCharacters()
        }
}
