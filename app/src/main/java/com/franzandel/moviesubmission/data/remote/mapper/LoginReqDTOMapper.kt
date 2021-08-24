package com.franzandel.moviesubmission.data.remote.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.remote.model.LoginReqDTO
import com.franzandel.moviesubmission.domain.model.LoginReq
import javax.inject.Inject

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

class LoginReqDTOMapper @Inject constructor() : BaseMapper<LoginReq, LoginReqDTO>() {

    override fun map(dataModel: LoginReq): LoginReqDTO =
        with(dataModel) {
            LoginReqDTO(
                username, password
            )
        }
}