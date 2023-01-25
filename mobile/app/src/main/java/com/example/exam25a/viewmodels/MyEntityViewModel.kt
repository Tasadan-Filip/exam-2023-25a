package com.example.exam25a.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.exam25a.db.MyEntityRoomDatabase
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.repository.MyEntityRepository
import com.example.exam25a.utils.logd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class MyEntityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyEntityRepository

    var allEntities: List<MyEntity>? = null

    init {
        val dao = MyEntityRoomDatabase.getDatabase(application).myEntityDao()
        repository = MyEntityRepository(dao)
    }

    suspend fun getAll(): List<MyEntity> {
        allEntities = repository.getAllEntities()
        return allEntities!!
    }

    fun insert(entity: MyEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entity)
        logd("In MyEntityViewModel - inserted entity")
    }

    fun update(entity: MyEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(entity)
        logd("In MyEntityViewModel - updated entity")
    }

    fun insertAll(entities: List<MyEntity>) = viewModelScope.launch(Dispatchers.IO){
        repository.insertAll(entities)
        logd("In MyEntityViewModel - inserted all")
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        logd("In MyEntityViewModel - deleted all")
    }

    fun confirm(entityId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.confirm(entityId)
        logd("In MyEntityViewModel - confirmed entity")
    }
}