package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.exception.IntegracaoException;
import br.com.sankhyamt.integracaotarget.model.entity.*;
import br.com.sankhyamt.integracaotarget.properties.AuthProperties;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import br.com.sankhyamt.integracaotarget.util.LogSankhya;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class CiotService {

    static TransportadorService transportadorService = new TransportadorService();
    static MotoristaService motoristaService = new MotoristaService();
    static ParticipanteService participanteService = new ParticipanteService();
    static AuthProperties authProperties = new AuthProperties();

    public String[] ciotCenario3(Viagem viagem) throws SQLException {

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
                    .cadastrarAtualizarMotorista(motorista, transportador));

            participante = participanteService.buscarDadosParticipante(
                    viagem.getOrdemCarga(), viagem.getCodEmp());

            participante.setIdParticipante(participanteService
                    .cadastrarAtualizarParticipante(participante));

            operacaoTransporte = OperacaoTransporteService.buscarDadosOperacaoTranporte(viagem);

            if (!viagem.getIdOperacao().equals("0")){
                operacaoTransporte.setIdOperacaoTransporte(
                        Integer.valueOf(viagem.getIdOperacao()));
            }

            operacaoTransporte.setIdOperacaoTransporte(OperacaoTransporteService
                    .cadastrarAtualizarOperacaoTransporte(
                            operacaoTransporte, transportador, motorista, participante, viagem));

            nroCiot = OperacaoTransporteService.declararOperacaoTranporte(operacaoTransporte);

            dadosCiot[0] = nroCiot.toString();
            dadosCiot[1] = operacaoTransporte.getIdOperacaoTransporte().toString();

            return dadosCiot;

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao gerar CIOT\n".concat(e.getMessage()));

            LogSankhya.inserirLog(e.getMessage(),"","");

            throw new DatabaseException(e.getMessage());

        } catch (Exception e) {

            LogFile.logger.info("Erro ao gerar CIOT\n".concat(e.getMessage()));

        }

        return null;
    }

    public static String emitirCiot(String idOperacaoTransporte) {

        final String url = "https://dev.transportesbra.com.br/frete/TMS/FreteService.svc";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:EmitirDocumento>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:emissaoDocumento>\n" +
                "            <tms:Tipo>1</tms:Tipo>\n" +
                "            <tms:IdEntidade>" + idOperacaoTransporte + "</tms:IdEntidade>\n" +
                "         </tms:emissaoDocumento>\n" +
                "      </tms:EmitirDocumento>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/EmitirDocumento");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(request.getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            if (element.getElementsByTagName("Erro")
                    .item(0).getTextContent().contains("Tipo de Erro")) {

                LogSankhya.inserirLog(
                        element.getElementsByTagName("MensagemErro")
                                .item(0).getTextContent(),
                        element.getElementsByTagName("Erro")
                                .item(0).getTextContent(),
                        request
                );

                throw new IntegracaoException("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());
            }

            String documentoResponse = element.getElementsByTagName("DocumentoBinario").item(0).getTextContent();

            return documentoResponse;

        } catch (SOAPException | IOException | SQLException e) {

            LogFile.logger.info(e.getMessage());

            throw new IntegracaoException("Erro na integração\n\n" + e.getMessage());
        }

    }

    public static String cancelarOperacaoTransporte(String idOperacao, String motivoCancelamento) {

        String idCancelamento = "0";

        final String url = "https://dev.transportesbra.com.br/frete/TMS/FreteService.svc";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <tms:CancelarOperacaoTransporte>\n" +
                "            <tms:auth>\n" +
                "                <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "                <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "                <tms:Token></tms:Token>\n" +
                "            </tms:auth>\n" +
                "            <tms:cancelamentoOperacaoRequest>\n" +
                "                <tms:IdOperacao>" + idOperacao + "</tms:IdOperacao>\n" +
                "                <tms:MotivoCancelamento>" + motivoCancelamento + "</tms:MotivoCancelamento>\n" +
                "            </tms:cancelamentoOperacaoRequest>\n" +
                "        </tms:CancelarOperacaoTransporte>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/CancelarOperacaoTransporte");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(request.getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            if (!element.getElementsByTagName("IdCancelamentoOperacaoTransporte")
                    .item(0).getTextContent().equals("0")) {

                idCancelamento = element.getElementsByTagName("IdCancelamentoOperacaoTransporte")
                        .item(0).getTextContent();

                return idCancelamento;
            }

            if (element.getElementsByTagName("Erro")
                    .item(0).getTextContent().contains("Tipo de Erro")) {

                LogSankhya.inserirLog(
                        element.getElementsByTagName("MensagemErro")
                                .item(0).getTextContent(),
                        element.getElementsByTagName("Erro")
                                .item(0).getTextContent(),
                        request
                );

                throw new IntegracaoException("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());
            }

        } catch (SOAPException | SQLException | IOException e) {

            throw new IntegracaoException("Erro na integração\n\n" + e.getMessage());
        }

        return "0";
    }

    public static String encerrarOperacaoTransporte(String idOperacao) {

        String idEncerramento = "0";

        final String url = "https://dev.transportesbra.com.br/frete/TMS/FreteService.svc";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:EncerrarOperacaoTransporte>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:encerramentoRequest>\n" +
                "            <tms:CodigoOperacao>" + idOperacao + "</tms:CodigoOperacao>\n" +
                "         </tms:encerramentoRequest>\n" +
                "      </tms:EncerrarOperacaoTransporte>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/EncerrarOperacaoTransporte");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(request.getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            if (!element.getElementsByTagName("IdEncerramentoOperacaoTransporte")
                    .item(0).getTextContent().equals("0")) {

                idEncerramento = element.getElementsByTagName("IdEncerramentoOperacaoTransporte")
                        .item(0).getTextContent();

                return idEncerramento;
            }

            if (element.getElementsByTagName("Erro")
                    .item(0).getTextContent().contains("Tipo de Erro")) {

                LogSankhya.inserirLog(
                        element.getElementsByTagName("MensagemErro")
                                .item(0).getTextContent(),
                        element.getElementsByTagName("Erro")
                                .item(0).getTextContent(),
                        request
                );

                throw new IntegracaoException("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());
            }

        } catch (SOAPException | SQLException | IOException e) {

            throw new IntegracaoException("Erro na integração\n\n" + e.getMessage());
        }

        return "0";
    }

}
