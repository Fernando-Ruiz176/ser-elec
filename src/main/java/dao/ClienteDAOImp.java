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

public class ClienteDAOImp implements ClienteDAO{
	
	@Override
	public List<Cliente> findAllClientes() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM cliente");
			List<Cliente> clientes = new ArrayList<Cliente>();
			while(rs.next()) {
				int id = rs.getInt("Cliente");
				String nombre = rs.getString("Nombre");
				String telefono = rs.getString("Telefono");
				String direccion = rs.getString("Direccion");
				Cliente cliente = new Cliente(id, nombre, telefono, direccion);
				clientes.add(cliente);
			}
			return clientes;
		}
	}

	@Override
	public Cliente findClienteById(int clienteId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente WHERE clienteId = ?");
			) {
			ps.setInt(1, clienteId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("clienteId");
				String nombre = rs.getString("Nombre");
				String telefono = rs.getString("Telefono");
				String direccion = rs.getString("Direccion");
				
				return new Cliente(id, nombre, telefono, direccion);
			}
			
		}
		return null;
	}

	@Override
	public void createCliente(Cliente cliente) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO cliente(nombre, telefono, direccion) VALUES (?,?,?)");

			) {
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getTelefono());
			ps.setString(3, cliente.getDireccion());
			ps.executeUpdate();
		}
		
		
	}

	@Override
	public void editCliente(Cliente cliente) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("UPDATE cliente SET nombre = ?, telefono = ?, direccion = ? WHERE clienteId = ?");
			) {

				ps.setString(1, cliente.getNombre());
				ps.setString(2, cliente.getTelefono());
				ps.setString(3, cliente.getDireccion());
				ps.setInt(4, cliente.getId());
				ps.executeUpdate();
			} 
		
	}

	@Override
	public void eliminarCliente(int clienteId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM cliente WHERE clienteId = ?");
			) {
				ps.setInt(1, clienteId);
				ps.executeUpdate();
			} 
	}

	@Override
	public Cliente findLastCreatedCliente() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM cliente ORDER BY clienteId DESC LIMIT 1");
			if(rs.next()) {
				int id 				= rs.getInt("clienteId");
				String nombre 		= rs.getString("nombre");
				String telefono 	= rs.getString("telefono");
				String direccion 	= rs.getString("direccion");
				return new Cliente(id, nombre, telefono, direccion);
			}
			
		}
		return null;
	}
	
}
