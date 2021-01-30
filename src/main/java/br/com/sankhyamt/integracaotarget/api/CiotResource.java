package br.com.sankhyamt.integracaotarget.api;

import br.com.sankhyamt.integracaotarget.model.entity.Viagem;
import br.com.sankhyamt.integracaotarget.service.CiotService;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que receberá as requisições de emissão do CIOT.
 */
public class CiotResource implements HttpHandler {

    CiotService ciotService = new CiotService();

    /**
     * Esse método recebe os dados para geração do CIOT, e retornar o número do CIOT gerado.
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

        JsonObject jsonObject = new JsonParser().parse(json.toString()).getAsJsonObject();
        JsonObject response = new JsonObject();

        Viagem afretamento = new Viagem();
        afretamento.setCodAfretamento(getCodAfretamento(jsonObject));
        afretamento.setCodEmp(getCodEmpresa(jsonObject));
        afretamento.setOrdemCarga(getOrdemCarga(jsonObject));

        String nroCiot = ciotService.ciotCenario3(afretamento);

        response.addProperty("nroCiot",nroCiot);

        exchange.sendResponseHeaders(200,response.toString().length());

        responseBody.write(response.toString().getBytes());
        responseBody.close();
    }

    public String getOrdemCarga(JsonObject JSON){

        String ordemCarga = JSON.get("ordemCarga").toString();

        return ordemCarga;
    }

    public String getCodEmpresa(JsonObject JSON){

        String codemp = JSON.get("codemp").toString();

        return codemp;
    }

    public String getCodAfretamento(JsonObject JSON){

        String codAfretamento = JSON.get("codaft").toString();

        return codAfretamento;
    }
}
