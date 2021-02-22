package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa a viagem.
 *
 * @author Gabriel Riguiti
 * @since v1.0
 */
public class Viagem {

    private String proprietarioANTT = "0";
    private String ordemCarga = "0";
    private String codEmp = "0";
    private String codAfretamento = "0";
    private String idOperacao = "0";

    public String getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(String idOperacao) {
        this.idOperacao = idOperacao;
    }

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

    @Override
    public String toString() {
        return "Viagem{" +
                "proprietarioANTT='" + proprietarioANTT + '\'' +
                ", ordemCarga='" + ordemCarga + '\'' +
                ", codEmp='" + codEmp + '\'' +
                ", codAfretamento='" + codAfretamento + '\'' +
                '}';
    }
}
