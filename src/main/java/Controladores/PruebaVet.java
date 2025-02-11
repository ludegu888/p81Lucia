package Controladores;

import Controladores.VeterinarioDAO;
import Modelos.VeterinarioDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PruebaVet {
	public static void main(String[] args) {

    	VeterinarioDAO daoVet = new VeterinarioDAO();
    	List<VeterinarioDTO> listaVet = new ArrayList<>();
    	listaVet.add(new VeterinarioDTO(15,"Paco","424212a","C/ Amapola", "689458121", "paco@gmail.com"));
    	listaVet.add(new VeterinarioDTO(24,"Carla","216241F","C/ Tulipan", "678234586", "carla@gmail.com"));
   	 
    	try {
       	 
        	System.out.println("Nº personas insertadas " + daoVet.insertVet(listaVet));
        	System.out.println("-----------------------------------------");
        	System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
        	List<VeterinarioDTO> nuevaLista = daoVet.getAll();
        	System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
        	nuevaLista.forEach(System.out::println);
        	System.out.println("-----------------------------------------");
        	System.out.println("Persona con primary key 1: ");
        	System.out.println(daoVet.findByPk(15));
        	System.out.println("-----------------------------------------");
        	System.out.println("Se va a borrar la persona con pk 3");
        	System.out.println("Nº personas borradas " +
                	daoVet.deleteVet(new VeterinarioDTO(15,"Paco","424212a","C/ Amapola", "689458121", "paco@gmail.com")));
        	System.out.println("-----------------------------------------");
        	nuevaLista = daoVet.getAll();
        	System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar una persona -------------");
        	nuevaLista.forEach(System.out::println);
        	System.out.println("-----------------------------------------");
        	System.out.println("Modificación de la persona con pk 5");
        	System.out.println("Nº Personas modificadas: " +
        	daoVet.updateVet(24,new VeterinarioDTO(7,"Penelope","232312a","C/ Pino","323122","p@gmail.com")));
        	System.out.println("-----------------------------------------");
        	nuevaLista = daoVet.getAll();
        	System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una persona -------------");
        	nuevaLista.forEach(System.out::println);
    	} catch (SQLException sqle) {
        	System.out.println("No se ha podido realizar la operación:");
        	sqle.printStackTrace();
        	System.out.println(sqle.getMessage());
    	}
    	System.out.println("-------- Lista original --------------------");
    	listaVet.forEach(System.out::println);
	}
}


