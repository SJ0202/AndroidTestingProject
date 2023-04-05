package com.seongju.androidtestingproject.data.repository

import com.seongju.androidtestingproject.domain.model.UserModel
import com.seongju.androidtestingproject.domain.repository.UserRepository

class FakeUserRepository: UserRepository {

    private val userModelList = mutableListOf<UserModel>()

    override suspend fun upsertUser(userModel: UserModel) {
        userModelList.add(userModel)
    }

    override suspend fun getUser(userName: String, password: String): List<UserModel> {
        userModelList.forEach {
            if (it.userName == userName && it.password == password) {
                return listOf(it)
            }
        }
        return emptyList()
    }
}