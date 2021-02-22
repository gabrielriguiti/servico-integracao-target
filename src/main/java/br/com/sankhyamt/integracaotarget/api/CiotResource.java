package br.com.sankhyamt.integracaotarget.api;

import br.com.sankhyamt.integracaotarget.exception.IntegracaoException;
import br.com.sankhyamt.integracaotarget.model.entity.Viagem;
import br.com.sankhyamt.integracaotarget.service.CiotService;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que receberá as requisições de emissão do CIOT.
 */
public class CiotResource implements HttpHandler {

    CiotService ciotService = new CiotService();

    /**
     * Esse método recebe os dados para geração do CIOT, e retornar o número do CIOT gerado.
     *
     * @param exchange
     * @throws IOException
     * @sice v1.0
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Map<String, Object> parameters = new HashMap<String, Object>();

        OutputStream responseBody = exchange.getResponseBody();

        StringBuilder json = new StringBuilder();

        InputStream request = exchange.getRequestBody();

        int i;
        while ((i = request.read()) != -1) {
            json.append((char) i);
        }

        try {
            JsonObject jsonObject = new JsonParser().parse(json.toString()).getAsJsonObject();
            JsonObject response = new JsonObject();

            String instrucao = getInstrucao(jsonObject);

            Viagem afretamento = new Viagem();

            switch (instrucao) {
                case "1":
                    afretamento.setCodAfretamento(getCodAfretamento(jsonObject));
                    afretamento.setCodEmp(getCodEmpresa(jsonObject));
                    afretamento.setOrdemCarga(getOrdemCarga(jsonObject));

                    String[] dadosCiot = ciotService.ciotCenario3(afretamento);

                    response.addProperty("nroCiot", dadosCiot[0]);
                    response.addProperty("idOperacaoTransporte", dadosCiot[1]);

                    break;
                case "2":
                    String motivo = getMotivo(jsonObject);
                    String idCancelamento = CiotService.cancelarOperacaoTransporte(
                            getIdOperacao(jsonObject),
                            getMotivo(jsonObject));

                    response.addProperty("idCancelamento", idCancelamento);

                    break;
                case "3":
                    String idEncerramento = CiotService.encerrarOperacaoTransporte(
                            getIdOperacao(jsonObject));

                    response.addProperty("idEncerramento", idEncerramento);

                    break;
                case "4":
                    afretamento.setCodAfretamento(getCodAfretamento(jsonObject));
                    afretamento.setCodEmp(getCodEmpresa(jsonObject));
                    afretamento.setOrdemCarga(getOrdemCarga(jsonObject));
                    afretamento.setIdOperacao(getIdOperacao(jsonObject));

                    String[] dadosCiotAtualizado = ciotService.ciotCenario3(afretamento);
                    response.addProperty("idOperacaoTransporte", dadosCiotAtualizado[1]);

                    break;
                case "5":
                    String documentoBinario = CiotService.emitirCiot(getIdOperacao(jsonObject));

                    response.addProperty("documentoBinario", documentoBinario);

                    break;
            }

            exchange.sendResponseHeaders(200, response.toString().length());

            responseBody.write(response.toString().getBytes());
            responseBody.close();
        } catch (JsonSyntaxException | IOException | SQLException e) {

            LogFile.logger.info("Erro na integração ".concat(e.getMessage()));

            throw new IntegracaoException(e.getMessage());
        }
    }

    /**
     * @param JSON Recebe o objeto JSON.
     * @return Retorna a ordem de carga.
     */
    public String getOrdemCarga(JsonObject JSON) {

        String ordemCarga = JSON.get("ordemCarga").toString();

        return ordemCarga;
    }

    /**
     * @param JSON Recebe o objeto JSON.
     * @return Retorna a empresa.
     */
    public String getCodEmpresa(JsonObject JSON) {

        String codemp = JSON.get("codemp").toString();

        return codemp;
    }

    /**
     * @param JSON Recebe o objeto JSON.
     * @return Retorna o código do afretamento.
     */
    public String getCodAfretamento(JsonObject JSON) {

        String codAfretamento = JSON.get("codaft").toString();

        return codAfretamento;
    }

    /**
     * @param JSON Recebe o objeto JSON.
     * @return Retorna a instrução da requisição.
     */
    public String getInstrucao(JsonObject JSON) {

        String instrucao = JSON.get("instrucao").toString();

        return instrucao;
    }

    /**
     * @param JSON Recebe o objeto JSON.
     * @return Retorna o id da operação.
     */
    public String getIdOperacao(JsonObject JSON) {

        String idOperacao = JSON.get("idOperacao").toString();

        return idOperacao;
    }

    /**
     * @param JSON Recebe o objeto JSON.
     * @return Retorna o motivo do cancelamento.
     */
    public String getMotivo(JsonObject JSON) {

        String motivo = JSON.get("motivo").toString();

        return motivo;
    }
}
