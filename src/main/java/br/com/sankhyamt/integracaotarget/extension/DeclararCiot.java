package br.com.sankhyamt.integracaotarget.extension;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Classe para botão de ação no SANKHYA.
 *
 * @since v1.0
 */
public class DeclararCiot implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {

        BigDecimal ordemCarga = BigDecimal.ZERO;
        BigDecimal codEmp = BigDecimal.ZERO;
        BigDecimal codAfretamento = BigDecimal.ZERO;

        Registro[] linhasSelecionadas = contextoAcao.getLinhas();

        for (Registro i : linhasSelecionadas) {

            ordemCarga = (BigDecimal) i.getCampo("ORDEMCARGA");
            codEmp = (BigDecimal) i.getCampo("CODEMP");
            codAfretamento = (BigDecimal) i.getCampo("CODAFT");
        }

        URL url = new URL("http://10.40.3.242:9091/target/gerarCiot/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        JsonObject viagem = new JsonObject();

        viagem.addProperty("ordemCarga", ordemCarga);
        viagem.addProperty("codemp", codEmp);
        viagem.addProperty("codaft", codAfretamento);
        viagem.addProperty("instrucao", 1);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(viagem.toString());
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

                String nroCiot = jsonObject.get("nroCiot").toString();
                String idOperacao = jsonObject.get("idOperacaoTransporte").toString();

                QueryExecutor queryExecutor = contextoAcao.getQuery();
                queryExecutor.setParam("NUCIOT", nroCiot.replaceAll("\"", ""));
                queryExecutor.setParam("IDOPERTRANSP", idOperacao.replaceAll("\"", ""));
                queryExecutor.setParam("ORDEMCARGA", ordemCarga);
                queryExecutor.setParam("CODEMP", codEmp);
                queryExecutor.setParam("NUAFT", codAfretamento);
                queryExecutor.update("INSERT INTO AD_SMTDCT (NUDECLARACAO, CODEMP, ORDEMCARGA,\n" +
                        "                       NUCIOT, NUAFT, IDOPERTRANSP, STATUSOPER)\n" +
                        "VALUES (NEXT VALUE FOR SANKHYA.SQ_AD_SMTDCT, {CODEMP}, {ORDEMCARGA},\n" +
                        "           {NUCIOT}, {NUAFT}, {IDOPERTRANSP}, 'A')");
                queryExecutor.close();

                contextoAcao.setMensagemRetorno("Documento declarado com sucesso!");

            } else {
                contextoAcao.mostraErro("Erro ao gerar CIOT.\nConsulte a tela 'Log Integração Target' para mais informações.");

                LogFile.logger.info("Erro ao gerar CIOT".concat(connection.getResponseMessage()));
            }
        } catch (IOException e) {

            contextoAcao.mostraErro("Erro ao gerar CIOT.\nConsulte a tela 'Log Integração Target' para mais informações.");

            LogFile.logger.info("Erro ao gerar CIOT".concat(connection.getResponseMessage()));

        }
    }
}
