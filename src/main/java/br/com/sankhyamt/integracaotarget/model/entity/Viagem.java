package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa a viagem.
 *
 * @author Gabriel Riguiti
 * @since v1.0
 */
public class Viagem {

    private String proprietarioANTT;
    private String ordemCarga;
    private String codEmp;
    private String codAfretamento;

    public String getProprietarioANTT() {
        return proprietarioANTT;
    }

    public void setProprietarioANTT(String proprietarioANTT) {
        this.proprietarioANTT = proprietarioANTT;
    }

    public String getOrdemCarga() {
        return ordemCarga;
    }

    public void setOrdemCarga(String ordemCarga) {
        this.ordemCarga = ordemCarga;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

    public String getCodAfretamento() {
        return codAfretamento;
    }

    public void setCodAfretamento(String codAfretamento) {
        this.codAfretamento = codAfretamento;
    }
}
