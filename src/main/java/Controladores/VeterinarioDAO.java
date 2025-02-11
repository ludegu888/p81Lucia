package Controladores;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Modelos.VeterinarioDTO;

public class VeterinarioDAO implements Modelos.IVeterinario {
    
    private Connection con = null;

    public VeterinarioDAO() {
        con = Conexion.getInstance();
    }

    public List<VeterinarioDTO> getAll() throws SQLException {
        List<VeterinarioDTO> listaVet = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("Select * from veterinarios");
            while (res.next()) {
                VeterinarioDTO vet = new VeterinarioDTO();
                vet.setIdVet(res.getInt("idVeterinario"));
                vet.setNombreVet(res.getString("nombreVet"));
                vet.setNifVet(res.getString("nifVet"));
                vet.setDireccion(res.getString("direccion"));
                vet.setTelefono(res.getString("telefono"));
                vet.setEmail(res.getString("email"));
                listaVet.add(vet);
            }
        }
        return listaVet;
    }

    public VeterinarioDTO findByPk(int id) throws SQLException {

        ResultSet res = null;
        VeterinarioDTO vet = new VeterinarioDTO();

        String sql = "select * from veterinarios where idVeterinario = ?";

        try ( PreparedStatement prest = con.prepareStatement(sql)) {
            prest.setInt(1, id);

            res = prest.executeQuery();

            if (res.next()) {
                vet.setIdVet(res.getInt("idVeterinario"));
                vet.setNombreVet(res.getString("nombreVet"));
                vet.setNifVet(res.getString("nifVet"));
                vet.setDireccion(res.getString("direccion"));
                vet.setTelefono(res.getString("telefono"));
                vet.setEmail(res.getString("email"));
                return vet;
            }
        }
        return null;
    }

    public int insertVet(VeterinarioDTO vet) throws SQLException {

        int numFilas = 0;
        String sql = "insert into veterinarios(idVeterinario,nombreVet,nifVet,direccion,telefono,email) values (?,?,?,?,?,?)";

        if (findByPk(vet.getIdVet()) != null) {
            return numFilas;
        } else {
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                prest.setInt(1, vet.getIdVet());
                prest.setString(2, vet.getNombreVet());
                prest.setString(3, vet.getNifVet());
                prest.setString(4, vet.getDireccion());
                prest.setString(5, vet.getTelefono());
                prest.setString(6, vet.getEmail());
                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    public int insertVet(List<VeterinarioDTO> lista) throws SQLException {

        int numFilas = 0;
        String sql = "insert into veterinarios(idVeterinario,nomVet,nifVet,direccion,telefono,email) values (?,?,?,?,?,?)";;
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            for (VeterinarioDTO tmp : lista) {
                numFilas += insertVet(tmp);
            }
            return numFilas;
        }
    }

    public int deleteVet() throws SQLException {

        String sql = "delete from veterinarios where idVeterinario=?";

        int nfilas;

        try (Statement st = con.createStatement()) {
            nfilas = st.executeUpdate(sql);
        }
        return nfilas;
    }

    public int deleteVet(VeterinarioDTO vet) throws SQLException {
        int numFilas;

        String sql = "delete from veterinarios where idVeterinario = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, vet.getIdVet());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateVet(int idVet, VeterinarioDTO nuevosDatosVet) throws SQLException {
        int numFilas = 0;
        String sql = "update veterinarios set nombreVet=?, nifVet=?, direccion=?,telefono=?,email=? where idVeterinario =?";

        if (findByPk(idVet) == null) {

            return numFilas;
        } else {

            try (PreparedStatement prest = con.prepareStatement(sql)) {

                prest.setString(1, nuevosDatosVet.getNombreVet());
                prest.setString(2, nuevosDatosVet.getNifVet());
                prest.setString(3, nuevosDatosVet.getDireccion());
                prest.setString(4, nuevosDatosVet.getTelefono());
                prest.setString(5, nuevosDatosVet.getEmail());
                prest.setInt(6, idVet);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }
}
