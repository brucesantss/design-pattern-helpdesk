package br.com.joaocarloslima.design_pattern_helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.observer.ObservadorSolicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.SolicitacaoRepository;
import br.com.joaocarloslima.design_pattern_helpdesk.validation.ValidadorDiaUtil;
import br.com.joaocarloslima.design_pattern_helpdesk.validation.ValidadorFinanceiroHorarioComercial;
import br.com.joaocarloslima.design_pattern_helpdesk.validation.ValidadorMensagemMinima;
import br.com.joaocarloslima.design_pattern_helpdesk.validation.ValidadorSolicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.validation.ValidadorUrgenciaHorario;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private List<ObservadorSolicitacao> observadores;

    public void processar(Solicitacao solicitacao) {

        ValidadorSolicitacao validadorMensagem = new ValidadorMensagemMinima();
        ValidadorSolicitacao validadorDiaUtil = new ValidadorDiaUtil();
        ValidadorSolicitacao validadorUrgencia = new ValidadorUrgenciaHorario();
        ValidadorSolicitacao validadorFinanceiro = new ValidadorFinanceiroHorarioComercial();

        validadorMensagem.setProximo(validadorDiaUtil);
        validadorDiaUtil.setProximo(validadorUrgencia);
        validadorUrgencia.setProximo(validadorFinanceiro);

        validadorMensagem.validar(solicitacao);

        solicitacaoRepository.save(solicitacao);

        for (ObservadorSolicitacao observador : observadores) {
            observador.notificar(solicitacao);
        }
    }
}
