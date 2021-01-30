package br.com.sankhyamt.integracaotarget.server;

import br.com.sankhyamt.integracaotarget.api.CiotResource;
import br.com.sankhyamt.integracaotarget.util.LogFile;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Classe que cria o servidor HTTP.
 *
 * @since v1.0
 */
public class StartServer {

    private static HttpServer server = null;
    public static Integer porta = 9091;

    /**
     * Inicia o servidor HTTP.
     */
    public static void startServer(){

        try {
            server = HttpServer.create(new InetSocketAddress(porta),0);
            server.createContext("/target/gerarCiot/",new CiotResource());
            server.setExecutor(null);
            server.start();

        } catch (IOException e) {

            LogFile.logger.info(e.getMessage());
        }
    }
}
