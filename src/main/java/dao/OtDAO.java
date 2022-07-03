package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Cliente;
import modelo.OrdenDeTrabajo;

public interface OtDAO {
	
	public List<OrdenDeTrabajo> findAllOrdenesDeTrabajo() throws SQLException, NamingException;
	public OrdenDeTrabajo findOrdenDeTrabajoById(int Id)throws SQLException, NamingException;
	public void createOrdenDeTrabajo(OrdenDeTrabajo ot)throws SQLException, NamingException;
	public void editOrdenDeTrabajo(OrdenDeTrabajo ot)throws SQLException, NamingException;
	public void deleteOrdenDeTrabajo(int otId)throws SQLException, NamingException;
	public OrdenDeTrabajo findLastCreatedOrdenDeTrabajo()throws SQLException, NamingException;

}
