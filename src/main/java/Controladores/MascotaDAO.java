package Controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Modelos.MascotaDTO;


public class MascotaDAO implements Modelos.IMascota {

    private Connection con = null;

    public MascotaDAO() {
        con = Conexion.Conexion.getInstance();
    }

    @Override
    public List<MascotaDTO> listaMasc() throws SQLException {
        List<MascotaDTO> lista = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("select * from mascotas");

            while (res.next()) {
                MascotaDTO masc = new MascotaDTO();

                masc.setIdMasc(res.getInt("idMascota"));
                masc.setIdVet((Integer) res.getObject("idVeterinario"));
                masc.setNombreMasc(res.getString("nombre"));
                masc.setNumChip(res.getInt("numChip"));
                masc.setFechaNacim(res.getDate("fechaNacim").toLocalDate());
                masc.setPeso(res.getDouble("peso"));
                masc.setTipo(res.getString("tipo"));

                lista.add(masc);
            }
        }
        return lista;
    }

    @Override
    public MascotaDTO buscarPorId(int idMascota) throws SQLException {

        ResultSet res = null;
        MascotaDTO masc = new MascotaDTO();

        String sql = "select * from mascotas where idMascota=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, idMascota);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {

                masc.setIdMasc(res.getInt("idMascota"));
                masc.setNombreMasc(res.getString("nombre"));
                masc.setNumChip(res.getInt("numChip"));
                masc.setPeso(res.getDouble("peso"));
                masc.setFechaNacim(res.getDate("fechaNacim").toLocalDate());
                masc.setTipo(res.getString("tipo"));
                masc.setIdVet((Integer) res.getObject("idVeterinario"));
                return masc;
            }

            return null;
        }
    }

    @Override
    public int insertMasc(MascotaDTO masc) throws SQLException {
        int numFilas = 0;
        String sql = "insert into mascotas(numChip,nombre,peso,fechaNacim,tipo,idVeterinario) values (?,?,?,?,?,?)";

        if (buscarPorId(masc.getIdMasc()) != null) {

            return numFilas;
        } else {
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                prest.setObject(6, masc.getIdVet());
                prest.setString(2, masc.getNombreMasc());
                prest.setInt(1, masc.getNumChip());
                prest.setDate(4, Date.valueOf(masc.getFechaNacim()));
                prest.setDouble(3, masc.getPeso());
                prest.setString(5, masc.getTipo());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertMasc(List<MascotaDTO> listaMasc) throws SQLException {
        int filas = 0;
        for (MascotaDTO masc : listaMasc) {
            filas += insertMasc(masc);
        }
        return filas;
    }

    @Override
    public int deleteMasc(int idMascota) throws SQLException {
        String sql = "delete from mascotas where idMascota=?";
        int nfilas = 0;

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            prest.setInt(1, idMascota);
            nfilas = prest.executeUpdate();
        }
        return nfilas;
    }

    @Override
    public int deleteMasc(MascotaDTO masc) throws SQLException {
        int numFilas = 0;

        String sql = "delete from mascotas where idMascota = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            prest.setInt(1, masc.getIdMasc());
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateMasc(int idMascota, MascotaDTO nuevosDatosMasc) throws SQLException {
        int numFilas = 0;
        String sql = "update mascotas set numChip=?, nombre=?, peso=?, fechaNacim=?, tipo=?, idVeterinario=? where idMascota=?";

        if (buscarPorId(idMascota) == null) {
            System.out.println("No se encontró la mascota con id " + idMascota);
            return numFilas;
        } else {
            System.out.println("Entra a la modificacion");
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                prest.setString(2, nuevosDatosMasc.getNombreMasc());
                prest.setInt(1, nuevosDatosMasc.getNumChip());
                prest.setDate(4, Date.valueOf(nuevosDatosMasc.getFechaNacim()));
                prest.setDouble(3, nuevosDatosMasc.getPeso());
                prest.setString(5, nuevosDatosMasc.getTipo());
                prest.setObject(6, nuevosDatosMasc.getIdVet());
                prest.setInt(7, idMascota);

                numFilas = prest.executeUpdate();
                System.out.println("Número de filas modificadas: " + numFilas);
            }
            return numFilas;
        }
    }

    @Override
    public List<MascotaDTO> obtenerMascotasPorVeterinario(Integer idVet) throws SQLException {
        List<MascotaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE idVeterinario = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setObject(1, idVet);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MascotaDTO masc = new MascotaDTO();
                masc.setIdMasc(rs.getInt("idMascota"));
                masc.setIdVet((Integer) rs.getObject("idVeterinario"));
                masc.setNombreMasc(rs.getString("nombre"));
                masc.setNumChip(rs.getInt("numChip"));
                masc.setFechaNacim(rs.getDate("fechaNacim").toLocalDate());
                masc.setPeso(rs.getDouble("peso"));
                masc.setTipo(rs.getString("tipo"));

                lista.add(masc);
            }
        }
        return lista;
    }
}
