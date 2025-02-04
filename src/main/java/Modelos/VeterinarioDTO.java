package Modelos;

public class VeterinarioDTO {

    private int idVet;
    private String nombreVet;
    private String nifVet;
    private String direccion;
    private String telefono;
    private String email;

    public VeterinarioDTO(int idVet, String nombreVet, String nifVet, String direccion, String telefono, String email) {
        this.idVet = idVet;
        this.nombreVet = nombreVet;
        this.nifVet = nifVet;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public VeterinarioDTO() {
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public String getNombreVet() {
        return nombreVet;
    }

    public void setNombreVet(String nombreVet) {
        this.nombreVet = nombreVet;
    }

    public String getNifVet() {
        return nifVet;
    }

    public void setNifVet(String nifVet) {
        this.nifVet = nifVet;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VeterinarioDTO{");
        sb.append("idVet=").append(idVet);
        sb.append(", nombreVet=").append(nombreVet);
        sb.append(", nifVet=").append(nifVet);
        sb.append(", direccion=").append(direccion);
        sb.append(", telefono=").append(telefono);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}
