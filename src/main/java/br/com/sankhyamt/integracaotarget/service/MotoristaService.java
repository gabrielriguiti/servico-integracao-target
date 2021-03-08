package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.exception.IntegracaoException;
import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.model.entity.Motorista;
import br.com.sankhyamt.integracaotarget.model.entity.Transportador;
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
 * @since v1.0
 * @version 1.2
 */
public class MotoristaService {

    static AuthProperties authProperties = new AuthProperties();

    /**
     * Esse método busca os dados do motorista na base de dados SANKHYA.
     *
     * @param ordemCarga Recebe a ordem de carga da viagem.
     * @param codemp     Rece a empresa da ordem de carga.
     * @return retorna um objeto Motorista com os dados retornado da base de dados.
     */
    public Motorista buscarDadosMotorista(String ordemCarga, String codemp) {

        Motorista dadosMotorista = new Motorista();
        Connection connection = null;

        String sql = "SELECT * FROM VWDADOSMOTTMS \n" +
                "WHERE ORDEMCARGA = ? AND CODEMP = ?";

        try {
            connection = ConnectionSQLServer.connection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ordemCarga);
            statement.setString(2, codemp);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                dadosMotorista.setNome(rs.getString("NOME").trim());
                dadosMotorista.setSobrenome(rs.getString("SOBRENOME").trim());
                dadosMotorista.setCpf(rs.getString("CPF").trim());
                dadosMotorista.setNroRG(rs.getString("NRORG").trim());
                dadosMotorista.setOrgaoEmissoRG(rs.getString("EMISSORRG").trim());
                dadosMotorista.setDtNascimento(rs.getString("DTNASC").trim());
                dadosMotorista.setSexo(rs.getString("SEXO").trim());
                dadosMotorista.setEstadoCivil(rs.getString("ESTADOCIVIL").trim());
                dadosMotorista.setNomePai(rs.getString("PAI").trim());
                dadosMotorista.setNomeMae(rs.getString("MAE").trim());
                dadosMotorista.setEmail(rs.getString("EMAIL").trim());
                dadosMotorista.setTelefone(rs.getString("TELEFONE").trim());
                dadosMotorista.setTelefoneCelular(rs.getString("TIMTELEFONE01").trim());
                dadosMotorista.setNacionalidade(rs.getString("TIMNACIONALIDAD").trim());
                dadosMotorista.setEndereco(rs.getString("NOMEEND").trim());
                dadosMotorista.setNumero(rs.getString("NUMEND").trim());
                dadosMotorista.setCep(rs.getString("CEP").trim());
                dadosMotorista.setBairro(rs.getString("NOMEBAI").trim());
                dadosMotorista.setCidade(rs.getString("NOMECID").trim());
                dadosMotorista.setUf(rs.getString("UF").trim());
                dadosMotorista.setCodigoIBGEMunicipio(rs.getString("CODMUNFIS").trim());
                dadosMotorista.setCodBanco(rs.getString("CODBCOADIANT").trim());
                dadosMotorista.setCodAgencia(rs.getString("AGENCIAADIANT").trim());
                dadosMotorista.setDigitoAgencia(rs.getString("DIGITOADIANT").trim());
                dadosMotorista.setContaCorrente(rs.getString("CONTAADIANT").trim());
                dadosMotorista.setRNTRC(rs.getString("RNTRC").trim());
            }

