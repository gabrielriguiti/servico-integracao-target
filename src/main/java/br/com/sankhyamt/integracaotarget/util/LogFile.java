package br.com.sankhyamt.integracaotarget.util;

import br.com.sankhyamt.integracaotarget.exception.ServerException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @since v1.0
 * @version 1.1
 */
public class LogFile {

    public static Logger logger;

    /**
     * Cria o arquivo de log.
     */
    public static void createLog(){

        logger = Logger.getLogger("logTotal");

        FileHandler fileHandler;
        String diretorio = ("C:\\SANKHYA\\Integracao-target\\logs\\");
        String arquivo = "server.log";

        try {

            fileHandler = new FileHandler(diretorio+arquivo);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.setUseParentHandlers(false);
            logger.info("Arquivo de log criado.");

        } catch (IOException e) {

            throw new ServerException(e.getMessage());
        }
    }
}
