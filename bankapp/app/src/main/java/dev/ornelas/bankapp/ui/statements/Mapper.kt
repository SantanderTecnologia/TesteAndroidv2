package dev.ornelas.bankapp.ui.statements

import dev.ornelas.banckapp.domain.model.Mapper
import dev.ornelas.banckapp.domain.model.Statement
import dev.ornelas.bankapp.commons.formatDate

object StatementToStatementView : Mapper<Statement, StatementViewModel> {
    override fun map(type: Statement?): StatementViewModel {
        return StatementViewModel(
            title = type!!.title,
            value = type.value.toString(),
            date = type.date?.formatDate(),
            desc = type.desc
        )
    }
}