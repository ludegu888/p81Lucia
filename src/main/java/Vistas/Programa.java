package Vistas;

import javax.swing.JOptionPane;
import Controladores.MascotaDAO;
import Controladores.VeterinarioDAO;
import Modelos.MascotaDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import Modelos.VeterinarioDTO;
import java.util.List;

public class Programa {

    private static MascotaDAO masc = new MascotaDAO();
    private static VeterinarioDAO vet = new VeterinarioDAO();

    public static void main(String[] args) throws SQLException {

        int opcion = 0;

        do {
            opcion = menu(opcion);
            switch (opcion) {
                case 1 -> {
                    JOptionPane.showMessageDialog(null, masc.listaMasc());
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(null, vet.listaVet());
                }
                case 3 -> {
                    agregarMasc();
                }
                case 4 -> {
                    agregarVet();
                }
                case 5 -> {
                    borrarMasc();
                }
                case 6 -> {
                    borrarVet();
                }
                case 7 -> {
                    actualizarMascota();
                }
                case 8 -> {
                    actualizarVeterinario();
                }
                case 9 -> {
                    buscarPorIdMasc();
                }
                case 10 -> {
                    buscarPorIdVet();
                }
                case 11 -> {
                    obtenerMascotasPorVeterinario(masc);
                }
                default ->
                    JOptionPane.showMessageDialog(null, "Introduce la opcion correcta");
            }
        } while (opcion != 0);

    }

    public static int menu(int opcion) {
        String menu = """
                  	1.Lista Mascotas
                  	2.Lista Veterinarios
                  	3.Añadir Mascota
                  	4.Añadir Veterinario
                  	5.Borrar Mascota
                  	6.Borrar Veterinario
                  	7.Modificar Mascota
                  	8.Modificar Veterinario
                  	9.Buscar por IdMascota
                  	10.Buscar por IdVeterianrio
                  	11.Obtener las mascotas asociadas a un veterinario
                  	0.salir
                  	""";
        do {
            // Solicita una opción al usuario
            String opcionString = JOptionPane.showInputDialog(menu);

            try {
                // Convierte la opción a un número entero
                opcion = Integer.parseInt(opcionString);
                return opcion; // Devuelve la opción válida
            } catch (NumberFormatException nfe) {
                System.out.println("Introduce una de las opciones");
            }
            // El bucle se repite hasta obtener una opción válida
        } while (true);
    }

