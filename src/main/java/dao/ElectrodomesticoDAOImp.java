package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Cliente;
import modelo.Electrodomestico;

public class ElectrodomesticoDAOImp implements ElectrodomesticoDAO{
	
private ClienteDAO clientesDAO;
	
	public ElectrodomesticoDAOImp(ClienteDAO clientesDAO) {
		this.clientesDAO = clientesDAO;
	}
	
	
	@Override
	public List<Electrodomestico> findAllElectrodomesticos() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM electrodomestico");
			List<Electrodomestico> electrodomesticos = new ArrayList<>();
			while(rs.next()) {
				int id 				= rs.getInt("electrodomesticoId");
				String nombre 		= rs.getString("Nombre");
				String falla 		= rs.getString("Falla");
				int idCliente 		= rs.getInt("clienteId");
				Cliente cliente 	= clientesDAO.findClienteById(idCliente);
				
				Electrodomestico electrodomestico= new Electrodomestico(id, nombre, falla, cliente);
				electrodomesticos.add(electrodomestico);
			}
			return electrodomesticos;
		}
	}

	@Override
	public Electrodomestico findElectrodomesticoById(int electrodomesticoId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Electrodomestico WHERE electrodomesticoId = ?");
			) {
			ps.setInt(1, electrodomesticoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 				= rs.getInt("electrodomesticoId");
				String nombre 		= rs.getString("Nombre");
				String falla 		= rs.getString("Falla");
				int clienteId 		= rs.getInt("cliente");
				Cliente cliente 	= clientesDAO.findClienteById(clienteId);
				
				return new Electrodomestico(id,	nombre,	falla,	cliente);
			}
			
		}
		return null;
	}

	@Override
	public void createElectrodomestico(Electrodomestico electrodomestico) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO electrodomestico(nombre, falla, clienteId) VALUES (?,?,?)");

			) {
			ps.setString(1, electrodomestico.getNombre());
			ps.setString(2, electrodomestico.getFalla());
			ps.setInt(3, electrodomestico.getCliente_id().getId());
			ps.executeUpdate();
			
		}
		
	}

	@Override
	public void editElectrodomestico(Electrodomestico electrodomestico) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("UPDATE electrodomestico SET nombre = ?, falla = ? WHERE electrodomesticoId = ?");
			) {

				ps.setString(1, electrodomestico.getNombre());
				ps.setString(2, electrodomestico.getFalla());
				ps.setInt(3, electrodomestico.getId());
				ps.executeUpdate();
			} 
		
	}

	@Override
	public void deleteElectrodomestico(int electrodomesticoId) throws SQLException, NamingException {
		
	}

	@Override
	public Electrodomestico findLastCreatedElectrodomestico() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM electrodomestico ORDER BY electrodomesticoId DESC LIMIT 1");
			if(rs.next()) {
				int id 				= rs.getInt("electrodomesticoId");
				String nombre 		= rs.getString("Nombre");
				String falla 		= rs.getString("Falla");
				int clienteId 		= rs.getInt("clienteId");
				Cliente cliente 	= clientesDAO.findClienteById(clienteId);
				return new Electrodomestico(id, nombre, falla, cliente);
			}
			
		}
		return null;
	}


	@Override
	public Electrodomestico findElectrodomesticoByClienteId(int clienteId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Electrodomestico WHERE clienteId = ?");
			) {
			ps.setInt(1, clienteId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 				= rs.getInt("electrodomesticoId");
				String nombre 		= rs.getString("Nombre");
				String falla 		= rs.getString("Falla");
				int clienteId1 		= rs.getInt("clienteId");
				Cliente cliente 	= clientesDAO.findClienteById(clienteId1);
				
				return new Electrodomestico(id, nombre, falla, cliente);
			}
			
		}
		return null;	}
	
}
