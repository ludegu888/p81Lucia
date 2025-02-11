package Controladores;

import Modelos.MascotaDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaMasc {

    public static void main(String[] args) throws SQLException {

        MascotaDAO daoMasc = new MascotaDAO();
        List<MascotaDTO> listaMasc = new ArrayList<>();
        listaMasc.add(new MascotaDTO(20, 1, "Kira", 24631, LocalDate.of(2020, 02, 10), 15.6, "perro"));
        listaMasc.add(new MascotaDTO(14, 2, "Salem", 54132, LocalDate.of(2018, 12, 20), 5.0, "gato"));

        try {

            System.out.println("Nº mascotas insertadas " + daoMasc.insertMasc(listaMasc));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<MascotaDTO> nuevaLista = daoMasc.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Mascota con primary key 1: ");
            System.out.println(daoMasc.findByPk(14));
            System.out.println("-----------------------------------------");
            System.out.println("Se va a borrar la mascota con pk 3");
            System.out.println("Nº mascotas borradas "
                    + daoMasc.deleteMasc(new MascotaDTO(14, 2, "Salem", 54132, LocalDate.of(2018, 12, 20), 5.0, "gato")));
            System.out.println("-----------------------------------------");
            nuevaLista = daoMasc.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar una mascota -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Modificación de la mascota con idMascota = 20");
            System.out.println("Nº mascotas modificadas: "
                    + daoMasc.updateMasc(20, new MascotaDTO(7, 2, "Riku", 35213, LocalDate.of(2022, 06, 13), 4.6, "gato")));
            System.out.println("-----------------------------------------");
            nuevaLista = daoMasc.getAll();
            System.out.println("---- LIsta obtenerMascotasPorVeterinario con idVet = 1 ----");
            List<MascotaDTO> mascotasVeterinario = daoMasc.obtenerMascotasPorVeterinario(1);
            System.out.println("Mascotas asociadas al veterinario con id 1:");
            mascotasVeterinario.forEach(System.out::println);
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una mascota -------------");
            nuevaLista.forEach(System.out::println);
        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            sqle.printStackTrace();
            System.out.println(sqle.getMessage());
        }
        System.out.println("-------- Lista original --------------------");
        listaMasc.forEach(System.out::println);

        System.out.println("Mascota con primary key 20: ");
        MascotaDTO mascota = daoMasc.findByPk(20);
        if (mascota != null) {
            System.out.println(mascota);
        } else {
            System.out.println("No se encontró la mascota con id 20.");
        }

    }

}
