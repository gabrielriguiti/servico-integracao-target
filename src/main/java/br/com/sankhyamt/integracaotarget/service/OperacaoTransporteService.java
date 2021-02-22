package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.exception.IntegracaoException;
import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.model.entity.*;
import br.com.sankhyamt.integracaotarget.properties.AuthProperties;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import br.com.sankhyamt.integracaotarget.util.LogSankhya;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.rmi.runtime.Log;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Classe com os serviços da Operação de Transporte.
 *
 * @since v1.0
 */
public class OperacaoTransporteService {

    static AuthProperties authProperties = new AuthProperties();

    /**
     * Esse método busca os dados da Operação de Tranporte na base de dados SANKHYA.
     *
     * @param viagem - Recebe um objeto Viagem
     * @return - Retorna um objeto OperacaoTransporte com os dados da operação.
     */
    public static OperacaoTransporte buscarDadosOperacaoTranporte(Viagem viagem) {

        OperacaoTransporte operacaoTransporte = new OperacaoTransporte();

        Connection connection = null;

        String sql = "SELECT * FROM WVDADOSOPTRANS\n" +
                "WHERE ORDEMCARGA = ? AND CODEMP = ? AND CODAFT = ?";

        try {
            connection = ConnectionSQLServer.connection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, viagem.getOrdemCarga());
            statement.setString(2, viagem.getCodEmp());
            statement.setString(3, viagem.getCodAfretamento());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                operacaoTransporte.setProprietarioCarga(resultSet.getInt("PROPCARGA"));
                operacaoTransporte.setPesoCarga(resultSet.getDouble("PESOCARGA"));
                operacaoTransporte.setMunicipioOrigemCodigoIBGE(resultSet.getInt("CODIBGEORIG"));
                operacaoTransporte.setMunicipioDestinoCodigoIBGE(resultSet.getInt("CODIBGEDEST"));
                operacaoTransporte.setDataHoraInicio(resultSet.getString("DTINIC").trim());
                operacaoTransporte.setDataHoraTermino(resultSet.getString("DTFIM").trim());
                operacaoTransporte.setValorFrete(resultSet.getDouble("VLRFRETE"));
                operacaoTransporte.setValorPedagio(resultSet.getDouble("VLRPEDAGIO"));
                operacaoTransporte.setValorImpostoSestSenat(resultSet.getDouble("VLRSESTSENAT"));
                operacaoTransporte.setValorImpostoIRRF(resultSet.getDouble("VLRIRRF"));
                operacaoTransporte.setValorImpostoINSS(resultSet.getDouble("VLRINSS"));
                operacaoTransporte.setValorDescontoAntecipado(resultSet.getDouble("VLRTAXASEGURO"));
                operacaoTransporte.setParcelaUnica((resultSet.getString("PARCELAUNICA")).equals("S"));
                operacaoTransporte.setModoCompraValePedagio(resultSet.getInt("FORMAPGTOPEDAGIO"));
                operacaoTransporte.setCategoriaVeiculo(resultSet.getInt("CATEGVEIC"));
                operacaoTransporte.setDeduzirImpostos(resultSet.getString("TIPPESSOA").equals("F"));
                operacaoTransporte.setIdIntegrador(resultSet.getString("IDINTEGRADOR"));
                operacaoTransporte.setNumeroCartaoValePedagio(resultSet.getString("CARTAOPEDAGIO"));
                operacaoTransporte.setCEPOrigem(resultSet.getString("CEPORIGEM"));
                operacaoTransporte.setCEPDestino(resultSet.getString("CEPDESTINO"));
                operacaoTransporte.setTipoCargaANTT(resultSet.getInt("TIPCARGAANTT"));
                operacaoTransporte.setDistanciaPercorrida(resultSet.getDouble("DISTANCIA"));
            }

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao buscar dados da operação de transporte: " + e.getMessage());

            throw new DatabaseException("Erro ao buscar dados da operação de transporte: " + e.getMessage());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {

            LogFile.logger.info("Erro ao buscar as parcelas do afretamento: " + e.getMessage());
        }

