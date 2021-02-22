package br.com.sankhyamt.integracaotarget.extension;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class EmitirDocumento implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {

        BigDecimal idOperacao = BigDecimal.ZERO;
        BigDecimal ordemCarga = BigDecimal.ZERO;
        BigDecimal empresa = BigDecimal.ZERO;
        BigDecimal nuDeclaracao = BigDecimal.ZERO;

        Registro[] linhasSelecionadas = contextoAcao.getLinhas();

        for (Registro i : linhasSelecionadas) {

            idOperacao = (BigDecimal) i.getCampo("IDOPERTRANSP");
            ordemCarga = (BigDecimal) i.getCampo("ORDEMCARGA");
            empresa = (BigDecimal) i.getCampo("CODEMP");
            nuDeclaracao = (BigDecimal) i.getCampo("NUDECLARACAO");
        }

        URL url = new URL("http://10.40.3.242:9091/target/emitirDocumento/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        JsonObject operacaoTransporte = new JsonObject();

        operacaoTransporte.addProperty("idOperacao", idOperacao);
        operacaoTransporte.addProperty("instrucao", 5);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(operacaoTransporte.toString());
            outputStreamWriter.flush();

            StringBuilder response = new StringBuilder();

            Integer httpResult = connection.getResponseCode();

            if (httpResult == HttpURLConnection.HTTP_OK) {

                BufferedReader responseReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8")
                );

                String line = null;

                while ((line = responseReader.readLine()) != null) {

                    response.append(line + "\n");
                }

                responseReader.close();

                JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();

                String documentoResponse = jsonObject.get("documentoBinario").toString();

                byte[] documentoBinario = Base64.getDecoder().decode(documentoResponse.replace("\"", ""));

                String sql = "SELECT TEXTO\n" +
                        "     FROM TSIPAR\n" +
                        "     WHERE CHAVE = 'FREPBASEFOLDER'";

                String diretorio = "";
                String nomeArquivo = "Operação_" + idOperacao + ".pdf";

                QueryExecutor query = contextoAcao.getQuery();

                try {

                    query.nativeSelect(sql);

                    if (query.next()) {

                        diretorio = query.getString("TEXTO").concat("/Sistema/Anexos/AD_SMTDCT/");
                    }

                    query.close();

                } catch (Exception e) {

                    contextoAcao.mostraErro(e.getMessage());
                }

                try {

                    DataOutputStream writePDF = new DataOutputStream(
                            new FileOutputStream(
                                    diretorio + nomeArquivo)
                    );

                    writePDF.write(documentoBinario);
                    writePDF.close();

                    query.setParam("NOMEARQUIVO", nomeArquivo);
                    query.update("DELETE TSIANX WHERE NOMEARQUIVO = {NOMEARQUIVO}");


                    query.setParam("CODUSU", contextoAcao.getUsuarioLogado());
                    query.setParam("DESCRICAO", "Declaração CIOT");
                    query.setParam("NOMEARQUIVO", nomeArquivo);
                    query.setParam("PKREGISTRO", "1_" + ordemCarga + "_" + nuDeclaracao + "_AD_SMTDCT");
                    query.update("INSERT INTO TSIANX (CODUSU, CODUSUALT, CHAVEARQUIVO, DESCRICAO, DHALTER, DHCAD, NOMEARQUIVO, NOMEINSTANCIA,\n" +
                            "                    NUATTACH, PKREGISTRO, RESOURCEID, TIPOACESSO, TIPOAPRES)\n" +
                            "VALUES ({CODUSU}, {CODUSU},  {NOMEARQUIVO}, {DESCRICAO}, GETDATE(), GETDATE(), {NOMEARQUIVO}, 'AD_SMTDCT',\n" +
                            "         (SELECT MAX(NUATTACH) + 1 FROM TSIANX), {PKREGISTRO}, 'br.com.sankhya.hnztms.mov.viagem', 'ALL', 'GLO')");

                } catch (IOException e) {

                    contextoAcao.mostraErro(e.getMessage());
                }

                contextoAcao.setMensagemRetorno("Documento emitido com sucesso!");

            } else {
                contextoAcao.mostraErro("Erro ao emitir CIOT.\nConsulte a tela 'Log Integração Target' para mais informações.");

                LogFile.logger.info("Erro ao emitr CIOT".concat(connection.getResponseMessage()));
            }
        } catch (IOException e) {

            contextoAcao.mostraErro("Erro ao emitir CIOT.\nConsulte a tela 'Log Integração Target' para mais informações.");

            LogFile.logger.info("Erro ao emitir CIOT".concat(connection.getResponseMessage()));

        }
    }
}
