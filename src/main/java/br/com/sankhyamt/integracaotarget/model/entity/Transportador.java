package br.com.sankhyamt.integracaotarget.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que representa o parceiro transportador.
 *
 * @author Gabriel Riguiti
 * @since v1.0
 */
public class Transportador {

    private Integer idTransportador = 0;
    private String RNTRC = "";
    private Integer instrucao = 0;
    private String CPFCNPJ = "";
    private String nome = "";
    private String sobrenome = "";
    private String razaoSocial = "";
    private String dataNascimento = "";
    private String RG = "";
    private String orgaoEmissorRg = "";
    private String CNH = "";
    private String tipoCNH = "";
    private String dataValidadeCNH = "";
    private String sexo = "";
    private String naturalidade = "";
    private String nacionalidade = "";
    private String inscricaoEstadual = "";
    private String inscricaoMunicipal = "";
    private String nomeFantasia = "";
    private String dataInscricao = "";
    private Integer idDmAtividadeEconomica = 0;
    private String endereco = "";
    private Integer numeroEndereco;
    private String bairro = "";
    private String CEP = "";
    private Integer codIbgeMunicipio = 0;
    private String indentificadorEndereco = "";
    private String telefoneFixo = "0";
    private String telefoneCelular = "0";
    private String estadoCivil = "";
    private String email = "";
    private String usuario = "";
    private String codigoBanco = "";
    private String codigoAgencia = "";
    private String digitoAgencia = "";
    private String contaCorrente = "";
    private String digitoContaCorrente = "";
    private Boolean flagContaPoupanca = false;
    private String variacaoContaPoupanca = "";
    private String nomeContato = "";
    private String cargoContato = "";
    private String CPFCNPJFContato = "";
    private Long telefoneFixoContato = 0l;
    private Long telefoneCelularContato = 0l;
    private String emailContato = "";
    private String dataNascimentoContato = "1900-01-01";
    private String RGContato = "";
    private String orgaoEmissoContato = "";
    private String PisPasep = "";

    /**
     * @param endereco       Recebe a rua do endereço.
     * @param numeroEndereco Recebe o número do enderço.
     * @param bairro         Recebe o bairro do endereço.
     * @return Retorna o endereço completo do parceiro transportador.
     * @sice v1.0
     */
    public String enderecoCompleto(String endereco, Integer numeroEndereco, String bairro) {

        String enderecoCompleto = "Rua " + endereco + ", " + numeroEndereco + ", " + bairro;

        return enderecoCompleto;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public Integer getIdTransportador() {
        return idTransportador;
    }

    public void setIdTransportador(Integer idTransportador) {
        this.idTransportador = idTransportador;
    }

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
    }

    public Integer getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(Integer instrucao) {
        this.instrucao = instrucao;
    }

    public String getCPFCNPJ() {
        return CPFCNPJ;
    }

