package com.seongju.androidtestingproject.domain.use_case.upsert

import com.seongju.androidtestingproject.common.util.Resource
import com.seongju.androidtestingproject.domain.model.UserModel
import com.seongju.androidtestingproject.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(
        userName: String,
        userPassword: String,
        userBirthday: String,
        userPhoneNumber: String,
        createTime: LocalDate,
    ): Flow<Resource<Boolean>> = flow {
        if (userName.isBlank()){
            emit(Resource.Error<Boolean>(message = "이름을 입력해주세요."))
            return@flow
        }
        if (userPassword.isBlank()){
            emit(Resource.Error<Boolean>(message = "비밀번호를 입력해주세요."))
            return@flow
        }
        if (userBirthday.isBlank()) {
            emit(Resource.Error<Boolean>(message = "생년월일을 입력해주세요."))
            return@flow
        }
        if (userPhoneNumber.isBlank()){
            emit(Resource.Error<Boolean>(message = "휴대폰번호를 입력해주세요."))
            return@flow
        }
        // TODO: flow 안에 있는 catch 는 emit 을 허용하지 않으므로, flow.catch 를 이용해 emit 해야 한다.
        //  아래는 잘못 된 예시이다. flow.catch 를 사용한 올바른 예시는 GetUserUseCase 에 있다.
        try {
            val birthday = LocalDate.parse(userBirthday, DateTimeFormatter.BASIC_ISO_DATE)
            userRepository.upsertUser(
                UserModel(
                    userName = userName,
                    password = userPassword,
                    phoneNumber = userPhoneNumber,
                    birthday = birthday,
                    createTime = createTime
                )
            )
            emit(Resource.Success<Boolean>(data = true))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(message = "에러가 발생하였습니다. 입력값을 확인해주세요."))
            return@flow
        }
    }
}