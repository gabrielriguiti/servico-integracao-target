package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.model.entity.Viagem;
import br.com.sankhyamt.integracaotarget.util.LogFile;

import java.sql.*;

/**
 * Classe com os serviços relacionados às parcelas.
 *
 * @since v1.0
 */
public class ParcelaService {

    /**
     * Método que busca as parcelas do afretamento na base de dado SANKHYA,
     * e define o request das parcelas.
     * @param viagem Recebe um objet Viagem com os dados do afretamento.
     * @return Retorno uma String com o request das parcelas.
     */
    public static String buscarParcelasAfretamento(Viagem viagem){

        StringBuilder parcelasRequest = new StringBuilder();

        Connection connection = null;

        String sql = "SELECT * FROM VWPARCELASAFT \n" +
                "WHERE ORDEMCARGA = ? AND CODAFT = ? AND CODEMP = ?";

        try{
            connection = ConnectionSQLServer.connection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,viagem.getOrdemCarga());
            statement.setString(2,viagem.getCodAfretamento());
            statement.setString(3,viagem.getCodEmp());

            ResultSet resultQuery = statement.executeQuery();

            while (resultQuery.next()){

                parcelasRequest.append("<tms:OperacaoTransporteParcelaRequest>\n" +
                        "                  <tms:DescricaoParcela>" + resultQuery.getString("DESCRICAO").trim() + "</tms:DescricaoParcela>\n" +
                        "                  <tms:Valor>" + resultQuery.getDouble("VALOR") + "</tms:Valor>\n" +
                        "                  <tms:NumeroParcela>" + resultQuery.getInt("NROPARCELA") + "</tms:NumeroParcela>\n" +
                        "                  <tms:DataVencimento>" + resultQuery.getString("DTVENC").trim() + "</tms:DataVencimento>\n" +
                        "                  <tms:TipoDaParcela>" + resultQuery.getInt("TIPPARCELA") + "</tms:TipoDaParcela>\n" +
                        "                  <tms:FormaPagamento>" + resultQuery.getInt("FORMAPGTO") + "</tms:FormaPagamento>\n" +
                        "                  <tms:CartaoPagamento></tms:CartaoPagamento>\n" +
                        "                  <tms:CodigoBanco>" + resultQuery.getInt("CODBCO") + "</tms:CodigoBanco>\n" +
                        "                  <tms:AgenciaDeposito>" + resultQuery.getInt("CODAG") + "</tms:AgenciaDeposito>\n" +
                        "                  <tms:ContaDeposito>" + resultQuery.getInt("NUMCTA") + "</tms:ContaDeposito>\n" +
                        "                  <tms:DigitoContaDeposito></tms:DigitoContaDeposito>\n" +
                        "                  <tms:ProcessarAutomaticamente>true</tms:ProcessarAutomaticamente>\n" +
                        "                  <tms:IdOperacaoTransporteParcela>0</tms:IdOperacaoTransporteParcela>\n" +
                        "                  <tms:FlagContaPoupanca>" + (resultQuery.getString("CTAPOUP").trim().equals("S")) + "</tms:FlagContaPoupanca>\n" +
                        "                  <tms:VariacaoContaPoupanca></tms:VariacaoContaPoupanca>\n" +
                        "                  <tms:ItemFinanceiroParcela></tms:ItemFinanceiroParcela>\n" +
                        "               </tms:OperacaoTransporteParcelaRequest>");
            }

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao buscar as parcelas do afretamento: " + e.getMessage());

            throw new DatabaseException(e.getMessage());
        }

        return parcelasRequest.toString();
    }
}
