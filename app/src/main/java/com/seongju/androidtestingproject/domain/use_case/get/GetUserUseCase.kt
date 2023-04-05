package com.seongju.androidtestingproject.domain.use_case.get

import com.seongju.androidtestingproject.common.util.Resource
import com.seongju.androidtestingproject.domain.model.UserModel
import com.seongju.androidtestingproject.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(userName: String, password: String): Flow<Resource<List<UserModel>>> = flow {
        emit(Resource.Loading<List<UserModel>>())
        if (userName.isBlank()) {
            emit(Resource.Error<List<UserModel>>(message = "이름을 입력해주세요."))
            return@flow
        }
        if (password.isBlank()) {
            emit(Resource.Error<List<UserModel>>(message = "비밀번호를 입력해주세요."))
            return@flow
        }
        val result = userRepository.getUser(userName = userName, password = password)
        if (result.isNotEmpty()) {
            emit(Resource.Success<List<UserModel>>(data = result))
        } else {
            emit(Resource.Error<List<UserModel>>(message = "회원 정보가 없습니다."))
            return@flow
        }
    }.catch { e ->
        when(e) {
            is IOException -> {
                emit(Resource.Error<List<UserModel>>(message = "네트워크 에러가 발생하였습니다."))
            }
            else -> {
                emit(Resource.Error<List<UserModel>>(message = "에러가 발생하였습니다."))
            }
        }
    }
}