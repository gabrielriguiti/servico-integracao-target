package br.com.sankhyamt.integracaotarget.extension;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
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
 * @since v1.0
 * @version 1.0
 */
public class AtualizarOperacaoTransporte implements AcaoRotinaJava {

    /**
     * Executa o botão de ação Atualizar Operação de Transporte.
     * @param contextoAcao
     * @throws Exception
     */
    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {

        BigDecimal idOperacao = BigDecimal.ZERO;
        BigDecimal ordemCarga = BigDecimal.ZERO;
        BigDecimal codEmp = BigDecimal.ZERO;
        BigDecimal codAfretamento = BigDecimal.ZERO;
        String statusOper = "";
        String motivoCancelamento = (String) contextoAcao.getParam("MOTIVOCANC");

        Registro[] linhasSelecionadas = contextoAcao.getLinhas();

        for (Registro i : linhasSelecionadas) {

            idOperacao = (BigDecimal) i.getCampo("IDOPERTRANSP");
            statusOper = (String) i.getCampo("STATUSOPER");
            ordemCarga = (BigDecimal) i.getCampo("ORDEMCARGA");
            codEmp = (BigDecimal) i.getCampo("CODEMP");
            codAfretamento = (BigDecimal) i.getCampo("NUAFT");
        }

        if (statusOper.equals("C")) {

            contextoAcao.mostraErro("Operações 'Canceladas' não podem ser alteradas.");

        } else if (statusOper.equals("AN")) {

            contextoAcao.mostraErro("Operações 'Anuladas' não podem ser alteradas.");

        } else if (statusOper.equals("E")) {

            contextoAcao.mostraErro("Operações 'Encerradas' não podem ser alteradas.");
        }

        URL url = new URL("http://10.40.3.242:9091/target/atualizarOperacao/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        JsonObject operacaoTransporte = new JsonObject();

        operacaoTransporte.addProperty("idOperacao", idOperacao);
        operacaoTransporte.addProperty("motivo", motivoCancelamento);
        operacaoTransporte.addProperty("instrucao", 4);
        operacaoTransporte.addProperty("ordemCarga", ordemCarga);
        operacaoTransporte.addProperty("codemp", codEmp);
        operacaoTransporte.addProperty("codaft", codAfretamento);

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

                if (!jsonObject.get("idOperacaoTransporte").toString()
                        .replace("\"","").equals("0")){

                    contextoAcao.setMensagemRetorno("Operação atualizada com sucesso!");
                }


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
