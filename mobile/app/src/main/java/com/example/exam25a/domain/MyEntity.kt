package com.example.exam25a.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entities")
data class MyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "group")
    var group: String,
    @ColumnInfo(name = "details")
    var details: String,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "students")
    var students: Int,
    @ColumnInfo(name = "type")
    var type: String
)