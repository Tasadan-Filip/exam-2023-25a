package com.example.exam25a.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.exam25a.dao.MyEntityDao
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.utils.logd

class MyEntityRepository(private val dao: MyEntityDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllEntities(): List<MyEntity> {
        val result = dao.getAll()
        logd("Get all - MyEntityRepository - ${result}")
        return result
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getOne(entityId: Int): MyEntity {
        val result = dao.getOne(entityId)
        logd("Get all - MyEntityRepository - ${result}")
        return result
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        logd("Deleting all - MyEntityRepository")
        return dao.deleteAll()
    }

    @WorkerThread
    suspend fun insertAll(entities: List<MyEntity>) {
        logd("Inserting all - MyEntityRepository")
        dao.insertAll(entities)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(entity: MyEntity): Long {
        val result = dao.insert(entity)
        logd("Insert - MyEntityRepository - $result")
        return result
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(entity: MyEntity) {
        logd("Update - MyEntityRepository")
        dao.update(entity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(id: Int) {
        logd("Delete - MyEntityRepository")
        dao.delete(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun confirm(entityId: Int) {
        dao.confirm(entityId)
        logd("Confirm - MyEntityRepository  - $entityId")
    }
}