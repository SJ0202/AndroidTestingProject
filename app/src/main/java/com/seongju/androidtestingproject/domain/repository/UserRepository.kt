package com.seongju.androidtestingproject.domain.repository

import com.seongju.androidtestingproject.domain.model.UserModel

/**
 * User repository interface
 *
 * @author SeongJu Moon
 * @see UserModel
 */
interface UserRepository {

    suspend fun upsertUser(userModel: UserModel)

    suspend fun getUser(userName: String, password: String): List<UserModel>

}