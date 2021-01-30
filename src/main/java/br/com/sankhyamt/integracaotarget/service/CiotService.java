package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.model.entity.Motorista;
import br.com.sankhyamt.integracaotarget.model.entity.Participante;
import br.com.sankhyamt.integracaotarget.model.entity.Transportador;
import br.com.sankhyamt.integracaotarget.model.entity.Viagem;
import br.com.sankhyamt.integracaotarget.util.LogFile;

import java.sql.SQLException;

public class CiotService {

    static TransportadorService transportadorService = new TransportadorService();
    static MotoristaService motoristaService = new MotoristaService();
    static ParticipanteService participanteService = new ParticipanteService();

    public String ciotCenario3(Viagem viagem){

        String nroCiot = "";

        Transportador transportador;
        Motorista motorista;
        Participante participante;

        try {

            transportador = transportadorService.buscarDadosTransportador(
                    viagem.getCodAfretamento(),
                    viagem.getOrdemCarga(),
                    viagem.getCodEmp());

            transportador.setIdTransportador(transportadorService
                    .cadastrarAtualizarTransportador(transportador));

            motorista = motoristaService.buscarDadosMotorista(
                    viagem.getOrdemCarga(), viagem.getCodEmp());

            motorista.setIdMotorista(motoristaService
                    .cadastrarAtualizarMotorista(motorista,transportador));

            participante = participanteService.buscarDadosParticipante(
                    viagem.getOrdemCarga(),viagem.getCodEmp());

            participante.setIdParticipante(participanteService
                    .cadastrarAtualizarParticipante(participante));

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao gerar CIOT\n".concat(e.getMessage()));

            throw new DatabaseException(e.getMessage());

        } catch (Exception e) {
            LogFile.logger.info("Erro ao gerar CIOT\n".concat(e.getMessage()));

        }

        return nroCiot;
    }
}
