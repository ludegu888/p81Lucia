
package Modelos;

import java.sql.SQLException;
import java.util.List;

public interface IVeterinario {
    
    List<VeterinarioDTO> getAll() throws SQLException;
    
    VeterinarioDTO findByPk(int id) throws SQLException;
    
    int insertVet(VeterinarioDTO vet) throws SQLException;
    
    int insertVet(List<VeterinarioDTO> listaVet) throws SQLException;
    
    int deleteVet(VeterinarioDTO vet) throws SQLException;
    
    int deleteVet() throws SQLException;
    
    int updateVet(int idVet, VeterinarioDTO nuevoDatosVet) throws SQLException;
}
