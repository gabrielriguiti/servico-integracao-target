package br.com.sankhyamt.integracaotarget;

import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.properties.AuthProperties;
import br.com.sankhyamt.integracaotarget.server.StartServer;
import br.com.sankhyamt.integracaotarget.util.LogFile;

/**
 * @since v1.0
 * @version 1.1
 */
public class Main {

    /**
     * Método principal
     * @param args Argumentos da aplicação
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, IllegalAccessException,
            ClassNotFoundException, InstantiationException {

        AuthProperties authProperties = new AuthProperties();
        authProperties.getAuthProperties();

        LogFile.createLog();
        LogFile.logger.info("Iniciando serviço de integração...");

        ConnectionSQLServer.checkConnectionDB();

        StartServer.startServer();

        LogFile.logger.info("Servidor iniciado na porta " + StartServer.porta);
    }
}