        return operacaoTransporte;
    }

    public static Integer cadastrarAtualizarOperacaoTransporte(
            OperacaoTransporte operacaoTransporte, Transportador transportador,
            Motorista motorista, Participante participante, Viagem viagem) {

        Integer idOperacaoTransporte = 0;

        Random id = new Random();

        final String url = "https://dev.transportesbra.com.br/frete/TMS/FreteService.svc";

        StringBuilder request = new StringBuilder();

        request.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\" xmlns:arr=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\"\n" +
                "xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:CadastrarAtualizarOperacaoTransporte>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:operacao>\n" +
                "            <tms:Instrucao>1</tms:Instrucao>\n" +
                (operacaoTransporte.getIdOperacaoTransporte().equals(0) ? "            <tms:IdOperacaoTransporte i:nil=\"true\"/>" :
                        "           <tms:IdOperacaoTransporte>" + operacaoTransporte.getIdOperacaoTransporte() + "</tms:IdOperacaoTransporte>") + "\n" +
                "            <tms:CodigoCentroDeCusto>" + operacaoTransporte.getCodCentroDeCusto() + "</tms:CodigoCentroDeCusto>\n" +
                "            <tms:NCM>" + operacaoTransporte.getNCM() + "</tms:NCM>\n" +
                "            <tms:ProprietarioCarga>" + operacaoTransporte.getProprietarioCarga() + "</tms:ProprietarioCarga>\n" +
                "            <tms:PesoCarga>" + operacaoTransporte.getPesoCarga() + "</tms:PesoCarga>\n" +
                "            <tms:TipoOperacao>" + operacaoTransporte.getTipoOperacao() + "</tms:TipoOperacao>\n" +
                "            <tms:MunicipioOrigemCodigoIBGE>" + operacaoTransporte.getMunicipioOrigemCodigoIBGE() + "</tms:MunicipioOrigemCodigoIBGE>\n" +
                "            <tms:MunicipioDestinoCodigoIBGE>" + operacaoTransporte.getMunicipioDestinoCodigoIBGE() + "</tms:MunicipioDestinoCodigoIBGE>\n" +
                "            <tms:DataHoraInicio>" + operacaoTransporte.getDataHoraInicio() + "</tms:DataHoraInicio>\n" +
                "            <tms:DataHoraTermino>" + operacaoTransporte.getDataHoraTermino() + "</tms:DataHoraTermino>\n" +
                "            <tms:CPFCNPJContratado>" + transportador.getCPFCNPJ() + "</tms:CPFCNPJContratado>\n" +
                "            <tms:ValorFrete>" + operacaoTransporte.getValorFrete() + "</tms:ValorFrete>\n" +
                "            <tms:ValorCombustivel>" + operacaoTransporte.getValorCombustivel() + "</tms:ValorCombustivel>\n" +
                "            <tms:ValorPedagio>" + operacaoTransporte.getValorPedagio() + "</tms:ValorPedagio>\n" +
                "            <tms:ValorDespesas>" + operacaoTransporte.getValorDespesas() + "</tms:ValorDespesas>\n" +
                "            <tms:ValorImpostoSestSenat>" + operacaoTransporte.getValorImpostoSestSenat() + "</tms:ValorImpostoSestSenat>\n" +
                "            <tms:ValorImpostoIRRF>" + operacaoTransporte.getValorImpostoIRRF() + "</tms:ValorImpostoIRRF>\n" +
                "            <tms:ValorImpostoINSS>" + operacaoTransporte.getValorImpostoINSS() + "</tms:ValorImpostoINSS>\n" +
                "            <tms:ValorImpostoIcmsIssqn>" + operacaoTransporte.getValorImpostoICMSISSQN() + "</tms:ValorImpostoIcmsIssqn>\n" +
                "            <tms:ParcelaUnica>" + operacaoTransporte.getParcelaUnica() + "</tms:ParcelaUnica>\n" +
                "            <tms:ModoCompraValePedagio>" + operacaoTransporte.getModoCompraValePedagio() + "</tms:ModoCompraValePedagio>\n" +
                "            <tms:CategoriaVeiculo>" + operacaoTransporte.getCategoriaVeiculo() + "</tms:CategoriaVeiculo>\n" +
                "            <tms:NomeMotorista>" + motorista.getNome().concat(motorista.getSobrenome()) + "</tms:NomeMotorista>\n" +
                "            <tms:CPFMotorista>" + motorista.getCpf() + "</tms:CPFMotorista>\n" +
                "            <tms:RNTRCMotorista>" + motorista.getRNTRC() + "</tms:RNTRCMotorista>\n" +
                "            <tms:ItemFinanceiro>" + operacaoTransporte.getItemFinanceiro() + "</tms:ItemFinanceiro>\n" +
                "            <tms:Parcelas>");

        request.append(ParcelaService.buscarParcelasAfretamento(viagem));

        request.append("</tms:Parcelas>\n" +
                "            <tms:Veiculos>");

        request.append(VeiculoTransporteService.requestVeiculos(viagem));

        request.append("</tms:Veiculos>\n" +
                "            <tms:IdRotaModelo>" + operacaoTransporte.getIdRotaModelo() + "</tms:IdRotaModelo>\n" +
                "            <tms:DeduzirImpostos>" + operacaoTransporte.getDeduzirImpostos() + "</tms:DeduzirImpostos>\n" +
                "            <tms:TarifasBancarias>" + operacaoTransporte.getTarifasBancarias() + "</tms:TarifasBancarias>\n" +
                "            <tms:QuantidadeTarifasBancarias>" + operacaoTransporte.getQuantidadeTarifasBancarias() + "</tms:QuantidadeTarifasBancarias>\n" +
                "            <tms:IdIntegrador>" + operacaoTransporte.getIdIntegrador() + " " + id.nextInt() + "</tms:IdIntegrador>\n" +
                "            <tms:ValorDescontoAntecipado>" + operacaoTransporte.getValorDescontoAntecipado() + "</tms:ValorDescontoAntecipado>\n" +
                "            <tms:CPFCNPJParticipanteDestinatario>" + participante.getCPFCNPJ() + "</tms:CPFCNPJParticipanteDestinatario>\n" +
                "            <tms:CPFCNPJParticipanteContratante></tms:CPFCNPJParticipanteContratante>\n" +
                "            <tms:CPFCNPJParticipanteSubcontratante></tms:CPFCNPJParticipanteSubcontratante>\n" +
                "            <tms:CPFCNPJParticipanteConsignatario></tms:CPFCNPJParticipanteConsignatario>\n" +
                "            <tms:NumeroLacreTransporteCombustivel></tms:NumeroLacreTransporteCombustivel>\n" +
                "            <tms:NumeroCartaoValePedagio>" + operacaoTransporte.getNumeroCartaoValePedagio() + "</tms:NumeroCartaoValePedagio>\n" +
                "            <tms:DocumentoValePedagio>" + operacaoTransporte.getDocumentoValePedagio() + "</tms:DocumentoValePedagio>\n" +
                "            <tms:CEPOrigem>" + operacaoTransporte.getCEPOrigem() + "</tms:CEPOrigem>\n" +
                "            <tms:CEPDestino>" + operacaoTransporte.getCEPDestino() + "</tms:CEPDestino>\n" +
                "            <tms:TipoCargaANTT>" + operacaoTransporte.getTipoCargaANTT() + "</tms:TipoCargaANTT>\n" +
                "            <tms:DistanciaPercorrida >" + operacaoTransporte.getDistanciaPercorrida() + "</tms:DistanciaPercorrida>\n" +
                "         </tms:operacao>\n" +
                "      </tms:CadastrarAtualizarOperacaoTransporte>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/CadastrarAtualizarOperacaoTransporte");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(request.toString().getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            idOperacaoTransporte = Integer.valueOf(element.getElementsByTagName("IdOperacaoTransporte")
                    .item(0).getTextContent());


            if (idOperacaoTransporte == 0 || idOperacaoTransporte == null) {

                LogSankhya.inserirLog(
                        element.getElementsByTagName("MensagemErro")
                                .item(0).getTextContent(),
                        element.getElementsByTagName("Erro")
                                .item(0).getTextContent(),
                        request.toString()
                );
            }

            return idOperacaoTransporte;

        } catch (SOAPException | IOException | SQLException e) {

            throw new IntegracaoException("Erro na integração\n\n" + e.getMessage());
        }
    }

    public static Long declararOperacaoTranporte(OperacaoTransporte operacaoTransporte) {

        Long nroCIOT = 0l;

        final String url = "https://dev.transportesbra.com.br/frete/TMS/FreteService.svc";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:DeclararOperacaoTransporte>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:declaracao>\n" +
                "            <tms:IdOperacaoTransporte>" + operacaoTransporte.getIdOperacaoTransporte() + "</tms:IdOperacaoTransporte>\n" +
                "         </tms:declaracao>\n" +
                "      </tms:DeclararOperacaoTransporte>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/DeclararOperacaoTransporte");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(request.toString().getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            if (element.getElementsByTagName("Erro")
                    .item(0).getTextContent().contains("Tipo de Erro")) {

                LogFile.logger.info("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());

                LogSankhya.inserirLog(
                        element.getElementsByTagName("MensagemErro")
                                .item(0).getTextContent(),
                        element.getElementsByTagName("Erro")
                                .item(0).getTextContent(),
                        request
                );

                throw new IntegracaoException("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());

            } else {

                nroCIOT = Long.valueOf(element.getElementsByTagName("NumeroCIOT")
                        .item(0).getTextContent());

                return nroCIOT;
            }

        } catch (SOAPException | IOException | SQLException e) {

            throw new IntegracaoException("Erro na integração\n\n" + e.getMessage());
        }
    }
}
