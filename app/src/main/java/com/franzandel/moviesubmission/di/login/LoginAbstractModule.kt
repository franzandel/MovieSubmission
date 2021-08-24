package com.franzandel.moviesubmission.di.login

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.data.local.LoginLocalData
import com.franzandel.moviesubmission.data.local.LoginLocalDataImpl
import com.franzandel.moviesubmission.data.remote.LoginRemoteData
import com.franzandel.moviesubmission.data.remote.LoginRemoteDataImpl
import com.franzandel.moviesubmission.data.remote.mapper.LoginReqDTOMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitLoginResMapper
import com.franzandel.moviesubmission.data.remote.model.LoginReqDTO
import com.franzandel.moviesubmission.data.remote.model.LoginResDTO
import com.franzandel.moviesubmission.data.repository.LoginRepositoryImpl
import com.franzandel.moviesubmission.domain.model.LoginReq
import com.franzandel.moviesubmission.domain.repository.LoginRepository
import com.franzandel.moviesubmission.domain.usecase.LoginUseCase
import com.franzandel.moviesubmission.domain.usecase.LoginUseCaseImpl
import com.franzandel.moviesubmission.presentation.login.vm.LoginVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginVM::class)
    abstract fun provideLoginVM(loginVM: LoginVM): ViewModel

    @Binds
    @ViewModelScoped
    abstract fun provideLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @ViewModelScoped
    abstract fun provideLoginReqDTOMapper(loginReqDTOMapper: LoginReqDTOMapper): BaseMapper<LoginReq, LoginReqDTO>

    @Binds
    @ViewModelScoped
    abstract fun provideRetrofitLoginResMapper(retrofitLoginResMapper: RetrofitLoginResMapper): RetrofitResMapper<LoginResDTO, String>

    @Binds
    @ViewModelScoped
    abstract fun provideLoginRemoteData(loginRemoteDataImpl: LoginRemoteDataImpl): LoginRemoteData

    @Binds
    @ViewModelScoped
    abstract fun provideLoginLocalData(loginLocalDataImpl: LoginLocalDataImpl): LoginLocalData
}