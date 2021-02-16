package model;

public class Voto {
    Usuario usuario;
    int idFilme;
    int idDiretor;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(int idDiretor) {
        this.idDiretor = idDiretor;
    }
}
