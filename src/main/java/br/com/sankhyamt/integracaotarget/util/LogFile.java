package br.com.sankhyamt.integracaotarget.util;

import br.com.sankhyamt.integracaotarget.exception.ServerException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {

    public static Logger logger;

    public static void createLog(){

        logger = Logger.getLogger("logTotal");

        FileHandler fileHandler;
        String diretorio = ("C:/Users/setransportes2/sankhya/");
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
