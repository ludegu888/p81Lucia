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
import Modelos.VeterinarioDTO;

public class MascotaDAO implements Modelos.IMascota {

	private Connection con = null;

	public MascotaDAO() {
    	con = Conexion.Conexion.getInstance();
	}

	public List<MascotaDTO> getAll() throws SQLException {
    	List<MascotaDTO> lista = new ArrayList<>();

    	try (Statement st = con.createStatement()) {
        	ResultSet res = st.executeQuery("select * from mascotas");

        	while (res.next()) {
            	MascotaDTO masc = new MascotaDTO();

            	masc.setIdMasc(res.getInt("pk_masc"));
            	masc.setIdVet(res.getInt("pk_vet"));
            	masc.setNombreMasc(res.getString("nombre"));
            	masc.setFechaNacim(res.getDate("fecha nacimiento").toLocalDate());
            	masc.setNumChip(res.getInt("num chip"));
            	masc.setPeso(res.getDouble("peso"));
            	masc.setTipo(res.getString("tipo"));

            	lista.add(masc);
        	}
    	}
    	return lista;
	}

	public MascotaDTO findByPk(int id) throws SQLException {

    	ResultSet res = null;
    	MascotaDTO masc = new MascotaDTO();

    	String sql = "select * from mascotas where pk=?";

    	try (PreparedStatement prest = con.prepareStatement(sql)) {

        	prest.setInt(1, id);

        	res = prest.executeQuery();

        	if (res.next()) {

            	masc.setIdMasc(res.getInt("pk_masc"));
                masc.setNumChip(res.getInt("num chip"));
            	masc.setNombreMasc(res.getString("nombre"));
            	masc.setFechaNacim(res.getDate("fecha nacimiento").toLocalDate());
            	masc.setPeso(res.getDouble("peso"));
            	masc.setTipo(res.getString("tipo"));
                masc.setIdVet(res.getInt("pk _vet"));
            	return masc;
        	}
        	return null;
    	}
	}
    
	public int insertMasc(MascotaDTO masc) throws SQLException {
    	int numFilas = 0;
    	String sql = "insert into mascotas values (?,?,?,?,?,?,?)";

    	if (findByPk(masc.getIdMasc()) != null) {

        	return numFilas;
    	} else {
        	try (PreparedStatement prest = con.prepareStatement(sql)) {

            	prest.setInt(1, masc.getIdMasc());
                prest.setInt(3, masc.getNumChip());
                prest.setString(2, masc.getNombreMasc());
            	prest.setDate(4, Date.valueOf(masc.getFechaNacim()));
            	prest.setDouble(5, masc.getPeso());
            	prest.setString(6, masc.getTipo());
                prest.setInt(7, masc.getIdVet());

            	numFilas = prest.executeUpdate();
        	}
        	return numFilas;
    	}
	}

	public int insertMasc(List<MascotaDTO> listaMasc) throws SQLException {
    	int filas = 0;
    	for (MascotaDTO masc : listaMasc) {
        	filas += insertMasc(masc);
    	}
    	return filas;
	}
    
	public int deleteMasc() throws SQLException {
    	String sql = "delete from mascotas where idMascota=?";

    	int nfilas = 0;

    	try (Statement st = con.createStatement()) {
        	nfilas = st.executeUpdate(sql);
    	}
    	return nfilas;
	}

	public int deleteMasc(MascotaDTO masc) throws SQLException {
    	int numFilas = 0;

    	String sql = "delete from mascotas where pk = ?";

    	try (PreparedStatement prest = con.prepareStatement(sql)) {
        	prest.setInt(1, masc.getIdMasc());
        	numFilas = prest.executeUpdate();
    	}
    	return numFilas;
	}

	public int updateMasc(int idMasc, MascotaDTO nuevosDatosMasc) throws SQLException {
    	int numFilas = 0;
    	String sql = "update mascotas set idMascota=?, numChip=?, nombre=?, peso=?, fechaNacim=?, tipo=?, idVeterinario=?";

    	if (findByPk(idMasc) == null) {
        	return numFilas;
    	} else {

        	try (PreparedStatement prest = con.prepareStatement(sql)) {

            	prest.setInt(1, nuevosDatosMasc.getIdMasc());
            	prest.setInt(2, nuevosDatosMasc.getNumChip());
            	prest.setString(3, nuevosDatosMasc.getNombreMasc());
            	prest.setDate(4, Date.valueOf(nuevosDatosMasc.getFechaNacim()));
            	prest.setDouble(5, nuevosDatosMasc.getPeso());
            	prest.setString(6, nuevosDatosMasc.getTipo());
                prest.setInt(7, nuevosDatosMasc.getIdVet());

            	numFilas = prest.executeUpdate();
        	}
        	return numFilas;
    	}
	}

	public List<MascotaDTO> obtenerMascotasPorVeterinario(int idVet) throws SQLException {
    	List<MascotaDTO> lista = new ArrayList<>();
    	String sql = "SELECT * FROM mascotas WHERE idVeterinario = ?";
    	try (PreparedStatement stmt = con.prepareStatement(sql)) {
        	stmt.setInt(1, idVet);
        	ResultSet rs = stmt.executeQuery();
        	while (rs.next()) {
            	MascotaDTO masc = new MascotaDTO();
            	masc.setIdMasc(rs.getInt("idMasc"));
                masc.setNumChip(rs.getInt("numChip"));
            	masc.setNombreMasc(rs.getString("nombre"));
            	masc.setPeso(rs.getDouble("peso"));
            	masc.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
            	masc.setTipo(rs.getString("tipo"));
                masc.setIdVet(rs.getInt("idVet"));

            	lista.add(masc);
        	}
    	}
    	return lista;
	}
}



