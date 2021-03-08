package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.model.entity.VeiculoTransporte;
import br.com.sankhyamt.integracaotarget.model.entity.Viagem;
import br.com.sankhyamt.integracaotarget.util.LogFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 1.0
 * @since v1.0
 */
public class VeiculoTransporteService {

    /**
     * Realiza a busca da composição do veículo da viagem.
     * @param viagem Recebe um objeto viagem.
     * @return Retorno um objeto VeículoComposto.
     */
    public static VeiculoTransporte buscarComposicaoVeiculo(Viagem viagem){

        VeiculoTransporte veiculoTransporte = new VeiculoTransporte();

        Connection connection = null;

        String sql = "SELECT * FROM VWCOMPVEIC \n" +
                "WHERE ORDEMCARGA = ? AND CODEMP = ?";
        try {

            connection = ConnectionSQLServer.connection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,viagem.getOrdemCarga());
            statement.setString(2,viagem.getCodEmp());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){

                veiculoTransporte.setPlacaCavalo(resultSet.getString("PLACACAVALO"));
                veiculoTransporte.setRNTRCCavalo(resultSet.getString("RNTRCAVALO"));
                veiculoTransporte.setPlacaCarreta1(resultSet.getString("PLACACARRETA1"));
                veiculoTransporte.setRNTRCCarreta1(resultSet.getString("RNTRCCARRETA1"));
                veiculoTransporte.setPlacaCarreta2(resultSet.getString("PLACACARRETA2"));
                veiculoTransporte.setRNTRCCarreta2(resultSet.getString("RNTRCCARRETA2"));
                veiculoTransporte.setPlacaCarreta3(resultSet.getString("PLACACARRETA3"));
                veiculoTransporte.setRNTRCCarreta3(resultSet.getString("RNTRCCARRETA3"));
            }

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao buscar composíção do veículo : " + e.getMessage());

            throw new DatabaseException(e.getMessage());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {

            LogFile.logger.info("Erro ao buscar composíção do veículo : " + e.getMessage());
        }

        return veiculoTransporte;
    }

    /**
     * Formata o XML Request dos veículos da operação.
     * @param viagem Recebe um objeto viagem.
     * @return Retorna o XML Request dos veículos da operação.
     */
    public static String requestVeiculos(Viagem viagem){

        VeiculoTransporte veiculoTransporte = buscarComposicaoVeiculo(viagem);

        StringBuilder requestVeiculo = new StringBuilder();

        if (!veiculoTransporte.getPlacaCavalo().contains("AAA0000")){
            requestVeiculo.append("<tms:OperacaoTransporteVeiculoRequest>\n" +
                    "                  <tms:Placa>" + veiculoTransporte.getPlacaCavalo().trim() + "</tms:Placa>\n" +
                    "                  <tms:RNTRC>" + veiculoTransporte.getRNTRCCavalo().trim() + "</tms:RNTRC>\n" +
                    "               </tms:OperacaoTransporteVeiculoRequest>\n".trim());
        }

        if (!veiculoTransporte.getPlacaCarreta1().contains("AAA0000")){
            requestVeiculo.append("<tms:OperacaoTransporteVeiculoRequest>\n" +
                    "                  <tms:Placa>" + veiculoTransporte.getPlacaCarreta1().trim() + "</tms:Placa>\n" +
                    "                  <tms:RNTRC>" + veiculoTransporte.getRNTRCCarreta1().trim() + "</tms:RNTRC>\n" +
                    "               </tms:OperacaoTransporteVeiculoRequest>\n".trim());
        }

        if (!veiculoTransporte.getPlacaCarreta2().contains("AAA0000")){
            requestVeiculo.append("<tms:OperacaoTransporteVeiculoRequest>\n" +
                    "                  <tms:Placa>" + veiculoTransporte.getPlacaCarreta2().trim() + "</tms:Placa>\n" +
                    "                  <tms:RNTRC>" + veiculoTransporte.getRNTRCCarreta2().trim() + "</tms:RNTRC>\n" +
                    "               </tms:OperacaoTransporteVeiculoRequest>\n".trim());
        }

        if (!veiculoTransporte.getPlacaCarreta3().contains("AAA0000")){
            requestVeiculo.append("<tms:OperacaoTransporteVeiculoRequest>\n" +
                    "                  <tms:Placa>" + veiculoTransporte.getPlacaCarreta3().trim() + "</tms:Placa>\n" +
                    "                  <tms:RNTRC>" + veiculoTransporte.getRNTRCCarreta3().trim() + "</tms:RNTRC>\n" +
                    "               </tms:OperacaoTransporteVeiculoRequest>\n".trim());
        }

        return requestVeiculo.toString();
    }
}
