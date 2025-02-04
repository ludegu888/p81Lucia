
package Modelos;

import java.sql.SQLException;
import java.util.List;

public interface IMascota {
    
    List<MascotaDTO> getAll() throws SQLException;
    
    MascotaDTO findByPk(int id) throws SQLException;
    
    int insertVet(MascotaDTO masc) throws SQLException;
    
    int insertVet(List<MascotaDTO> listaMasc) throws SQLException;
    
    int deleteVet(MascotaDTO masc) throws SQLException;
    
    int deleteVet() throws SQLException;
    
    int updateVet(int idMasc, MascotaDTO nuevoDatosMasc) throws SQLException;
    
    List<MascotaDTO> obtenerMascotasPorVeterinario(int idVet);
}
