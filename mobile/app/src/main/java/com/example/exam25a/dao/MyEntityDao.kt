package com.example.exam25a.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.exam25a.domain.MyEntity

@Dao
interface MyEntityDao {

    @Query("SELECT * FROM entities")
    suspend fun getAll(): List<MyEntity>

    @Query("SELECT * FROM entities WHERE id=:id")
    suspend fun getOne(id: Int): MyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myEntity: MyEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myEntity: MyEntity)

    @Query("DELETE FROM entities WHERE id=:id")
    suspend fun delete(id: Int): Int

    @Query("DELETE FROM entities")
    suspend fun deleteAll()

    @Query("UPDATE entities SET status = 1 WHERE id=:id")
    suspend fun confirm(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<MyEntity>)
}