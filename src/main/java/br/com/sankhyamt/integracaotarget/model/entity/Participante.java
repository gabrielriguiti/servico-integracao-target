package br.com.sankhyamt.integracaotarget.model.entity;

/**
 * Classe que representa o Participante.
 *
 * @since v1.0
 */
public class Participante {

    private Integer idParticipante = 0;
    private Integer idDmTipoPessoa = 0;
    private String nome = "";
    private String razaoSocial = "";
    private String CPFCNPJ = "";
    private String endereco = "";
    private String bairro = "";
    private String cep = "";
    private Integer municipioCodigoIbge = 0;
    private String RNTRC = "";
    private Boolean ativo = true;
    private String email = "";
    private String telefone = "";
    private String telefoneCelular = "";

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Integer getIdDmTipoPessoa() {
        return idDmTipoPessoa;
    }

    public void setIdDmTipoPessoa(Integer idDmTipoPessoa) {
        this.idDmTipoPessoa = idDmTipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCPFCNPJ() {
        return CPFCNPJ;
    }

    public void setCPFCNPJ(String CPFCNPJ) {
        this.CPFCNPJ = CPFCNPJ;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getMunicipioCodigoIbge() {
        return municipioCodigoIbge;
    }

    public void setMunicipioCodigoIbge(Integer municipioCodigoIbge) {
        this.municipioCodigoIbge = municipioCodigoIbge;
    }

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    @Override
    public String toString() {
        return "Participante{" +
                "idParticipante=" + idParticipante +
                ", idDmTipoPessoa=" + idDmTipoPessoa +
                ", nome='" + nome + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", CPFCNPJ='" + CPFCNPJ + '\'' +
                ", endereco='" + endereco + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", municipioCodigoIbge=" + municipioCodigoIbge +
                ", RNTRC='" + RNTRC + '\'' +
                ", ativo=" + ativo +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", telefoneCelular='" + telefoneCelular + '\'' +
                '}';
    }
}
