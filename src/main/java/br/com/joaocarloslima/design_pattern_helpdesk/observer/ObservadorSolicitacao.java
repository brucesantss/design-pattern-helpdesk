package br.com.joaocarloslima.design_pattern_helpdesk.observer;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public interface ObservadorSolicitacao {
    void notificar(Solicitacao solicitacao);
}
