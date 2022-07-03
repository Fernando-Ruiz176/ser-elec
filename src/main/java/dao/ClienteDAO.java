package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Cliente;

public interface ClienteDAO {
	
	public List<Cliente> findAllClientes()throws SQLException, NamingException;
	public Cliente findClienteById(int clienteId)throws SQLException, NamingException;
	public void createCliente(Cliente cliente)throws SQLException, NamingException;
	public void editCliente(Cliente cliente)throws SQLException, NamingException;
	public void eliminarCliente(int clienteId)throws SQLException, NamingException;
	public Cliente findLastCreatedCliente()throws SQLException, NamingException;
	
	

}
