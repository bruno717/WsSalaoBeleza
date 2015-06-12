package classes;

/**
 *
 * @author bruno.oliveira
 */
public class Usuario {

    private int Id;
    private String EnderecoEmail;
    private String NumeroTelefone;
    private String Nome;
    private String NumeroCpf;
    private String NumeroRg;
    private String Login;
    private String Senha;
    private int IdPerfil;

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the EnderecoEmail
     */
    public String getEnderecoEmail() {
        return EnderecoEmail;
    }

    /**
     * @param EnderecoEmail the EnderecoEmail to set
     */
    public void setEnderecoEmail(String EnderecoEmail) {
        this.EnderecoEmail = EnderecoEmail;
    }

    /**
     * @return the NumeroTelefone
     */
    public String getNumeroTelefone() {
        return NumeroTelefone;
    }

    /**
     * @param NumeroTelefone the NumeroTelefone to set
     */
    public void setNumeroTelefone(String NumeroTelefone) {
        this.NumeroTelefone = NumeroTelefone;
    }

    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the NumeroCpf
     */
    public String getNumeroCpf() {
        return NumeroCpf;
    }

    /**
     * @param NumeroCpf the NumeroCpf to set
     */
    public void setNumeroCpf(String NumeroCpf) {
        this.NumeroCpf = NumeroCpf;
    }

    /**
     * @return the NumeroRg
     */
    public String getNumeroRg() {
        return NumeroRg;
    }

    /**
     * @param NumeroRg the NumeroRg to set
     */
    public void setNumeroRg(String NumeroRg) {
        this.NumeroRg = NumeroRg;
    }

    /**
     * @return the Login
     */
    public String getLogin() {
        return Login;
    }

    /**
     * @param Login the Login to set
     */
    public void setLogin(String Login) {
        this.Login = Login;
    }

    /**
     * @return the Senha
     */
    public String getSenha() {
        return Senha;
    }

    /**
     * @param Senha the Senha to set
     */
    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    /**
     * @return the IdPerfil
     */
    public int getIdPerfil() {
        return IdPerfil;
    }

    /**
     * @param IdPerfil the IdPerfil to set
     */
    public void setIdPerfil(int IdPerfil) {
        this.IdPerfil = IdPerfil;
    }
}
