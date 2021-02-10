package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa o parceiro motorista da viagem.
 *
 * @author Gabriel Riguiti de Oliveira
 * @since v1.0
 */
public class Motorista {

    private Integer idMotorista = 0;
    private String nome = "";
    private String sobrenome = "";
    private String cpf = "";
    private String nroRG = "";
    private String orgaoEmissoRG = "";
    private String dtNascimento = "";
    private String sexo = "S";
    private String estadoCivil = "";
    private String nomePai = "";
    private String nomeMae = "";
    private String email = "";
    private String telefone = "";
    private String telefoneCelular = "";
    private String nacionalidade = "";
    private String endereco = "";
    private String numero = "";
    private String cep = "";
    private String bairro = "";
    private String cidade = "";
    private String uf = "";
    private String codBanco = "0";
    private String codAgencia = "0";
    private String digitoAgencia = "0";
    private String contaCorrente = "0";
    private String digitoContaCorrente = "0";
    private Boolean flagContaPoupanca = false;
    private String variacaoContaPoupanca = "0";
    private Boolean ativo = true;
    private String codigoIBGEMunicipio = "0";
    private String RNTRC = "0";

    /**
     * @return Retorna o endereço completo do parceiro transportador.
     * @sice v1.0
     */
    public String enderecoCompleto() {

        String enderecoCompleto = "Rua " + this.endereco + ", " + this.numero + ", " + this.bairro;

        return enderecoCompleto;
    }

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNroRG() {
        return nroRG;
    }

    public void setNroRG(String nroRG) {
        this.nroRG = nroRG;
    }

    public String getOrgaoEmissoRG() {
        return orgaoEmissoRG;
    }

    public void setOrgaoEmissoRG(String orgaoEmissoRG) {
        this.orgaoEmissoRG = orgaoEmissoRG;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public String getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCodigoIBGEMunicipio() {
        return codigoIBGEMunicipio;
    }

    public void setCodigoIBGEMunicipio(String codigoIBGEMunicipio) {
        this.codigoIBGEMunicipio = codigoIBGEMunicipio;
    }

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "idMotorista=" + idMotorista +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nroRG='" + nroRG + '\'' +
                ", orgaoEmissoRG='" + orgaoEmissoRG + '\'' +
                ", dtNascimento='" + dtNascimento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", nomePai='" + nomePai + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", telefoneCelular='" + telefoneCelular + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", codBanco='" + codBanco + '\'' +
                ", codAgencia='" + codAgencia + '\'' +
                ", digitoAgencia='" + digitoAgencia + '\'' +
                ", contaCorrente='" + contaCorrente + '\'' +
                ", digitoContaCorrente='" + digitoContaCorrente + '\'' +
                ", flagContaPoupanca=" + flagContaPoupanca +
                ", variacaoContaPoupanca='" + variacaoContaPoupanca + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
