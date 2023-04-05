package com.seongju.androidtestingproject.data.repository

import com.seongju.androidtestingproject.data.database.dao.UserDao
import com.seongju.androidtestingproject.domain.model.UserModel
import com.seongju.androidtestingproject.domain.repository.UserRepository

/**
 * User repository implement
 *
 * @author SeongJu Moon
 * @see UserModel
 */
class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {

    override suspend fun upsertUser(userModel: UserModel) {
        userDao.upsertUser(userModel = userModel)
    }

    override suspend fun getUser(userName: String, password: String): List<UserModel> {
        return userDao.getUser(userName = userName, password = password)
    }

}