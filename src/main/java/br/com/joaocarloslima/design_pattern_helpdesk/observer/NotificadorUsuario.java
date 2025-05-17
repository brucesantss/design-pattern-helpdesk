package br.com.joaocarloslima.design_pattern_helpdesk.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Notificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoNotificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.NotificacaoRepository;

@Component
public class NotificadorUsuario implements ObservadorSolicitacao {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Override
    public void notificar(Solicitacao solicitacao) {
        Notificacao notificacao = Notificacao.builder()
                .tipo(TipoNotificacao.USUARIO)
                .mensagem("Solicitação registrada: " + solicitacao.getMensagem())
                .build();
        notificacaoRepository.save(notificacao);
    }
}
