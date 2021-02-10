package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.model.entity.*;
import br.com.sankhyamt.integracaotarget.util.LogFile;

import java.sql.SQLException;

public class CiotService {

    static TransportadorService transportadorService = new TransportadorService();
    static MotoristaService motoristaService = new MotoristaService();
    static ParticipanteService participanteService = new ParticipanteService();

    public String[] ciotCenario3(Viagem viagem){

        String[] dadosCiot = new String[2];

        Long nroCiot = 0l;

        Transportador transportador;
        Motorista motorista;
        Participante participante;
        OperacaoTransporte operacaoTransporte;

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

            operacaoTransporte = OperacaoTransporteService.buscarDadosOperacaoTranporte(viagem);

            operacaoTransporte.setIdOperacaoTransporte(OperacaoTransporteService
                    .cadastrarAtualizarOperacaoTransporte(
                            operacaoTransporte,transportador,motorista,participante,viagem));

             nroCiot = OperacaoTransporteService.declararOperacaoTranporte(operacaoTransporte);

            dadosCiot[0] = nroCiot.toString();
            dadosCiot[1] = operacaoTransporte.getIdOperacaoTransporte().toString();

            return dadosCiot;

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao gerar CIOT\n".concat(e.getMessage()));

            throw new DatabaseException(e.getMessage());

        } catch (Exception e) {

            LogFile.logger.info("Erro ao gerar CIOT\n".concat(e.getMessage()));

        }

        return null;
    }
}
