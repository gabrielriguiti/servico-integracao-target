package br.com.sankhyamt.integracaotarget.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @since v1.3
 */
public class RequestFormat {

    /**
     * Retira os caracteres especiais da requisição.
     * @since v1.3
     * @param request Recebe a requisição.
     * @return Retorna a requisição formatada sem caracteres especiais.
     */
    public static String requestFormat(String request){

        String normalizedString = Normalizer.normalize(request, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(normalizedString).replaceAll("");
    }
}