    public static void agregarMasc() {
        try {
            try {
                String nombre = JOptionPane.showInputDialog("Ingresa el nombre de la mascota: ");
                Integer numChip = Integer.valueOf(JOptionPane.showInputDialog("Ingresa el numero de chip de la mascota: "));
                LocalDate fechaNacim = LocalDate.parse(JOptionPane.showInputDialog("Ingresa la fecha de nacimiento de la mascota: "));
                Double peso = Double.valueOf(JOptionPane.showInputDialog("Ingresa el peso de la mascota: "));
                String tipo = JOptionPane.showInputDialog("Ingresa el tipo de mascota que es: ");
                Integer idVet = null;

                MascotaDTO nuevaMasc = new MascotaDTO();
                nuevaMasc.setNombreMasc(nombre);
                nuevaMasc.setNumChip(numChip);
                nuevaMasc.setFechaNacim(fechaNacim);
                nuevaMasc.setPeso(peso);
                nuevaMasc.setTipo(tipo);
                nuevaMasc.setIdVet(idVet);

                int mascInsertada = masc.insertMasc(nuevaMasc);
                if (mascInsertada > 0) {
                    JOptionPane.showMessageDialog(null, "Mascota agregada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo agregar la mascota.");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar la mascota");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Has dejado un campo vacio");
        }
    }

    public static void agregarVet() {
        try {
            
            int idVet = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del veterinario: "));
            String nombre = JOptionPane.showInputDialog("Ingresa el nombre del veterinario: ");
            String nif = JOptionPane.showInputDialog("Ingresa el nif del veterinario: ");
            String direccion = JOptionPane.showInputDialog("Ingresa la direccion del veterinario: ");
            String telefono = JOptionPane.showInputDialog("Ingresa el telefono del veterinario: ");
            String email = JOptionPane.showInputDialog("Ingresa el email del veterinario: ");

            VeterinarioDTO nuevoVet = new VeterinarioDTO();
           
            nuevoVet.setIdVet(idVet);
            nuevoVet.setNombreVet(nombre);
            nuevoVet.setNifVet(nif);
            nuevoVet.setDireccion(direccion);
            nuevoVet.setTelefono(telefono);
            nuevoVet.setEmail(email);

            int vetInsertado = vet.insertVet(nuevoVet);
            if (vetInsertado > 0) {
                JOptionPane.showMessageDialog(null, "Veterinario agregada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el veterinario");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el veterinario");
        }
    }

    public static void borrarMasc() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el idMascota a eliminar:"));
            int filasEliminadas = masc.deleteMasc(idMascota);
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null, "Mascota eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la mascota.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la mascota");

        }
    }

    private static void borrarVet() {
        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el idVeterinario:"));
            int filasEliminadas = vet.deleteVet(idVeterinario);
            
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null, "Veterinario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el veterinario.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el veterinario: ");
        }
    }

    private static void actualizarMascota() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Pon el idMascota a actualizar:"));
            if (idMascota <= 0) {
                return;
            }

            MascotaDTO mascota = masc.buscarPorId(idMascota);
            if (mascota != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Pon nuevo nombre para la mascota:");
                if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                    mascota.setNombreMasc(nuevoNombre);
                }

                String nuevoPesoStr = JOptionPane.showInputDialog("Pon nuevo peso para la mascota:");
                if (nuevoPesoStr != null && !nuevoPesoStr.isEmpty()) {
                    double nuevoPeso = Double.parseDouble(nuevoPesoStr);
                    if (nuevoPeso > 0) {
                        mascota.setPeso(nuevoPeso);
                    }
                }

                String nuevaFechaStr = JOptionPane.showInputDialog("Pon nueva fecha de nacimiento (yyyy-mm-dd):");
                if (nuevaFechaStr != null && !nuevaFechaStr.isEmpty()) {
                    LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr);
                    mascota.setFechaNacim(nuevaFecha);
                }

                String nuevoChip = JOptionPane.showInputDialog("Pon nuevo número de chip para la mascota:");
                if (nuevoChip != null && !nuevoChip.isEmpty()) {
                    int conversionChip = Integer.parseInt(nuevoChip);
                    mascota.setNumChip(conversionChip);
                }

                String nuevoTipo = JOptionPane.showInputDialog("Pon nuevo tipo de mascota:");
                if (nuevoTipo != null && !nuevoTipo.isEmpty()) {

                    mascota.setTipo(nuevoTipo);
                }

                String nuevoVet = (JOptionPane.showInputDialog("Pon nuevo Veterinario para la mascota:"));

                if (nuevoVet != null && !nuevoVet.isEmpty()) {
                    Integer conversionVet = Integer.parseInt(nuevoVet);
                    mascota.setIdVet(conversionVet);
                }

                masc.updateMasc(idMascota, mascota);
                JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La mascota no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la mascota");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El formato del peso o id de la mascota no es válido");
        }
    }

    private static void actualizarVeterinario() {
        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Pon el id del veterinario a actualizar"));
            if (idVeterinario <= 0) {
                return;
            }
            VeterinarioDTO veterinario = vet.buscarPorId(idVeterinario);
            if (veterinario != null) {
                // Actualizar nombre
                String nuevoNombre = JOptionPane.showInputDialog("Pon el nuevo nombre:");
                if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                    veterinario.setNombreVet(nuevoNombre);
                }

                String nuevaDireccion = JOptionPane.showInputDialog("Pon la nueva dirección:");
                if (nuevaDireccion != null && !nuevaDireccion.isEmpty()) {
                    veterinario.setDireccion(nuevaDireccion);
                }

                String nuevoTelefono = JOptionPane.showInputDialog("Pon el nuevo teléfono:");
                if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
                    veterinario.setTelefono(nuevoTelefono);
                }

                String nuevoEmail = JOptionPane.showInputDialog("Pon el nuevo email:");
                if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
                    veterinario.setEmail(nuevoEmail);
                }

                String nuevoNif = JOptionPane.showInputDialog("Pon el nuevo nif:");
                if (nuevoNif != null && !nuevoNif.isEmpty()) {
                    veterinario.setNifVet(nuevoNif);
                }

                vet.updateVet(idVeterinario, veterinario);
                JOptionPane.showMessageDialog(null, "Veterinario actualizado.");
            } else {
                JOptionPane.showMessageDialog(null, "El veterinario no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el veterinario");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id del veterinario debe ser un número entero");
        }
    }

    private static void buscarPorIdMasc() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la mascota que desea buscar: "));

            MascotaDTO mascota = masc.buscarPorId(idMascota);

            if (mascota != null) {
                String detallesMascota = "IdMasc: " + mascota.getIdMasc() + "\n"
                        + "Nombre: " + mascota.getNombreMasc() + "\n"
                        + "Número de Chip: " + mascota.getNumChip() + "\n"
                        + "Peso: " + mascota.getPeso() + " kg\n"
                        + "Fecha de Nacimiento: " + mascota.getFechaNacim() + "\n"
                        + "Tipo: " + mascota.getTipo() + "\n"
                        + "IdVet: " + mascota.getIdVet();

                JOptionPane.showMessageDialog(null, detallesMascota);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese id");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id ingresado no es válido, debe ser un número entero");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }

    private static void buscarPorIdVet() {
        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la veterinario que desea buscar: "));

            VeterinarioDTO veterinario = vet.buscarPorId(idVeterinario);

            if (veterinario != null) {
                String detalleVet = "IdVet: " + veterinario.getIdVet() + "\n"
                        + "Nombre: " + veterinario.getNombreVet() + "\n"
                        + "Nif: " + veterinario.getNifVet() + "\n"
                        + "Telefono: " + veterinario.getTelefono() + "\n"
                        + "Direccion " + veterinario.getDireccion() + "\n"
                        + "Email: " + veterinario.getEmail() + "\n";

                JOptionPane.showMessageDialog(null, detalleVet);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese id");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id ingresado no es válido, debe ser un número entero");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }

    private static void obtenerMascotasPorVeterinario(MascotaDAO mascotaDAO) {
        try {

            int idVet = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del veterinario para ver sus mascotas:"));

            List<MascotaDTO> mascotas = mascotaDAO.obtenerMascotasPorVeterinario(idVet);

            if (mascotas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron mascotas para este veterinario.");
            } else {
                StringBuilder mensaje = new StringBuilder("Mascotas del veterinario con id " + idVet + ":\n");
                for (MascotaDTO mascota : mascotas) {
                    mensaje.append("IdMasc: ").append(mascota.getIdMasc()).append("\n")
                            .append("Nombre: ").append(mascota.getNombreMasc()).append("\n")
                            .append("Número de Chip: ").append(mascota.getNumChip()).append("\n")
                            .append("Peso: ").append(mascota.getPeso()).append(" kg\n")
                            .append("Fecha de Nacimiento: ").append(mascota.getFechaNacim()).append("\n")
                            .append("Tipo: ").append(mascota.getTipo()).append("\n")
                            .append("IdVet: ").append(mascota.getIdVet()).append("\n\n");
                }

                JOptionPane.showMessageDialog(null, mensaje.toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id ingresado no es válido, debe ser un número entero");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }
}
