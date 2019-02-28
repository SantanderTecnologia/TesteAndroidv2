package com.example.androidtest.presentation.login

import com.example.androidtest.repository.SuccessResponse
import com.example.androidtest.repository.UserRepository


interface LoginInteractorContract {
    fun requestLogin(user: String, pass: String)
}

class LoginInteractor(private val presenter: LoginPresenterContract) : LoginInteractorContract {

    override fun requestLogin(user: String, pass: String) {

        if (user.isBlank() || pass.isBlank()) {
            presenter.invalidInputForm()
            return
        }

        presenter.requestInProgress()
        UserRepository.loginCall(user, pass) {
            when (it) {
                is SuccessResponse -> {
                    presenter.loginSuccessfull()
                }

                else -> {
                    presenter.loginFailed(it.msg)
                }
            }
        }

    }

}
