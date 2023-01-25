package com.example.exam25a.repository

import android.util.AndroidRuntimeException
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.domain.MyId
import com.example.exam25a.utils.logd

object NetworkRepository {
    suspend fun getAll(): List<MyEntity>? {
        try {
            val result = MyEntityApi.entityService.getAll()
            logd("Available : $result")
            return result
        } catch (ex: Exception) {
            return null
        }
    }

    suspend fun getDraft(): List<MyEntity>? {
        try {
            val result = MyEntityApi.entityService.getDraft()
            logd("Available : $result")
            return result
        } catch (ex: Exception) {
            return null
        }
    }

    suspend fun getGroup(group: String): List<MyEntity>? {
        try {
            val result = MyEntityApi.entityService.getGroup(group)
            logd("Available : $result")
            return result
        } catch (ex: Exception) {
            return null
        }
    }

    suspend fun save(entity: MyEntity): Int {
        try {
            logd("In save NetworkRepository")
            val result = MyEntityApi.entityService.save(entity)
            logd("save - NetworkRepo: ${result.body().toString()}")
            if (result.isSuccessful) {
                logd("Return id: ${result.body()!!.id}")
                return result.body()!!.id
            } else {
                logd("Error Message: ${result.body()!!.text}")
                return 0
            }
        } catch (ex: Exception) {
            logd("Exception...")
            return -1
        } catch (ex: AndroidRuntimeException) {
            logd("AndroidRuntimeException...")
            return -2
        }
    }

    suspend fun join(entityId: MyId): Int {
        try {
            logd("In join NetworkRepository")
            val result = MyEntityApi.entityService.join(entityId)
            logd("save - NetworkRepo: ${result.body().toString()}")
            if (result.isSuccessful) {
                logd("Return id: ${result.body()!!.id}")
                return result.body()!!.id
            } else {
                logd("Error Message: ${result.body()!!.text}")
                return 0
            }
        } catch (ex: Exception) {
            logd("Exception...")
            return -1
        } catch (ex: AndroidRuntimeException) {
            logd("AndroidRuntimeException...")
            return -2
        }
    }

    suspend fun getOne(entityId: Int): MyEntity? {
        try {
            val result = MyEntityApi.entityService.getOne(entityId)
            logd("Available : $result")
            return result
        } catch (ex: Exception) {
            return null
        }
    }
}