    public void setCPFCNPJ(String CPFCNPJ) {
        this.CPFCNPJ = CPFCNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getOrgaoEmissorRg() {
        return orgaoEmissorRg;
    }

    public void setOrgaoEmissorRg(String orgaoEmissorRg) {
        this.orgaoEmissorRg = orgaoEmissorRg;
    }

    public String getCNH() {
        return CNH;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public String getTipoCNH() {
        return tipoCNH;
    }

    public void setTipoCNH(String tipoCNH) {
        this.tipoCNH = tipoCNH;
    }

    public String getDataValidadeCNH() {
        return dataValidadeCNH;
    }

    public void setDataValidadeCNH(String dataValidadeCNH) {
        this.dataValidadeCNH = dataValidadeCNH;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(String dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Integer getIdDmAtividadeEconomica() {
        return idDmAtividadeEconomica;
    }

    public void setIdDmAtividadeEconomica(Integer idDmAtividadeEconomica) {
        this.idDmAtividadeEconomica = idDmAtividadeEconomica;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(Integer numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Integer getCodIbgeMunicipio() {
        return codIbgeMunicipio;
    }

    public void setCodIbgeMunicipio(Integer codIbgeMunicipio) {
        this.codIbgeMunicipio = codIbgeMunicipio;
    }

    public String getIndentificadorEndereco() {
        return indentificadorEndereco;
    }

    public void setIndentificadorEndereco(String indentificadorEndereco) {
        this.indentificadorEndereco = indentificadorEndereco;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(String digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getDigitoContaCorrente() {
        return digitoContaCorrente;
    }

    public void setDigitoContaCorrente(String digitoContaCorrente) {
        this.digitoContaCorrente = digitoContaCorrente;
    }

    public Boolean getFlagContaPoupanca() {
        return flagContaPoupanca;
    }

    public void setFlagContaPoupanca(Boolean flagContaPoupanca) {
        this.flagContaPoupanca = flagContaPoupanca;
    }

    public String getVariacaoContaPoupanca() {
        return variacaoContaPoupanca;
    }

    public void setVariacaoContaPoupanca(String variacaoContaPoupanca) {
        this.variacaoContaPoupanca = variacaoContaPoupanca;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getCargoContato() {
        return cargoContato;
    }

    public void setCargoContato(String cargoContato) {
        this.cargoContato = cargoContato;
    }

    public String getCPFCNPJFContato() {
        return CPFCNPJFContato;
    }

    public void setCPFCNPJFContato(String CPFCNPJFContato) {
        this.CPFCNPJFContato = CPFCNPJFContato;
    }

    public Long getTelefoneFixoContato() {
        return telefoneFixoContato;
    }

    public void setTelefoneFixoContato(Long telefoneFixoContato) {
        this.telefoneFixoContato = telefoneFixoContato;
    }

    public Long getTelefoneCelularContato() {
        return telefoneCelularContato;
    }

    public void setTelefoneCelularContato(Long telefoneCelularContato) {
        this.telefoneCelularContato = telefoneCelularContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getDataNascimentoContato() {
        return dataNascimentoContato;
    }

    public void setDataNascimentoContato(String dataNascimentoContato) {
        this.dataNascimentoContato = dataNascimentoContato;
    }

    public String getRGContato() {
        return RGContato;
    }

    public void setRGContato(String RGContato) {
        this.RGContato = RGContato;
    }

    public String getOrgaoEmissoContato() {
        return orgaoEmissoContato;
    }

    public void setOrgaoEmissoContato(String orgaoEmissoContato) {
        this.orgaoEmissoContato = orgaoEmissoContato;
    }

    public String getPisPasep() {
        return PisPasep;
    }

    public void setPisPasep(String pisPasep) {
        PisPasep = pisPasep;
    }

    @Override
    public String toString() {
        return "Transportador{" +
                "RNTRC='" + RNTRC + '\'' +
                ", instrucao=" + instrucao +
                ", CPFCNPJ='" + CPFCNPJ + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", RG='" + RG + '\'' +
                ", orgaoEmissorRg='" + orgaoEmissorRg + '\'' +
                ", CNH='" + CNH + '\'' +
                ", tipoCNH='" + tipoCNH + '\'' +
                ", dataValidadeCNH=" + dataValidadeCNH +
                ", sexo='" + sexo + '\'' +
                ", naturalidade='" + naturalidade + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", inscricaoEstadual='" + inscricaoEstadual + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", dataInscricao=" + dataInscricao +
                ", idDmAtividadeEconomica=" + idDmAtividadeEconomica +
                ", endereco='" + endereco + '\'' +
                ", numeroEndereco=" + numeroEndereco +
                ", bairro='" + bairro + '\'' +
                ", CEP='" + CEP + '\'' +
                ", codIbgeMunicipio=" + codIbgeMunicipio +
                ", indentificadorEndereco='" + indentificadorEndereco + '\'' +
                ", telefoneFixo=" + telefoneFixo +
                ", telefoneCelular=" + telefoneCelular +
                ", estadoCivil=" + estadoCivil +
                ", email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", codigoBanco='" + codigoBanco + '\'' +
                ", codigoAgencia='" + codigoAgencia + '\'' +
                ", digitoAgencia='" + digitoAgencia + '\'' +
                ", contaCorrente='" + contaCorrente + '\'' +
                ", digitoContaCorrente='" + digitoContaCorrente + '\'' +
                ", flagContaPoupanca=" + flagContaPoupanca +
                ", variacaoContaPoupanca='" + variacaoContaPoupanca + '\'' +
                ", nomeContato='" + nomeContato + '\'' +
                ", cargoContato='" + cargoContato + '\'' +
                ", CPFCNPJFContato='" + CPFCNPJFContato + '\'' +
                ", telefoneFixoContato=" + telefoneFixoContato +
                ", telefoneCelularContato=" + telefoneCelularContato +
                ", emailContato='" + emailContato + '\'' +
                ", dataNascimentoContato=" + dataNascimentoContato +
                ", RGContato='" + RGContato + '\'' +
                ", orgaoEmissoContato='" + orgaoEmissoContato + '\'' +
                ", PisPasep='" + PisPasep + '\'' +
                '}';
    }
}