            return dadosMotorista;

        } catch (SQLException e) {

            throw new DatabaseException("Erro ao buscar dados do motorista\n\n" + e.getMessage());

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            LogFile.logger.info("Erro ao buscar as parcelas do afretamento: " + e.getMessage());
        }

        return null;
    }

    /**
     * Esse método salva um motorista na base de dados TARGET.
     *
     * @param motorista     Recebe um objeto com os dados do motorista.
     * @param transportador Recebe um objeto com os dados do transportador.
     * @return Retorna o ID do motorista salvo na base de dados TARGER.
     */
    public Integer cadastrarAtualizarMotorista(Motorista motorista, Transportador transportador) {

        Integer idMotorista = 0;

        final String url = "https://www.transportesbra.com.br/frete/TMS/FreteService.svc?singleWsdl";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:CadastrarAtualizarMotorista>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:motorista>\n" +
                "            <tms:Instrucao>1</tms:Instrucao>\n" +
                "            <tms:CPFCNPJTransportador>" + transportador.getCPFCNPJ() + "</tms:CPFCNPJTransportador>\n" +
                "            <tms:IdMotorista>" + motorista.getIdMotorista() + "</tms:IdMotorista>\n" +
                "            <tms:Nome>" + motorista.getNome() + "</tms:Nome>\n" +
                "            <tms:Sobrenome>" + motorista.getSobrenome() + "</tms:Sobrenome>\n" +
                "            <tms:CPF>" + motorista.getCpf() + "</tms:CPF>\n" +
                "            <tms:NumeroRG>" + motorista.getNroRG() + "</tms:NumeroRG>\n" +
                "            <tms:OrgaoEmissorRg>" + motorista.getOrgaoEmissoRG() + "</tms:OrgaoEmissorRg>\n" +
                "            <tms:DataNascimento>" + motorista.getDtNascimento() + "</tms:DataNascimento>\n" +
                "            <tms:Sexo>" + motorista.getSexo() + "</tms:Sexo>\n" +
                "            <tms:EstadoCivil>" + motorista.getEstadoCivil() + "</tms:EstadoCivil>\n" +
                "            <tms:NomePai>" + motorista.getNomePai() + "</tms:NomePai>\n" +
                "            <tms:NomeMae>" + motorista.getNomeMae() + "</tms:NomeMae>\n" +
                "            <tms:Email>" + motorista.getEmail() + "</tms:Email>\n" +
                "            <tms:Telefone>" + motorista.getTelefone() + "</tms:Telefone>\n" +
                "            <tms:TelefoneCelular>" + motorista.getTelefoneCelular() + "</tms:TelefoneCelular>\n" +
                "            <tms:Nacionalidade>" + motorista.getNacionalidade() + "</tms:Nacionalidade>\n" +
                "            <tms:Endereco>" + motorista.getEndereco() + "</tms:Endereco>\n" +
                "            <tms:NumeroEndereco>" + motorista.getNumero() + "</tms:NumeroEndereco>\n" +
                "            <tms:EnderecoComplemento>" + (motorista.enderecoCompleto().length()
                >= 30 ? motorista.enderecoCompleto().substring(0, 30) : motorista.enderecoCompleto()) + "</tms:EnderecoComplemento>\n" +
                "            <tms:CEP>" + motorista.getCep() + "</tms:CEP>\n" +
                "            <tms:Bairro>" + motorista.getBairro() + "</tms:Bairro>\n" +
                "            <tms:CodigoIBGEMunicipio>" + motorista.getCodigoIBGEMunicipio() + "</tms:CodigoIBGEMunicipio>\n" +
                "            <tms:CodigoBanco>" + motorista.getCodBanco() + "</tms:CodigoBanco>\n" +
                "            <tms:CodigoAgencia>" + motorista.getCodAgencia() + "</tms:CodigoAgencia>\n" +
                "            <tms:DigitoAgencia>" + motorista.getDigitoAgencia() + "</tms:DigitoAgencia>\n" +
                "            <tms:ContaCorrente>" + motorista.getContaCorrente() + "</tms:ContaCorrente>\n" +
                "            <tms:DigitoContaCorrente>" + motorista.getContaCorrente() + "</tms:DigitoContaCorrente>\n" +
                "            <tms:FlagContaPoupanca>" + motorista.getFlagContaPoupanca() + "</tms:FlagContaPoupanca>\n" +
                "            <tms:VariacaoContaPoupanca>" + motorista.getVariacaoContaPoupanca() + "</tms:VariacaoContaPoupanca>\n" +
                "            <tms:Ativo>" + motorista.getAtivo() + "</tms:Ativo>\n" +
                "         </tms:motorista>\n" +
                "      </tms:CadastrarAtualizarMotorista>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/CadastrarAtualizarMotorista");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(RequestFormat.requestFormat(request).getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            if (!element.getElementsByTagName("IdMotorista")
                    .item(0).getTextContent().equals("0")) {
                idMotorista = Integer.valueOf(element.getElementsByTagName("IdMotorista")
                        .item(0).getTextContent());

                return idMotorista;
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

            } else if (element.getElementsByTagName("MensagemErro")
                    .item(0).getTextContent() != null) {

                LogSankhya.inserirLog(
                        element.getElementsByTagName("MensagemErro")
                                .item(0).getTextContent(),
                        element.getElementsByTagName("Erro")
                                .item(0).getTextContent(),
                        request
                );

                LogFile.logger.info("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());

                throw new IntegracaoException("Erro na integração\n\n" + element.getElementsByTagName("Erro")
                        .item(0).getTextContent());
            }

        } catch (SOAPException | IOException | SQLException /*| TransformerConfigurationException*/ e) {

            LogFile.logger.info("Erro na integração\n\n" + e.getMessage());

            throw new IntegracaoException("Erro na integração\n\n" + e.getMessage());
        }

        return 0;
    }
}

// v1.2 - Implementação da formatação do request, para retirar caracteres especiais