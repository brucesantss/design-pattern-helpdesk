package br.com.joaocarloslima.design_pattern_helpdesk.validation;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public class ValidadorMensagemMinima implements ValidadorSolicitacao {
    private ValidadorSolicitacao proximo;

    @Override
    public void setProximo(ValidadorSolicitacao proximo) {
        this.proximo = proximo;
    }

    @Override
    public void validar(Solicitacao solicitacao) {
        if (solicitacao.getMensagem() == null || solicitacao.getMensagem().length() < 10) {
            throw new SolicitacaoInvalidaException("Mensagem deve ter no mínimo 10 caracteres");
        }
        if (proximo != null) proximo.validar(solicitacao);
    }
}

