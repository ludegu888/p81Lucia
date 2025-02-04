package Modelos;

import java.time.LocalDate;

public class MascotaDTO {

    private int idMasc;
    private int idVet;
    private String nombreMasc;
    private int numChip;
    private LocalDate fechaNacim;
    private double peso;
    private String tipo;

    public MascotaDTO(int idMasc, int idVet, String nombreMasc, int numChip, LocalDate fechaNacim, double peso, String tipo) {
        this.idMasc = idMasc;
        this.idVet = idVet;
        this.nombreMasc = nombreMasc;
        this.numChip = numChip;
        this.fechaNacim = fechaNacim;
        this.peso = peso;
        this.tipo = tipo;
    }

    public MascotaDTO() {
    }

    public int getIdMasc() {
        return idMasc;
    }

    public void setIdMasc(int idMasc) {
        this.idMasc = idMasc;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public String getNombreMasc() {
        return nombreMasc;
    }

    public void setNombreMasc(String nombreMasc) {
        this.nombreMasc = nombreMasc;
    }

    public int getNumChip() {
        return numChip;
    }

    public void setNumChip(int numChip) {
        this.numChip = numChip;
    }

    public LocalDate getFechaNacim() {
        return fechaNacim;
    }

    public void setFechaNacim(LocalDate fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MascotaDTO{");
        sb.append("idMasc=").append(idMasc);
        sb.append(", idVet=").append(idVet);
        sb.append(", nombreMasc=").append(nombreMasc);
        sb.append(", numChip=").append(numChip);
        sb.append(", fechaNacim=").append(fechaNacim);
        sb.append(", peso=").append(peso);
        sb.append(", tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }
}

