package com.qintess.santanderapp.ui.components

import android.content.Context
import android.util.AttributeSet
import com.qintess.santanderapp.helper.Validator

class UserField(ctx: Context, attrs: AttributeSet) : ValidatorEditText(ctx, attrs) {
    override var rule = { value: String -> Validator.isEmailValid(value) || Validator.isCpfValid(value) }
}