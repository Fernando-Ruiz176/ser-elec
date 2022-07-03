package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Electrodomestico;
import modelo.OrdenDeTrabajo;

public class OtDAOImp implements OtDAO{
	
private ElectrodomesticoDAO electrodomesticosDAO;
	
	public OtDAOImp(ElectrodomesticoDAO electrodomesticosDAO) {
		this.electrodomesticosDAO = electrodomesticosDAO;
	}
	
	@Override
	public List<OrdenDeTrabajo> findAllOrdenesDeTrabajo() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			
			String query = "SELECT * FROM ordendetrabajo";				
			ResultSet rs = st.executeQuery(query);
			List<OrdenDeTrabajo> ordenesDeTrabajo = new ArrayList<>();
			while(rs.next()) {
				int id 						 		 = rs.getInt("odtId");
				String estado 				 		 = rs.getString("estado");
				LocalDate fechaSolicitud 		 	 = rs.getObject("fechasolicitud", LocalDate.class);
				LocalDate fechaEntrega 	 	 		 = rs.getObject("fechaaEntrega", LocalDate.class);
				int id_electrodomestico 			 = rs.getInt("electrodomesticoId");
				
				Electrodomestico electrodomestico = electrodomesticosDAO.findElectrodomesticoById(id_electrodomestico);

				OrdenDeTrabajo ordenDeTrabajo		 = new OrdenDeTrabajo(id, estado, fechaSolicitud, fechaEntrega, electrodomestico);
				ordenesDeTrabajo.add(ordenDeTrabajo);
			}
			return ordenesDeTrabajo;
		}
	}
	
	@Override
	public OrdenDeTrabajo findOrdenDeTrabajoById(int odtId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ordendetrabajo WHERE id_oSt = ?");
			) {
			int otId = 0;
			ps.setInt(1, otId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 						 		 = rs.getInt("otId");
				String estado 				 		 = rs.getString("estado");
				LocalDate fechaSolicitud 		 	 = rs.getObject("fechasolicitud", LocalDate.class);
				LocalDate fechaEntrega 				 = rs.getObject("fechaaEntrega", LocalDate.class);
				int electrodomesticoId 			     = rs.getInt("electrodomesticoId");
				
				Electrodomestico electrodomestico = electrodomesticosDAO.findElectrodomesticoById(electrodomesticoId);

				LocalDate fechaEntrega1 = null;
				return new OrdenDeTrabajo(id, estado, fechaSolicitud, fechaEntrega1, electrodomestico);
			}
			
		}
		return null;
	}

	@Override
	public void createOrdenDeTrabajo(OrdenDeTrabajo ot) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO ordendetrabajo(estado, fechasolicitud, fechaEntrega, electrodomesticoId) VALUES (?,?,?,?)");

			) {
			ps.setString(1, ot.getEstado());
			ps.setObject(2, ot.getFechaSolicitud());
			ps.setObject(3, ot.getFechaEntrega());
			ps.setInt(4, ot.getElectrodomestico_id().getId());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void editOrdenDeTrabajo(OrdenDeTrabajo ot) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("UPDATE ordendetrabajo SET fechaEntrega = ?, estado = ? WHERE otId = ?");
			) {

				ps.setObject(1, LocalDate.now());
				ps.setString(2, ot.getEstado());
				ps.setInt(3, ot.getId());
				ps.executeUpdate();
			} 
	}

	@Override
	public void deleteOrdenDeTrabajo(int otId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM ordendetrabajo WHERE otId = ?");
			) {
				ps.setInt(1, otId);
				ps.executeUpdate();
			} 
	}


	@Override
	public OrdenDeTrabajo findLastCreatedOrdenDeTrabajo() throws SQLException, NamingException {
		
		return null;
		
	}

	

}
