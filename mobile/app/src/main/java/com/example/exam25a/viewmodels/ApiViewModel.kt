package com.example.exam25a.viewmodels

import androidx.lifecycle.ViewModel
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.domain.MyId
import com.example.exam25a.repository.NetworkRepository
import com.example.exam25a.utils.logd

class ApiViewModel: ViewModel() {

    suspend fun getAll(): List<MyEntity>? {
        val result = NetworkRepository.getAll()
        logd("In Model getAll: $result")
        return result
    }

    suspend fun getDraft(): List<MyEntity>? {
        val result = NetworkRepository.getDraft()
        logd("In Model getDraft: $result")
        return result
    }

    suspend fun getGroup(group: String): List<MyEntity>? {
        val result = NetworkRepository.getGroup(group)
        logd("In Model getGroup: $result")
        return result
    }

    suspend fun save(entity: MyEntity): Int {
        val result = NetworkRepository.save(entity)
        logd("In Model save: $result")
        return result
    }

    suspend fun join(entityId: Int): Int {
        val entity = MyId(entityId)
        val result = NetworkRepository.join(entity)
        logd("In Model save: $result")
        return result
    }

    suspend fun getOne(entityId: Int): MyEntity {
        val result = NetworkRepository.getOne(entityId)
        logd("In Model getOne: $result")
        return result!!
    }

//    suspend fun confirm(entityId: Int): Int {
//        val result = NetworkRepository.confirm(entityId)
//        logd("In Model confirm: $result")
//        return result
//    }
}