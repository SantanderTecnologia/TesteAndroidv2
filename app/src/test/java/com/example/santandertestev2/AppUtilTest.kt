package com.example.santandertestev2

import com.example.santandertestev2.domain.Util.AppUtil
import org.junit.Assert
import org.junit.Test

class AppUtilTest {

    @Test
    fun password_regex_is_wrong_should_be_uppercase_lowercase_number_alphanumeric(){

        val rightPass = "Asd123@"
        val result1 = AppUtil.validatePassword(rightPass)
        Assert.assertTrue(result1)

        val wrongPass = "aaa123@"
        val result2 = AppUtil.validatePassword(wrongPass)
        Assert.assertFalse(result2)

        val wrongPass2 = "AAA123@"
        val result3 = AppUtil.validatePassword(wrongPass2)
        Assert.assertFalse(result3)

        val wrongPass3 = "Aaa123hju"
        val result4 = AppUtil.validatePassword(wrongPass3)
        Assert.assertFalse(result4)
    }

    @Test
    fun an_invalid_cpf_was_accept(){
        val result = AppUtil.isCPFvalid("645.376.456-37")
        Assert.assertFalse(result)
    }

    @Test
    fun an_valid_cpf_is_rejected(){
        val result1 = AppUtil.isCPFvalid("097.852.780-18")
        Assert.assertTrue(result1)

        val result2 = AppUtil.isCPFvalid("56927543040")
        Assert.assertTrue(result2)
    }

    @Test
    fun email_invalid_was_accepted(){
        val email = "asdasd.com"
        val result = AppUtil.isEmailValid(email)
        Assert.assertFalse(result)
    }

    @Test
    fun email_valid_was_rejected(){
        var email = "test@test.com"
        Assert.assertTrue(AppUtil.isEmailValid(email))

        email = "test@test.com.br"
        Assert.assertTrue(AppUtil.isEmailValid(email))

        email = "test@test.en"
        Assert.assertTrue(AppUtil.isEmailValid(email))
    }

    @Test
    fun instance_should_not_be_null(){
        val instance = AppUtil()
        Assert.assertNotNull(instance)
    }

}