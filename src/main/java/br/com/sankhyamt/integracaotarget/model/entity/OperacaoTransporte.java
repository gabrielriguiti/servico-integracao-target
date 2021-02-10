package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa a Operação de Transporte.
 *
 * @since v1.0
 */
public class OperacaoTransporte {

    private Integer idOperacaoTransporte = 0;
    private String codCentroDeCusto = "";
    private String NCM = "2302";
    private Integer proprietarioCarga = 0;
    private Double pesoCarga = 0.0;
    private String tipoOperacao = "1";
    private Integer municipioOrigemCodigoIBGE = 0;
    private Integer municipioDestinoCodigoIBGE = 0;
    private String dataHoraInicio = "";
    private String dataHoraTermino = "";
    private String CNPJCPFContratado = "";
    private Double valorFrete = 0.0;
    private Double valorCombustivel = 0.0;
    private Double valorPedagio = 0.0;
    private Double valorDespesas = 0.0;
    private Double valorImpostoSestSenat = 0.0;
    private Double valorImpostoIRRF = 0.0;
    private Double valorImpostoINSS = 0.0;
    private Double valorImpostoICMSISSQN = 0.0;
    private Boolean parcelaUnica = true;
    private Integer modoCompraValePedagio = 0;
    private Integer categoriaVeiculo = 0;
    private String nomeMotorista = "";
    private String CPFMotorista = "";
    private String RNTRCMotorista = "";
    private String itemFinanceiro = "";
    private Integer idRotaModelo = 14512;
    private Boolean deduzirImpostos = false;
    private Double tarifasBancarias = 0.0;
    private Integer quantidadeTarifasBancarias = 0;
    private String idIntegrador = "";
    private Double valorDescontoAntecipado = 0.0;
    private String CPFCNPJParticipanteDestinatario = "";
    private String CPFCNPJParticipanteContratante = "";
    private String CPFCNPJParticipanteSubcontratante = "";
    private String CPFCNPJParticipanteConsignatario = "";
    private String numeroLacreTranporteCombustivel = "";
    private String numeroCartaoValePedagio = "";
    private Boolean quitacao = false;
    private String documentoValePedagio = "";
    private Double vlrImpostoISS = 0.0;
    private Integer tipoFormaPagamento = 0;
    private String CEPOrigem = "";
    private String CEPDestino = "";
    private Integer tipoCargaANTT = 0;
    private Double distanciaPercorrida = 0.0;
    private Boolean altoDesempenho = false;
    private Boolean destinacaoComercial = false;
    private String CNPJPagamentoOutraIPEF = "";

    public Integer getIdOperacaoTransporte() {
        return idOperacaoTransporte;
    }

    public void setIdOperacaoTransporte(Integer idOperacaoTransporte) {
        this.idOperacaoTransporte = idOperacaoTransporte;
    }

    public String getCodCentroDeCusto() {
        return codCentroDeCusto;
    }

    public void setCodCentroDeCusto(String codCentroDeCusto) {
        this.codCentroDeCusto = codCentroDeCusto;
    }

    public String getNCM() {
        return NCM;
    }

    public void setNCM(String NCM) {
        this.NCM = NCM;
    }

    public Integer getProprietarioCarga() {
        return proprietarioCarga;
    }

    public void setProprietarioCarga(Integer proprietarioCarga) {
        this.proprietarioCarga = proprietarioCarga;
    }

    public Double getPesoCarga() {
        return pesoCarga;
    }

