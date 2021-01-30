package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa os Documentos da Quitação.
 *
 * @since v1.0
 */
public class DocumentosQuitacao {

    private String nomeDocumento = "";
    private String numeroIdentificadorDocumento = "";
    private Boolean obrigatorio = false;
    private Boolean documentoGeradoDestino = false;

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public String getNumeroIdentificadorDocumento() {
        return numeroIdentificadorDocumento;
    }

    public void setNumeroIdentificadorDocumento(String numeroIdentificadorDocumento) {
        this.numeroIdentificadorDocumento = numeroIdentificadorDocumento;
    }

    public Boolean getObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(Boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public Boolean getDocumentoGeradoDestino() {
        return documentoGeradoDestino;
    }

    public void setDocumentoGeradoDestino(Boolean documentoGeradoDestino) {
        this.documentoGeradoDestino = documentoGeradoDestino;
    }
}
