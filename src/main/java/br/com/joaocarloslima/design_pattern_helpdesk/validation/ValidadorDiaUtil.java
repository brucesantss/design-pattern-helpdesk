package br.com.joaocarloslima.design_pattern_helpdesk.validation;

import java.time.DayOfWeek;
import java.time.LocalDate;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public class ValidadorDiaUtil implements ValidadorSolicitacao {
    private ValidadorSolicitacao proximo;

    @Override
    public void setProximo(ValidadorSolicitacao proximo) {
        this.proximo = proximo;
    }

    @Override
    public void validar(Solicitacao solicitacao) {
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        if (hoje == DayOfWeek.SATURDAY || hoje == DayOfWeek.SUNDAY) {
            throw new SolicitacaoInvalidaException("Solicitação não pode ser processada em fins de semana");
        }
        if (proximo != null) proximo.validar(solicitacao);
    }
}
