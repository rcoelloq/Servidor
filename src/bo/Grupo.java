package bo;

public class Grupo {
    private String login;
    private int     idGrupo;
    private String  descripcionGrupo;
    
    /**
     * @return the idGrupo
     */
    public int getIdGrupo() {
        return idGrupo;
    }

    /**
     * @param idGrupo the idGrupo to set
     */
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * @return the descripcionGrupo
     */
    public String getDescripcionGrupo() {
        return descripcionGrupo;
    }

    /**
     * @param descripcionGrupo the descripcionGrupo to set
     */
    public void setDescripcionGrupo(String descripcionGrupo) {
        this.descripcionGrupo = descripcionGrupo;
    }   

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
}