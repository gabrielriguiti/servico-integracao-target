package br.com.sankhyamt.integracaotarget;

import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;
import br.com.sankhyamt.integracaotarget.server.StartServer;
import br.com.sankhyamt.integracaotarget.util.LogFile;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        LogFile.createLog();
        LogFile.logger.info("Iniciando serviço de integração...");

        ConnectionSQLServer.checkConnectionDB();
        StartServer.startServer();

        LogFile.logger.info("Servidor iniciado na porta " + StartServer.porta);
    }
}
