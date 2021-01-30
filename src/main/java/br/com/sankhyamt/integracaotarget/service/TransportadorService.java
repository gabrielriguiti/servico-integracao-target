package br.com.sankhyamt.integracaotarget.service;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.model.entity.Transportador;
import br.com.sankhyamt.integracaotarget.properties.AuthProperties;
import br.com.sankhyamt.integracaotarget.util.LogFile;
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
 * Classe que possui os serviçõs relacionados ao transportador.
 *
 * @author Gabriel Riguiti
 * @since v1.0
 */
public class TransportadorService {

    AuthProperties authProperties = new AuthProperties();

    /**
     * Esse método realiza o cadastro de um Tranportador no software Target.
     *
     * @param transportador Recebe instância de Transportador.
     * @return retornar o ID do transportador cadastrado.
     */
    public Integer cadastrarAtualizarTransportador(Transportador transportador) {

        Integer idTranportador = 0;

        final String url = "https://dev.transportesbra.com.br/frete/TMS/FreteService.svc";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tms=\"http://tmsfrete.v2.targetmp.com.br\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tms:CadastrarAtualizarTransportador>\n" +
                "         <tms:auth>\n" +
                "            <tms:Usuario>" + authProperties.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:Senha>" + authProperties.getSenha() + "</tms:Senha>\n" +
                "            <tms:Token></tms:Token>\n" +
                "         </tms:auth>\n" +
                "         <tms:transportador>\n" +
                "            <tms:Instrucao>1</tms:Instrucao>\n" +
                "            <tms:RNTRC>" + transportador.getRNTRC() + "</tms:RNTRC>\n" +
                "            <tms:CPFCNPJ>" + transportador.getCPFCNPJ() + "</tms:CPFCNPJ>\n" +
                "            <tms:Nome>" + transportador.getNome() + "</tms:Nome>\n" +
                "            <tms:Sobrenome>" + transportador.getSobrenome() + "</tms:Sobrenome>\n" +
                "            <tms:RazaoSocial>" + transportador.getRazaoSocial() + "</tms:RazaoSocial>\n" +
                "            <tms:DataNascimento>" + transportador.getDataNascimento() + "</tms:DataNascimento>\n" +
                "            <tms:RG>" + transportador.getRG() + "</tms:RG>\n" +
                "            <tms:OrgaoEmissorRg>" + transportador.getOrgaoEmissorRg() + "</tms:OrgaoEmissorRg>\n" +
                "            <tms:CNH>" + transportador.getCNH() + "</tms:CNH>\n" +
                "            <tms:TipoCNH>" + transportador.getTipoCNH() + "</tms:TipoCNH>\n" +
                "            <tms:DataValidadeCNH>" + transportador.getDataValidadeCNH() + "</tms:DataValidadeCNH>\n" +
                "            <tms:Sexo>" + transportador.getSexo() + "</tms:Sexo>\n" +
                "            <tms:Naturalidade>" + transportador.getNaturalidade() + "</tms:Naturalidade>\n" +
                "            <tms:Nacionalidade>" + transportador.getNacionalidade() + "</tms:Nacionalidade>\n" +
                "            <tms:InscricaoEstadual>" + transportador.getInscricaoEstadual() + "</tms:InscricaoEstadual>\n" +
                "            <tms:InscricaoMunicipal>" + transportador.getInscricaoMunicipal() + "</tms:InscricaoMunicipal>\n" +
                "            <tms:NomeFantasia>" + transportador.getNomeFantasia() + "</tms:NomeFantasia>\n" +
                "            <tms:DataInscricao>" + transportador.getDataInscricao() + "</tms:DataInscricao>\n" +
                "            <tms:IdDmAtividadeEconomica>" + transportador.getIdDmAtividadeEconomica() + "</tms:IdDmAtividadeEconomica>\n" +
                "            <tms:Endereco>" + transportador.getEndereco() + "</tms:Endereco>\n" +
                "            <tms:NumeroEndereco>" + transportador.getNumeroEndereco() + "</tms:NumeroEndereco>\n" +
                "            <tms:EnderecoComplemento>" + transportador.enderecoCompleto(transportador.getEndereco(), transportador.getNumeroEndereco(), transportador.getBairro()) + "</tms:EnderecoComplemento>\n" +
                "            <tms:Bairro>" + transportador.getBairro() + "</tms:Bairro>\n" +
                "            <tms:CEP>" + transportador.getCEP() + "</tms:CEP>\n" +
                "            <tms:CodigoIBGEMunicipio>" + transportador.getCodIbgeMunicipio() + "</tms:CodigoIBGEMunicipio>\n" +
                "            <tms:IdentificadorEndereco>" + transportador.getCEP() + "</tms:IdentificadorEndereco>\n" +
                "            <tms:TelefoneFixo>" + transportador.getTelefoneFixo() + "</tms:TelefoneFixo>\n" +
                "            <tms:TelefoneCelular>" + transportador.getTelefoneCelular() + "</tms:TelefoneCelular>\n" +
                "            <tms:EstadoCivil>" + transportador.getEstadoCivil() + "</tms:EstadoCivil>\n" +
                "            <tms:Email>" + transportador.getEmail() + "</tms:Email>\n" +
                "            <tms:Usuario>" + transportador.getUsuario() + "</tms:Usuario>\n" +
                "            <tms:CodigoBanco>" + transportador.getCodigoBanco() + "</tms:CodigoBanco>\n" +
                "            <tms:CodigoAgencia>" + transportador.getCodigoAgencia() + "</tms:CodigoAgencia>\n" +
                "            <tms:DigitoAgencia>" + transportador.getDigitoAgencia() + "</tms:DigitoAgencia>\n" +
                "            <tms:ContaCorrente>" + transportador.getContaCorrente() + "</tms:ContaCorrente>\n" +
                "            <tms:DigitoContaCorrente>" + transportador.getDigitoContaCorrente() + "</tms:DigitoContaCorrente>\n" +
                "            <tms:FlagContaPoupanca>" + transportador.getFlagContaPoupanca() + "</tms:FlagContaPoupanca>\n" +
                "            <tms:VariacaoContaPoupanca>" + transportador.getVariacaoContaPoupanca() + "</tms:VariacaoContaPoupanca>\n" +
                "            <tms:NomeContato>" + transportador.getNomeContato() + "</tms:NomeContato>\n" +
                "            <tms:CargoContato>" + transportador.getCargoContato() + "</tms:CargoContato>\n" +
                "            <tms:CPFCNPJContato>" + transportador.getCPFCNPJFContato() + "</tms:CPFCNPJContato>\n" +
                "            <tms:TelefoneFixoContato>" + transportador.getTelefoneFixoContato() + "</tms:TelefoneFixoContato>\n" +
                "            <tms:TelefoneCelularContato>" + transportador.getTelefoneCelularContato() + "</tms:TelefoneCelularContato>\n" +
                "            <tms:EmailContato>" + transportador.getEmail() + "</tms:EmailContato>\n" +
                "            <tms:DataNascimentoContato>" + transportador.getDataNascimentoContato() + "</tms:DataNascimentoContato>\n" +
                "            <tms:RGContato>" + transportador.getRGContato() + "</tms:RGContato>\n" +
                "            <tms:OrgaoEmissorRgContato>" + transportador.getOrgaoEmissoContato() + "</tms:OrgaoEmissorRgContato>\n" +
                "         </tms:transportador>\n" +
                "      </tms:CadastrarAtualizarTransportador>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();

            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            MimeHeaders headers = new MimeHeaders();

            headers.addHeader("Content-Type", "text/xml");

            headers.addHeader("SOAPAction",
                    "http://tmsfrete.v2.targetmp.com.br/FreteTMSService/CadastrarAtualizarTransportador");

            MessageFactory messageFactory = MessageFactory.newInstance();

            SOAPMessage soapMessage = messageFactory.createMessage(headers,
                    (new ByteArrayInputStream(request.getBytes())));

            SOAPMessage response = soapConnection.call(soapMessage, url);

            Document resposeXML = response.getSOAPBody().getOwnerDocument();

            NodeList nodeList = resposeXML.getElementsByTagName("s:Body");

            Node node = nodeList.item(0);

            Element element = (Element) node;

            if (!element.getElementsByTagName("Erro")
                    .item(0).getTextContent().equals("")) {

                throw new DatabaseException("ERRO NA INTEGRACAO\nMESSAGEM RETORNADA:" + element.getElementsByTagName("MensagemErro")
                        .item(0).getTextContent());
            }

            idTranportador = Integer.valueOf(element.getElementsByTagName("IdCliente").item(0).getTextContent());

            return idTranportador;

        } catch (SOAPException | IOException e) {

            throw new DatabaseException(e.getMessage());

        }
    }

    /**
     * @param codAfretamento Recebe o código do afretamento da execução.
     * @param ordemCarga     Recebe a ordem de carga da viagem.
     * @param codEmp         Recebe o código da empresa da viagem
     * @return Retorna o objeto Tranportador, com os dados retornada da base de dados do Sankhya.
     * @throws Exception
     * @since v1.0
     */
    public Transportador buscarDadosTransportador(String codAfretamento, String ordemCarga,
                                                  String codEmp) throws Exception {

        Transportador dadosTransportador = new Transportador();

        Connection connection = null;

        String sql = "SELECT * FROM VWDADOSTRANSTMS \n" +
                "WHERE ORDEMCARGA = ? AND CODAFT = ? AND CODEMP = ?";

        try {
            connection = ConnectionSQLServer.connection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ordemCarga);
            statement.setString(2, codAfretamento);
            statement.setString(3, codEmp);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                dadosTransportador.setRNTRC(rs.getString("RNTRC").trim());
                dadosTransportador.setCPFCNPJ(rs.getString("CGC_CPF").trim());
                dadosTransportador.setNome(rs.getString("NOMEPARC").trim());
                dadosTransportador.setRazaoSocial(rs.getString("RAZAOSOCIAL").trim());
                dadosTransportador.setDataNascimento(rs.getString("DTNASC").trim());
                dadosTransportador.setRG(rs.getString("AD_NUMRG").trim());
                dadosTransportador.setOrgaoEmissorRg(rs.getString("EMISSORRG").trim());
                dadosTransportador.setSexo(rs.getString("SEXO").trim());
                dadosTransportador.setNacionalidade(rs.getString("TIMNACIONALIDAD").trim());
                dadosTransportador.setNomeFantasia(rs.getString("NOMEPARC").trim());
                dadosTransportador.setDataInscricao(rs.getString("DTCAD").trim());
                dadosTransportador.setNumeroEndereco(rs.getInt("NUMEND"));
                dadosTransportador.setCEP(rs.getString("CEP").trim());
                dadosTransportador.setIndentificadorEndereco(rs.getString("COMPLEMENTO").trim());
                dadosTransportador.setTelefoneFixo(rs.getString("TELEFONE").trim());
                dadosTransportador.setTelefoneCelular(rs.getString("TIMTELEFONE01").trim());
                dadosTransportador.setEstadoCivil(rs.getString("TIMESTADOCIVIL").trim());
                dadosTransportador.setEmail(rs.getString("EMAIL").trim());
                dadosTransportador.setUsuario(rs.getString("USUARIO").trim());
                dadosTransportador.setCNH(rs.getString("NROCNH").trim());
                dadosTransportador.setTipoCNH(rs.getString("CATEGORIACNH").trim());
                dadosTransportador.setDataValidadeCNH(rs.getString("VENCIMENTOCNH").trim());
                dadosTransportador.setNaturalidade(rs.getString("NATURALIDADE").trim());
                dadosTransportador.setInscricaoEstadual(rs.getString("IDENTINSCESTAD").trim());
                dadosTransportador.setEndereco(rs.getString("NOMEEND").trim());
                dadosTransportador.setBairro(rs.getString("NOMEBAI").trim());
                dadosTransportador.setCodigoBanco(rs.getString("CODBCOADIANT").trim());
                dadosTransportador.setCodigoAgencia(rs.getString("AGENCIAADIANT").trim());
                dadosTransportador.setDigitoAgencia(rs.getString("DIGITOADIANT").trim());
                dadosTransportador.setContaCorrente(rs.getString("CONTAADIANT").trim());
                dadosTransportador.setCodIbgeMunicipio(rs.getInt("CODIBGE"));
            } else {

                LogFile.logger.info("Dados não encontrados para o transportador da viagem.");
            }

            return dadosTransportador;

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao buscar dados do transportador: " + e.getMessage());

            throw new DatabaseException("Erro ao buscar dados do transportador\n\n" + e.getMessage());

        } finally {

            connection.close();
        }
    }

}
