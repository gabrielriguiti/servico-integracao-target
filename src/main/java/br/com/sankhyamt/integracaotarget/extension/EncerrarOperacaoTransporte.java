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

public class EncerrarOperacaoTransporte implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {

        BigDecimal idOperacao = new BigDecimal(0);
        String statusOper = "";

        Registro[] linhasSelecionadas = contextoAcao.getLinhas();

        for (Registro i : linhasSelecionadas) {

            idOperacao = (BigDecimal) i.getCampo("IDOPERTRANSP");
            statusOper = (String) i.getCampo("STATUSOPER");
        }

        if (statusOper.equals("C")) {

            contextoAcao.mostraErro("Operações 'Canceladas' não podem ser encerradas.");

        } else if (statusOper.equals("AN")) {

            contextoAcao.mostraErro("Operações 'Anuladas' não podem ser encerradas.");

        } else if (statusOper.equals("E")) {

            contextoAcao.mostraErro("Operação de Transportes já encerrada.");
        }

        URL url = new URL("http://10.40.3.242:9091/target/encerrarOperacao/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        JsonObject operacaoTransporte = new JsonObject();

        operacaoTransporte.addProperty("idOperacao", idOperacao);
        operacaoTransporte.addProperty("instrucao", 3);

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

                String idEncerramento = jsonObject.get("idEncerramento").toString();

                QueryExecutor queryExecutor = contextoAcao.getQuery();
                queryExecutor.setParam("IDOPERTRANSP", idOperacao);
                queryExecutor.setParam("IDENCERRAMENTO", idEncerramento.replaceAll("\"", ""));
                queryExecutor.update("UPDATE AD_SMTDCT\n" +
                        "SET STATUSOPER = 'E',\n" +
                        "    IDENCERRAMENTO   = {IDENCERRAMENTO}\n" +
                        "WHERE IDOPERTRANSP = {IDOPERTRANSP}");
                queryExecutor.close();

                contextoAcao.setMensagemRetorno("Operação encerrada com sucesso!");

            } else {
                contextoAcao.mostraErro("Erro ao cancelar CIOT.\nConsulte a tela 'Log Integração Target' para mais informações.");

                LogFile.logger.info("Erro ao cancelar CIOT".concat(connection.getResponseMessage()));
            }
        } catch (IOException e) {

            contextoAcao.mostraErro("Erro ao cancelar CIOT.\nConsulte a tela 'Log Integração Target' para mais informações.");

            LogFile.logger.info("Erro ao cancelar CIOT".concat(connection.getResponseMessage()));

        }
    }
}