    public void setPesoCarga(Double pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Integer getMunicipioOrigemCodigoIBGE() {
        return municipioOrigemCodigoIBGE;
    }

    public void setMunicipioOrigemCodigoIBGE(Integer municipioOrigemCodigoIBGE) {
        this.municipioOrigemCodigoIBGE = municipioOrigemCodigoIBGE;
    }

    public Integer getMunicipioDestinoCodigoIBGE() {
        return municipioDestinoCodigoIBGE;
    }

    public void setMunicipioDestinoCodigoIBGE(Integer municipioDestinoCodigoIBGE) {
        this.municipioDestinoCodigoIBGE = municipioDestinoCodigoIBGE;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraTermino() {
        return dataHoraTermino;
    }

    public void setDataHoraTermino(String dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public String getCNPJCPFContratado() {
        return CNPJCPFContratado;
    }

    public void setCNPJCPFContratado(String CNPJCPFContratado) {
        this.CNPJCPFContratado = CNPJCPFContratado;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorCombustivel() {
        return valorCombustivel;
    }

    public void setValorCombustivel(Double valorCombustivel) {
        this.valorCombustivel = valorCombustivel;
    }

    public Double getValorPedagio() {
        return valorPedagio;
    }

    public void setValorPedagio(Double valorPedagio) {
        this.valorPedagio = valorPedagio;
    }

    public Double getValorDespesas() {
        return valorDespesas;
    }

    public void setValorDespesas(Double valorDespesas) {
        this.valorDespesas = valorDespesas;
    }

    public Double getValorImpostoSestSenat() {
        return valorImpostoSestSenat;
    }

    public void setValorImpostoSestSenat(Double valorImpostoSestSenat) {
        this.valorImpostoSestSenat = valorImpostoSestSenat;
    }

    public Double getValorImpostoIRRF() {
        return valorImpostoIRRF;
    }

    public void setValorImpostoIRRF(Double valorImpostoIRRF) {
        this.valorImpostoIRRF = valorImpostoIRRF;
    }

    public Double getValorImpostoINSS() {
        return valorImpostoINSS;
    }

    public void setValorImpostoINSS(Double valorImpostoINSS) {
        this.valorImpostoINSS = valorImpostoINSS;
    }

    public Double getValorImpostoICMSISSQN() {
        return valorImpostoICMSISSQN;
    }

    public void setValorImpostoICMSISSQN(Double valorImpostoICMSISSQN) {
        this.valorImpostoICMSISSQN = valorImpostoICMSISSQN;
    }

    public Boolean getParcelaUnica() {
        return parcelaUnica;
    }

    public void setParcelaUnica(Boolean parcelaUnica) {
        this.parcelaUnica = parcelaUnica;
    }

    public Integer getModoCompraValePedagio() {
        return modoCompraValePedagio;
    }

    public void setModoCompraValePedagio(Integer modoCompraValePedagio) {
        this.modoCompraValePedagio = modoCompraValePedagio;
    }

    public Integer getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(Integer categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getCPFMotorista() {
        return CPFMotorista;
    }

    public void setCPFMotorista(String CPFMotorista) {
        this.CPFMotorista = CPFMotorista;
    }

    public String getRNTRCMotorista() {
        return RNTRCMotorista;
    }

    public void setRNTRCMotorista(String RNTRCMotorista) {
        this.RNTRCMotorista = RNTRCMotorista;
    }

    public String getItemFinanceiro() {
        return itemFinanceiro;
    }

    public void setItemFinanceiro(String itemFinanceiro) {
        this.itemFinanceiro = itemFinanceiro;
    }

    public Integer getIdRotaModelo() {
        return idRotaModelo;
    }

    public void setIdRotaModelo(Integer idRotaModelo) {
        this.idRotaModelo = idRotaModelo;
    }

    public Boolean getDeduzirImpostos() {
        return deduzirImpostos;
    }

    public void setDeduzirImpostos(Boolean deduzirImpostos) {
        this.deduzirImpostos = deduzirImpostos;
    }

    public Double getTarifasBancarias() {
        return tarifasBancarias;
    }

    public void setTarifasBancarias(Double tarifasBancarias) {
        this.tarifasBancarias = tarifasBancarias;
    }

    public Integer getQuantidadeTarifasBancarias() {
        return quantidadeTarifasBancarias;
    }

    public void setQuantidadeTarifasBancarias(Integer quantidadeTarifasBancarias) {
        this.quantidadeTarifasBancarias = quantidadeTarifasBancarias;
    }

    public String getIdIntegrador() {
        return idIntegrador;
    }

    public void setIdIntegrador(String idIntegrador) {
        this.idIntegrador = idIntegrador;
    }

    public Double getValorDescontoAntecipado() {
        return valorDescontoAntecipado;
    }

    public void setValorDescontoAntecipado(Double valorDescontoAntecipado) {
        this.valorDescontoAntecipado = valorDescontoAntecipado;
    }

    public String getCPFCNPJParticipanteDestinatario() {
        return CPFCNPJParticipanteDestinatario;
    }

    public void setCPFCNPJParticipanteDestinatario(String CPFCNPJParticipanteDestinatario) {
        this.CPFCNPJParticipanteDestinatario = CPFCNPJParticipanteDestinatario;
    }

    public String getCPFCNPJParticipanteContratante() {
        return CPFCNPJParticipanteContratante;
    }

    public void setCPFCNPJParticipanteContratante(String CPFCNPJParticipanteContratante) {
        this.CPFCNPJParticipanteContratante = CPFCNPJParticipanteContratante;
    }

    public String getCPFCNPJParticipanteSubcontratante() {
        return CPFCNPJParticipanteSubcontratante;
    }

    public void setCPFCNPJParticipanteSubcontratante(String CPFCNPJParticipanteSubcontratante) {
        this.CPFCNPJParticipanteSubcontratante = CPFCNPJParticipanteSubcontratante;
    }

    public String getCPFCNPJParticipanteConsignatario() {
        return CPFCNPJParticipanteConsignatario;
    }

    public void setCPFCNPJParticipanteConsignatario(String CPFCNPJParticipanteConsignatario) {
        this.CPFCNPJParticipanteConsignatario = CPFCNPJParticipanteConsignatario;
    }

    public String getNumeroLacreTranporteCombustivel() {
        return numeroLacreTranporteCombustivel;
    }

    public void setNumeroLacreTranporteCombustivel(String numeroLacreTranporteCombustivel) {
        this.numeroLacreTranporteCombustivel = numeroLacreTranporteCombustivel;
    }

    public String getNumeroCartaoValePedagio() {
        return numeroCartaoValePedagio;
    }

    public void setNumeroCartaoValePedagio(String numeroCartaoValePedagio) {
        this.numeroCartaoValePedagio = numeroCartaoValePedagio;
    }

    public Boolean getQuitacao() {
        return quitacao;
    }

    public void setQuitacao(Boolean quitacao) {
        this.quitacao = quitacao;
    }

    public String getDocumentoValePedagio() {
        return documentoValePedagio;
    }

    public void setDocumentoValePedagio(String documentoValePedagio) {
        this.documentoValePedagio = documentoValePedagio;
    }

    public Double getVlrImpostoISS() {
        return vlrImpostoISS;
    }

    public void setVlrImpostoISS(Double vlrImpostoISS) {
        this.vlrImpostoISS = vlrImpostoISS;
    }

    public Integer getTipoFormaPagamento() {
        return tipoFormaPagamento;
    }

    public void setTipoFormaPagamento(Integer tipoFormaPagamento) {
        this.tipoFormaPagamento = tipoFormaPagamento;
    }

    public String getCEPOrigem() {
        return CEPOrigem;
    }

    public void setCEPOrigem(String CEPOrigem) {
        this.CEPOrigem = CEPOrigem;
    }

    public String getCEPDestino() {
        return CEPDestino;
    }

    public void setCEPDestino(String CEPDestino) {
        this.CEPDestino = CEPDestino;
    }

    public Integer getTipoCargaANTT() {
        return tipoCargaANTT;
    }

    public void setTipoCargaANTT(Integer tipoCargaANTT) {
        this.tipoCargaANTT = tipoCargaANTT;
    }

    public Double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(Double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public Boolean getAltoDesempenho() {
        return altoDesempenho;
    }

    public void setAltoDesempenho(Boolean altoDesempenho) {
        this.altoDesempenho = altoDesempenho;
    }

    public Boolean getDestinacaoComercial() {
        return destinacaoComercial;
    }

    public void setDestinacaoComercial(Boolean destinacaoComercial) {
        this.destinacaoComercial = destinacaoComercial;
    }

    public String getCNPJPagamentoOutraIPEF() {
        return CNPJPagamentoOutraIPEF;
    }

    public void setCNPJPagamentoOutraIPEF(String CNPJPagamentoOutraIPEF) {
        this.CNPJPagamentoOutraIPEF = CNPJPagamentoOutraIPEF;
    }

    @Override
    public String toString() {
        return "OperacaoTransporte{" +
                "idOperacaoTransporte=" + idOperacaoTransporte +
                ", codCentroDeCusto='" + codCentroDeCusto + '\'' +
                ", NCM='" + NCM + '\'' +
                ", proprietarioCarga=" + proprietarioCarga +
                ", pesoCarga=" + pesoCarga +
                ", tipoOperacao='" + tipoOperacao + '\'' +
                ", municipioOrigemCodigoIBGE=" + municipioOrigemCodigoIBGE +
                ", municipioDestinoCodigoIBGE=" + municipioDestinoCodigoIBGE +
                ", dataHoraInicio='" + dataHoraInicio + '\'' +
                ", dataHoraTermino='" + dataHoraTermino + '\'' +
                ", CNPJCPFContratado='" + CNPJCPFContratado + '\'' +
                ", valorFrete=" + valorFrete +
                ", valorCombustivel=" + valorCombustivel +
                ", valorPedagio=" + valorPedagio +
                ", valorDespesas=" + valorDespesas +
                ", valorImpostoSestSenat=" + valorImpostoSestSenat +
                ", valorImpostoIRRF=" + valorImpostoIRRF +
                ", valorImpostoINSS=" + valorImpostoINSS +
                ", valorImpostoICMSISSQN=" + valorImpostoICMSISSQN +
                ", parcelaUnica=" + parcelaUnica +
                ", modoCompraValePedagio=" + modoCompraValePedagio +
                ", categoriaVeiculo=" + categoriaVeiculo +
                ", nomeMotorista='" + nomeMotorista + '\'' +
                ", CPFMotorista='" + CPFMotorista + '\'' +
                ", RNTRCMotorista='" + RNTRCMotorista + '\'' +
                ", itemFinanceiro='" + itemFinanceiro + '\'' +
                ", idRotaModelo=" + idRotaModelo +
                ", deduzirImpostos=" + deduzirImpostos +
                ", tarifasBancarias=" + tarifasBancarias +
                ", quantidadeTarifasBancarias=" + quantidadeTarifasBancarias +
                ", idIntegrador='" + idIntegrador + '\'' +
                ", valorDescontoAntecipado=" + valorDescontoAntecipado +
                ", CPFCNPJParticipanteDestinatario='" + CPFCNPJParticipanteDestinatario + '\'' +
                ", CPFCNPJParticipanteContratante='" + CPFCNPJParticipanteContratante + '\'' +
                ", CPFCNPJParticipanteSubcontratante='" + CPFCNPJParticipanteSubcontratante + '\'' +
                ", CPFCNPJParticipanteConsignatario='" + CPFCNPJParticipanteConsignatario + '\'' +
                ", numeroLacreTranporteCombustivel='" + numeroLacreTranporteCombustivel + '\'' +
                ", numeroCartaoValePedagio='" + numeroCartaoValePedagio + '\'' +
                ", quitacao=" + quitacao +
                ", documentoValePedagio='" + documentoValePedagio + '\'' +
                ", vlrImpostoISS=" + vlrImpostoISS +
                ", tipoFormaPagamento=" + tipoFormaPagamento +
                ", CEPOrigem='" + CEPOrigem + '\'' +
                ", CEPDestino='" + CEPDestino + '\'' +
                ", tipoCargaANTT=" + tipoCargaANTT +
                ", distanciaPercorrida=" + distanciaPercorrida +
                ", altoDesempenho=" + altoDesempenho +
                ", destinacaoComercial=" + destinacaoComercial +
                ", CNPJPagamentoOutraIPEF='" + CNPJPagamentoOutraIPEF + '\'' +
                '}';
    }
}
