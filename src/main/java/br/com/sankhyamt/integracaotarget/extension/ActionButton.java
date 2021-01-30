package br.com.sankhyamt.integracaotarget.extension;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import com.google.gson.JsonObject;

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
public class ActionButton implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {

        BigDecimal ordemCarga = BigDecimal.ZERO;
        BigDecimal codEmp = BigDecimal.ZERO;
        BigDecimal codAfretamento = BigDecimal.ZERO;

        Registro[] linhasSelecionadas = contextoAcao.getLinhas();

        for (Registro i : linhasSelecionadas){

            ordemCarga = (BigDecimal) i.getCampo("ORDEMCARGA");
            codEmp = (BigDecimal) i.getCampo("CODEMP");
            codAfretamento = (BigDecimal) i.getCampo("CODAFT");
        }

        URL url = new URL("http://localhost:9091/target/gerarCiot/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json; utf-8");
        connection.setRequestProperty("Accept","application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        JsonObject viagem = new JsonObject();

        viagem.addProperty("ordemCarga",ordemCarga);
        viagem.addProperty("codemp",codEmp);
        viagem.addProperty("codaft",codAfretamento);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(viagem.toString());
            outputStreamWriter.flush();

            StringBuilder response = new StringBuilder();

            Integer httpResult = connection.getResponseCode();

            if (httpResult == HttpURLConnection.HTTP_OK){

                BufferedReader responseReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(),"utf-8")
                );

                String line = null;

                while ((line = responseReader.readLine()) != null){

                    response.append(line + "\n");
                }

                responseReader.close();

                contextoAcao.mostraErro(response.toString());
            } else {
                contextoAcao.mostraErro("Erro ao gerar CIOT.\nConsulte o log para mais informações.");

                LogFile.logger.info("Erro ao gerar CIOT".concat(connection.getResponseMessage()));
            }
        } catch (IOException e){

            contextoAcao.mostraErro(e.getMessage());

        }
    }

    /*public static void main(String[] args) throws Exception {

        ActionButton actionButton = new ActionButton();

        ContextoAcao contextoAcao = null;
        actionButton.doAction(contextoAcao);
    }*/
}
