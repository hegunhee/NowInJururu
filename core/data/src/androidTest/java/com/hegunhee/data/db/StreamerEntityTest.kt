package com.hegunhee.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hegunhee.data.database.StreamerDatabase
import com.hegunhee.data.database.dao.StreamerDao
import com.hegunhee.data.database.entity.StreamerEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class StreamerEntityTest {

    private lateinit var streamerDao: StreamerDao
    private lateinit var db: StreamerDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, StreamerDatabase::class.java
        ).build()
        streamerDao = db.streamerDao()
    }

    @Throws
    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun givenThreeEntities_whenInsertAll_thenReturnThreeEntities() = runBlocking {
        // Given
        val expectedEntities = createStreamerEntities(3)
        for (entity in expectedEntities) {
            streamerDao.insertStreamer(entity)
        }

        // When
        val entities = streamerDao.getAllStreamerEntityList()

        // Then
        assertEquals(expectedEntities.size, entities.size)
    }

    @Test
    fun givenOneEntity_whenUpdateAlertFlag_thenReturnUpdatedEntity() = runBlocking {
        // Given
        val entityName = "name"
        val entity = StreamerEntity(entityName, isAlert = true)
        streamerDao.insertStreamer(entity)

        // When
        streamerDao.updateStreamer(entity.copy(isAlert = !entity.isAlert))
        val updatedEntity = streamerDao.getAllStreamerEntityList().first { it.streamerLogin == entityName }

        // Then
        assertTrue(entity.isAlert != updatedEntity.isAlert)
    }

    @Test
    fun givenOneEntity_whenDeleteEntity_thenReturnEmptyList() = runBlocking {
        // Given
        val entity = createStreamerEntity(0)
        streamerDao.insertStreamer(entity)

        // When
        streamerDao.deleteStreamer(entity)
        val entities = streamerDao.getAllStreamerEntityList()

        // Then
        assertTrue(entities.isEmpty())
        assertTrue(!entities.contains(entity))
    }

    private fun createStreamerEntities(size: Int): List<StreamerEntity> {
        return (0 until size).map { number ->
            createStreamerEntity(number)
        }
    }

    private fun createStreamerEntity(offset: Int): StreamerEntity {
        return StreamerEntity(
            streamerLogin = "name${offset}",
            isAlert = offset % 2 == 0,
        )
    }

}
