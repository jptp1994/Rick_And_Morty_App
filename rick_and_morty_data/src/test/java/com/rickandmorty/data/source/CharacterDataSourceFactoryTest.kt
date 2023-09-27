package com.rickandmorty.data.source

import com.rickandmorty.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.rickandmorty.data.repository.CharacterCache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterDataSourceFactoryTest : DataBaseTest() {

    @Mock
    lateinit var characterCache: CharacterCache

    @Mock
    lateinit var dataSourceCache: CharacterCacheDataSource

    @Mock
    lateinit var dataSourceRemote: CharacterRemoteDataSource

    private lateinit var sut: CharacterDataSourceFactory

    @Before
    fun setUp() {
        sut = CharacterDataSourceFactory(characterCache, dataSourceCache, dataSourceRemote)
    }

    @Test
    fun `get data store with cache should return characters from cache data-source`() =
        dispatcher.runTest {
            // Arrange (Given)
            val isCached = true
            `when`(characterCache.isExpired()) doReturn false
            // Act (When)
            val dataSource = sut.getDataStore(isCached)
            // Assert (Then)
            assertThat(dataSource, instanceOf(CharacterCacheDataSource::class.java))
            verify(characterCache, times(1)).isExpired()
        }

    @Test
    fun `get data store with cache should return characters from remote data-source`() =
        dispatcher.runTest {
            // Arrange (Given)
            val isCached = true
            `when`(characterCache.isExpired()) doReturn true
            // Act (When)
            val dataSource = sut.getDataStore(isCached)
            // Assert (Then)
            assertThat(dataSource, instanceOf(CharacterRemoteDataSource::class.java))
            verify(characterCache, times(1)).isExpired()
        }
}
