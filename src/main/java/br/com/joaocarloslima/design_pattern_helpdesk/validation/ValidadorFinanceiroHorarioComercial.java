package br.com.joaocarloslima.design_pattern_helpdesk.validation;

import java.time.LocalTime;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoSolicitacao;

public class ValidadorFinanceiroHorarioComercial implements ValidadorSolicitacao {
    private ValidadorSolicitacao proximo;

    @Override
    public void setProximo(ValidadorSolicitacao proximo) {
        this.proximo = proximo;
    }

    @Override
    public void validar(Solicitacao solicitacao) {
        LocalTime agora = LocalTime.now();
        if (solicitacao.getTipo() == TipoSolicitacao.FINANCEIRO &&
                (agora.isBefore(LocalTime.of(8, 0)) || agora.isAfter(LocalTime.of(18, 0)))) {
            throw new SolicitacaoInvalidaException("Solicitação financeira só pode ser processada em horário comercial");
        }
        if (proximo != null) proximo.validar(solicitacao);
    }
}
