package com.br.projetotestesantanter

import org.junit.Assert
import org.junit.Test

class ValidationUtilTest {

    @Test
    fun sucess_validationCpf(){

        val cpf = "009.036.280-23"

       var result = ValidationUtil.isCPF(cpf)

        Assert.assertTrue(result)
    }

    @Test
    fun false_validationCpf(){

        val cpf = "111.111.111-11"

        var result = ValidationUtil.isCPF(cpf)

        Assert.assertFalse(result)
    }

    @Test
    fun white_validationCpf() {
        val cpf = ""


       var result = ValidationUtil.isCPF(cpf)

        Assert.assertFalse(result)

    }
    @Test
    fun sucess_validationPassword() {

        val information = "Q@"

        var result = ValidationUtil.validationPassword(information)

        Assert.assertTrue(result)
    }

    @Test
    fun error_validationPassword() {

        val information = "2345677"

        var result = ValidationUtil.validationPassword(information)

        Assert.assertFalse(result)
    }

    @Test
    fun white_validationPassword() {

        val information = ""

        var result = ValidationUtil.validationPassword(information)

        Assert.assertFalse(result)
    }

}