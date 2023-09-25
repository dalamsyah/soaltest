package com.example.soaltest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "age") val age: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}