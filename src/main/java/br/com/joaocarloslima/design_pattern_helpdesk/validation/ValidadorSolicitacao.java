package br.com.joaocarloslima.design_pattern_helpdesk.validation;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public interface ValidadorSolicitacao {
    void setProximo(ValidadorSolicitacao proximo);
    void validar(Solicitacao solicitacao);
}

