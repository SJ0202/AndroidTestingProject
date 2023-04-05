package com.seongju.androidtestingproject.domain.use_case.get

import com.google.common.truth.Truth.assertThat
import com.seongju.androidtestingproject.common.util.Resource
import com.seongju.androidtestingproject.data.repository.FakeUserRepository
import com.seongju.androidtestingproject.domain.model.UserModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetUserUseCaseTest {

    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {

        fakeUserRepository = FakeUserRepository()
        getUserUseCase = GetUserUseCase(userRepository = fakeUserRepository)

        val userToInsert = mutableListOf<UserModel>()
        userToInsert.add(
            UserModel(
                userName = "TestUser1",
                createTime = LocalDate.now(),
                birthday = LocalDate.now(),
                phoneNumber = "010-1234-5678",
                password = "1234"
            )
        )

        (0..10).forEach {index ->
            userToInsert.add(
                UserModel(
                    userName = "TestUser$index",
                    createTime = LocalDate.now(),
                    birthday = LocalDate.now(),
                    phoneNumber = "010-1234-5678",
                    password = "1234"
                )
            )
        }
        userToInsert.shuffle()
        runBlocking {
            userToInsert.forEach {
                fakeUserRepository.upsertUser(it)
            }
        }
    }

    @Test
    fun `Get local database user`() = runBlocking {
        println("========== TestStart ==========")
        getUserUseCase(userName = "TestUser1", password = "1234").collect{ result ->
            when(result) {
                is Resource.Error -> {
                    println(result.message)
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println(result.data)
                    // result 의 userName 과 Get 을 시도한 userName 이 같은지 확인
                    assertThat(result.data!![0].userName).isEqualTo("TestUser1")
                }
            }
        }
        println("========== TestStop ==========")
    }

    @Test
    fun `Get local database nonexistent user`() = runBlocking {
        println("========== TestStart ==========")
        getUserUseCase(userName = "TestUser50", password = "1235").collect{ value ->
            when(value) {
                is Resource.Error -> {
                    println(value.message)
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println(value.data)
                }
            }
        }
        println("========== TestStop ==========")
    }

    @Test
    fun `Get local database userName null`() = runBlocking {
        println("========== TestStart ==========")
        getUserUseCase(userName = "", password = "1235").collect{ value ->
            when(value) {
                is Resource.Error -> {
                    println(value.message)
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println(value.data)
                }
            }
        }
        println("========== TestStop ==========")
    }

    @Test
    fun `Get local database password null`() = runBlocking {
        println("========== TestStart ==========")
        getUserUseCase(userName = "TestUser1", password = "").collect{ value ->
            when(value) {
                is Resource.Error -> {
                    println(value.message)
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println(value.data)
                }
            }
        }
        println("========== TestStop ==========")
    }
}