package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.exception.IntegracaoException;
import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.model.entity.Participante;
import br.com.sankhyamt.integracaotarget.properties.AuthProperties;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import br.com.sankhyamt.integracaotarget.util.LogSankhya;
import br.com.sankhyamt.integracaotarget.util.RequestFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @since v1.2
 * @version 1.1
 */
public class ParticipanteService {

    Participante dadosParticipante = new Participante();
    AuthProperties authProperties = new AuthProperties();

    /**
     * Esse método busca os dados de UM parceiro da cidade destino da rota.
     * @param ordemcarga
     * @param codemp
     * @return
     */
    public Participante buscarDadosParticipante(String ordemcarga, String codemp){

        Connection connection = null;

        String sql = "SELECT * FROM VWDADOSPARTTMS\n" +
                "WHERE ORDEMCARGA = ? AND CODEMP = ?";

        try {
            connection = ConnectionSQLServer.connection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,ordemcarga);
            statement.setString(2,codemp);

            ResultSet query = statement.executeQuery();

            if (query.next()){
                dadosParticipante.setNome(query.getString("NOMEPARC").trim());
                dadosParticipante.setRazaoSocial(query.getString("RAZAOSOCIAL").trim());
                dadosParticipante.setCPFCNPJ(query.getString("CNPJCPF").trim());
                dadosParticipante.setEndereco(query.getString("ENDERECO").trim());
                dadosParticipante.setBairro(query.getString("BAIRRO").trim());
                dadosParticipante.setCep(query.getString("CEP").trim());
                dadosParticipante.setRNTRC(query.getString("RNTRC").trim());
                dadosParticipante.setEmail(query.getString("EMAIL").trim());
                dadosParticipante.setTelefone(query.getString("TELEFONE").trim());
                dadosParticipante.setTelefoneCelular(query.getString("TELEFONECELULAR"));
                dadosParticipante.setIdDmTipoPessoa(query.getInt("TIPPESSOA"));
                dadosParticipante.setMunicipioCodigoIbge(query.getInt("CODMUNFIS"));
            }

            return dadosParticipante;
        } catch (SQLException e) {

            throw new DatabaseException("Erro ao buscar dados do participante\n\n".concat(e.getMessage()));

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {

            LogFile.logger.info("Erro ao buscar as parcelas do afretamento: " + e.getMessage());
        }

        return null;
    }

    /**
     * Cadastra/Atualiza participante da viagem.
     * @param participante Recebe uma instância de Participante com os dados do participante.
     * @return Retorna o ID do Participante.
     * @throws SQLException
     */
    public Integer cadastrarAtualizarParticipante(Participante participante) throws SQLException {

        final String url = "https://www.transportesbra.com.br/frete/TMS/FreteService.svc";

        Element element = null;

        Document responseXML = null;

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:CadastrarAtualizarParticipante>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:participante>\n" +
                "            <tms:Instrucao>1</tms:Instrucao>\n" +
                "            <tms:IdParticipante>0</tms:IdParticipante>\n" +
                "            <tms:IdDmTipoPessoa>" + participante.getIdDmTipoPessoa() + "</tms:IdDmTipoPessoa>\n" +
                "            <tms:Nome>" + participante.getNome() + "</tms:Nome>\n" +
                "            <tms:RazaoSocial>" + participante.getRazaoSocial() + "</tms:RazaoSocial>\n" +
                "            <tms:CPFCNPJ>" + participante.getCPFCNPJ() + "</tms:CPFCNPJ>\n" +
                "            <tms:Endereco>" + participante.getEndereco() + "</tms:Endereco>\n" +
                "            <tms:Bairro>" + participante.getBairro() + "</tms:Bairro>\n" +
                "            <tms:CEP>" + participante.getCep() + "</tms:CEP>\n" +
                "            <tms:MunicipioCodigoIBGE>" + participante.getMunicipioCodigoIbge() + "</tms:MunicipioCodigoIBGE>\n" +
                "            <tms:RNTRC>" + participante.getRNTRC() + "</tms:RNTRC>\n" +
                "            <tms:Ativo>" + participante.getAtivo() + "</tms:Ativo>\n" +
                "            <tms:Email>" + participante.getEmail() + "</tms:Email>\n" +
                "            <tms:Telefone>" + participante.getTelefone() + "</tms:Telefone>\n" +
                "            <tms:TelefoneCelular>" + participante.getTelefoneCelular() + "</tms:TelefoneCelular>\n" +
                "         </tms:participante>\n" +
                "      </tms:CadastrarAtualizarParticipante>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        
        try {

            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();
            headers.addHeader("Content-Type", "text/xml");
            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/CadastrarAtualizarParticipante");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(RequestFormat.requestFormat(request).getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            responseXML =  response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = responseXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            element = (Element) node;

        } catch (SOAPException | IOException e) {

            throw new IntegracaoException("Erro ao cadastrar participante\n\n".concat(e.getMessage()));
        }

        if (!element.getElementsByTagName("Erro")
                .item(0).getTextContent().equals("")){

            LogSankhya.inserirLog(
                    element.getElementsByTagName("MensagemErro")
                            .item(0).getTextContent(),
                    element.getElementsByTagName("Erro")
                            .item(0).getTextContent(),
                    request
            );

            throw new IntegracaoException("Erro na integração\n\n" + element.getElementsByTagName("MensagemErro")
                    .item(0).getTextContent());


        } else if (!element.getElementsByTagName("IdParticipante").item(0).getTextContent().equals("")){

            return Integer.valueOf(element.getElementsByTagName("IdParticipante").item(0).getTextContent());
        }

        return 0;
    }
}


// v1.1 - Implementação da formatação do request, para retirar caracteres especiais