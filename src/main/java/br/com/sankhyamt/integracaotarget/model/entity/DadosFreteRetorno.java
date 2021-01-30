package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa os Dados do Frete Retorno.
 *
 * @since v1.0
 */
public class DadosFreteRetorno {

    private Double distanciaRetorno = 0.0;
    private String CEPRetorno = "";

    public Double getDistanciaRetorno() {
        return distanciaRetorno;
    }

    public void setDistanciaRetorno(Double distanciaRetorno) {
        this.distanciaRetorno = distanciaRetorno;
    }

    public String getCEPRetorno() {
        return CEPRetorno;
    }

    public void setCEPRetorno(String CEPRetorno) {
        this.CEPRetorno = CEPRetorno;
    }
}
