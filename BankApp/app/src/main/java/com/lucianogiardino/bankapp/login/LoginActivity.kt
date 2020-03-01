package com.lucianogiardino.bankapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lucianogiardino.bankapp.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val loginPresenter: LoginContract.Presenter by inject { parametersOf(this, applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            loginPresenter.validate(etUsername.text.toString(),etPassword.text.toString())
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun goToStatement() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

