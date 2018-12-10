package br.com.rphmelo.bankapp.presentation.currency

import br.com.rphmelo.bankapp.presentation.currency.models.OnCurrencyLoadDataListener

class CurrencyInteractor {

    val STATUS_CODE_SUCCESS = 200;

    fun loadStatementData(statusCode: Int, listener: OnCurrencyLoadDataListener) {
        when {
            statusCode == STATUS_CODE_SUCCESS -> listener.onLoadStatementListSuccess()
            else -> listener.onLoadStatementListError()
        }
    }

    fun loadToolbarData(statusCode: Int, listener: OnCurrencyLoadDataListener) {
        when {
            statusCode == STATUS_CODE_SUCCESS -> listener.onToolbarLoadDataSuccess()
            else -> listener.onToolbarLoadDataError()
        }
    }

    fun setupToolbar(listener: OnCurrencyLoadDataListener) {
        listener.onSetupToolbar()
    }

}