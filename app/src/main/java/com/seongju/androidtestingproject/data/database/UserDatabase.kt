package com.seongju.androidtestingproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seongju.androidtestingproject.data.database.dao.UserDao
import com.seongju.androidtestingproject.data.database.type_converter.LocalDataConverter
import com.seongju.androidtestingproject.domain.model.UserModel

/**
 * User Database
 *
 * @author SeongJu Moon
 * @see UserModel
 * @see LocalDataConverter
 */
@Database(
    entities = [UserModel::class],
    version = 1
)
@TypeConverters(LocalDataConverter::class)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

}