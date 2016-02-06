package DataBase;

/**
 * Created by barri on 3/2/16.
 */
public class Usuario {

    private String user;
    private int puntuacion;


    public Usuario(String user, int puntuacion) {
        this.user = user;
        this.puntuacion = puntuacion;
    }

    public Usuario() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
