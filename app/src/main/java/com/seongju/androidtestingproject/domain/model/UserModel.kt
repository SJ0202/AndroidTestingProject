package com.seongju.androidtestingproject.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "user")
data class UserModel(
    val userName: String,
    val createTime: LocalDate,
    val birthday: LocalDate,
    val phoneNumber: String,
    val password: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)