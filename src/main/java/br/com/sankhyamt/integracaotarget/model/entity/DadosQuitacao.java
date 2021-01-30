package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa os Dados da Quitação.
 *
 * @since v1.0
 */
public class DadosQuitacao {

    private Double valorMercadoria = 0.0;
    private Double pesoCarregadoMercadoria = 0.0;
    private Integer quantidadeCarregada = 0;
    private Integer tipoCalculoAvaria = 0;
    private Boolean encerraNaANTT = false;
    private Double porcentagemToleranciaPeso = 0.0;
    private Integer tipoToleranciaPeso = 0;
    private Double porcentagemPesoAMaior = 0.0;
    private Integer idsTerminaisCarregamento = 0;
    private Boolean quitaEmTodosTerminais = false;

    public Double getValorMercadoria() {
        return valorMercadoria;
    }

    public void setValorMercadoria(Double valorMercadoria) {
        this.valorMercadoria = valorMercadoria;
    }

    public Double getPesoCarregadoMercadoria() {
        return pesoCarregadoMercadoria;
    }

    public void setPesoCarregadoMercadoria(Double pesoCarregadoMercadoria) {
        this.pesoCarregadoMercadoria = pesoCarregadoMercadoria;
    }

    public Integer getQuantidadeCarregada() {
        return quantidadeCarregada;
    }

    public void setQuantidadeCarregada(Integer quantidadeCarregada) {
        this.quantidadeCarregada = quantidadeCarregada;
    }

    public Integer getTipoCalculoAvaria() {
        return tipoCalculoAvaria;
    }

    public void setTipoCalculoAvaria(Integer tipoCalculoAvaria) {
        this.tipoCalculoAvaria = tipoCalculoAvaria;
    }

    public Boolean getEncerraNaANTT() {
        return encerraNaANTT;
    }

    public void setEncerraNaANTT(Boolean encerraNaANTT) {
        this.encerraNaANTT = encerraNaANTT;
    }

    public Double getPorcentagemToleranciaPeso() {
        return porcentagemToleranciaPeso;
    }

    public void setPorcentagemToleranciaPeso(Double porcentagemToleranciaPeso) {
        this.porcentagemToleranciaPeso = porcentagemToleranciaPeso;
    }

    public Integer getTipoToleranciaPeso() {
        return tipoToleranciaPeso;
    }

    public void setTipoToleranciaPeso(Integer tipoToleranciaPeso) {
        this.tipoToleranciaPeso = tipoToleranciaPeso;
    }

    public Double getPorcentagemPesoAMaior() {
        return porcentagemPesoAMaior;
    }

    public void setPorcentagemPesoAMaior(Double porcentagemPesoAMaior) {
        this.porcentagemPesoAMaior = porcentagemPesoAMaior;
    }

    public Integer getIdsTerminaisCarregamento() {
        return idsTerminaisCarregamento;
    }

    public void setIdsTerminaisCarregamento(Integer idsTerminaisCarregamento) {
        this.idsTerminaisCarregamento = idsTerminaisCarregamento;
    }

    public Boolean getQuitaEmTodosTerminais() {
        return quitaEmTodosTerminais;
    }

    public void setQuitaEmTodosTerminais(Boolean quitaEmTodosTerminais) {
        this.quitaEmTodosTerminais = quitaEmTodosTerminais;
    }
}
