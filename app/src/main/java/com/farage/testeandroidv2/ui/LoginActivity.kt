package com.farage.testeandroidv2.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.farage.testeandroidv2.R
import com.farage.testeandroidv2.di.KodeinContainers
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.farage.testeandroidv2.viewmodel.UserViewModel
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.newInstance
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginActivity : AppCompatActivity() {


    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViewModel()
        observeViewModel()
        handleLoginAction()
    }

    private fun initViewModel() {
        viewModel = KodeinContainers.diBaseProject.newInstance {
            UserViewModel(instance(), instance())
        }
    }

    private fun observeViewModel() {
        viewModel.let {
            it.getScreenStateLiveData.observe(this, Observer { data ->
                onResultReceived(data)
            })
            it.getRouterScreen.observe(this, Observer { intent ->
                changeScreen(intent)
            })
        }

    }

    private fun onResultReceived(result: ResultState<UserAccount>?) {
        when (result?.resultType) {
            ResultType.SUCCESS -> successLogin(result.data)
            ResultType.ERROR -> errorLogin(result.message)
            ResultType.EMPTY_DATA -> emptyDataLogin()
        }
    }

    private fun changeScreen(intent: Intent?) {
        startActivity(intent)
        finish()
    }

    private fun successLogin(data: UserAccount?) {
        viewModel.callLoginRouter(this, data)
    }

    private fun errorLogin(message: String?) {
        Snackbar.make(layout, message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun emptyDataLogin() {
        Snackbar.make(layout, "Dados não encontrados", Snackbar.LENGTH_SHORT).show()
    }

    private fun handleLoginAction() {
        login_btn_login.setOnClickListener {
            viewModel.callHandleUserLogin(login_et_email.text.toString(), login_et_password.text.toString())
        }
    }

}
