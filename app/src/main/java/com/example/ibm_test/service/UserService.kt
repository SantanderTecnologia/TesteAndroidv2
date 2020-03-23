package com.example.ibm_test.service

import com.example.ibm_test.data.LoginData
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.data.UserItemData
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserService @Inject constructor(private val ibmNetwork: IBMNetwork){
    fun login(loginData: LoginData, onSuccess: (it: UserInfoData) -> Unit, onError: (it: Throwable) -> Unit){
        ibmNetwork.sendInfoToLogin(data = loginData)
            .subscribeOn(Schedulers.io())
            .doOnSuccess(onSuccess)
            .doOnError(onError)
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }

    fun userItems(userId: String, onSuccess: (it: List<UserItemData>) -> Unit, onError: (it: Throwable) -> Unit){
        ibmNetwork.getUserItemInfo(idUser = userId)
            .subscribeOn(Schedulers.io())
            .doOnSuccess(onSuccess)
            .doOnError(onError)
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }
}