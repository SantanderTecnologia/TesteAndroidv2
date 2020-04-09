package pt.felipegouveia.bankapp.presentation.login.entity.mapper

import pt.felipegouveia.bankapp.domain.common.Mapper
import pt.felipegouveia.bankapp.domain.model.Login
import pt.felipegouveia.bankapp.presentation.login.entity.LoginPresentation

/**
 * Map the domain entity retrieved from (data/domain) layer to presentation entity.
 */
class LoginPresentationMapper : Mapper<Login, LoginPresentation>() {

    override fun mapFrom(data: Login): LoginPresentation =
        mapLoginToPresentation(data)

    private fun mapLoginToPresentation(domain: Login): LoginPresentation =
        LoginPresentation(
            userAccount = domain.userAccount,
            error = domain.error
        )
}