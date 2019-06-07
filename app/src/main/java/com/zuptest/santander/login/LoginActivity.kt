package com.zuptest.santander.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.zuptest.santander.R
import com.zuptest.santander.getText
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : Activity(), LoginContract.View {

    private val presenter by inject<LoginContract.Presenter> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEditText?.editText?.apply {
            addTextChangedListener {
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        presenter.checkLoginType(charSequence)

                    }
                }
            }
        }

        loginButton?.apply {
            setOnClickListener {
                presenter.doLogin(
                    login = loginEditText?.getText(),
                    password = passwordEditText?.getText()
                )
            }
        }

    }

    override fun displayInvalidPasswordFeedBack() {
        toast(R.string.feedback_invalid_password)
    }

    override fun displayInvalidEmailLoginFeedBack() {
        toast(R.string.feedback_invalid_login)
    }

    override fun displayInvalidCPFLoginFeedBack() {
        toast(R.string.feedback_invalid_cpf)
    }

    override fun applyEmailLogin() {
        loginEditText?.editText?.apply {
            // TODO: criar watcher de email
            addTextChangedListener()
        }
    }

    override fun applyCPFLogin() {
        loginEditText?.editText?.apply {
            // TODO: criar watcher de CPF
            addTextChangedListener()
        }
    }

    override fun displayEmptyPasswordFeedBack() {
        toast(R.string.feedback_empty_password)
    }

    override fun launchStatementsScreen() {
        //TODO: Intent para tela de Statements
    }
}