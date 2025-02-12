package Modelos;

import java.sql.SQLException;
import java.util.List;

public interface IMascota {
    
	List<MascotaDTO> listaMasc() throws SQLException;
    
	MascotaDTO buscarPorId(int id) throws SQLException;
    
	int insertMasc(MascotaDTO masc) throws SQLException;
    
	int insertMasc(List<MascotaDTO> listaMasc) throws SQLException;
    
	int deleteMasc(MascotaDTO masc) throws SQLException;
    
	int deleteMasc(int idMascota) throws SQLException;
    
	int updateMasc(int idMasc, MascotaDTO nuevoDatosMasc) throws SQLException;
    
	List<MascotaDTO> obtenerMascotasPorVeterinario(Integer idVet) throws SQLException;
}


