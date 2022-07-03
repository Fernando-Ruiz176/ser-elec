package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import dao.ClienteDAO;
import dao.ClienteDAOImp;
import dao.ElectrodomesticoDAO;
import dao.ElectrodomesticoDAOImp;
import dao.OtDAO;
import dao.OtDAOImp;


public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClienteDAO clienteDAO;
	private ElectrodomesticoDAO electrodomesticosDAO;
	private OtDAO odtDAO;
	
	@Override
	public void init() throws ServletException{
		super.init();
		this.clienteDAO = new ClienteDAOImp();
	}
	
    public ClienteController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String vistaJSP = "";
		switch(accion) {
		case "eliminar":
			try {
				int clienteId = Integer.parseInt( request.getParameter("id") );
				clienteDAO.eliminarCliente(clienteId);
				response.sendRedirect("/serv-rep/ClienteController?accion=listar");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				response.sendError(500);
			} catch (NamingException ne) {
				ne.printStackTrace();
				response.sendError(500);
			}				
			break;
			
		case "editar":
			try {
				int clienteId = Integer.parseInt( request.getParameter("id") );
				Cliente cliente = clienteDAO.findClienteById(clienteId);
				request.setAttribute("cliente", cliente);
				vistaJSP = "/WEB-INF/jsp/vista/cliente/cliente-form.jsp";
				request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response)
				;
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				response.sendError(500);
			} catch (NamingException ne) {
				ne.printStackTrace();
				response.sendError(500);
			}
			break;
		
		case "listar":	
			List<Cliente> clientes	= null;
			try {
				clientes 			= clienteDAO.findAllClientes();
			} catch ( Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("clientes", clientes);			
			request.getRequestDispatcher("/WEB-INF/jsp/vista/cliente-listado.jsp").forward(request, response);
			break;
			
		case "form":
			vistaJSP = "/WEB-INF/jsp/vista/cliente/cliente-form.jsp";
			request
				.getRequestDispatcher(vistaJSP)
				.forward(request, response)
			;
			break;
		default:
			response.sendError(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "agregarCliente":
			String nombre 		= request.getParameter("nombre");
			String telefono 	= request.getParameter("telefono");
			String direccion	= request.getParameter("direccion");
			
			Cliente nuevoCliente = new Cliente(nombre,telefono,direccion);
			
			try {
				clienteDAO.createCliente(nuevoCliente);
				nuevoCliente = clienteDAO.findLastCreatedCliente();
				request.setAttribute("cliente", nuevoCliente);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/ot-form.jsp").forward(request, response);				
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
			break;		
		default:
			response.sendError(500);
		}
		
	}

}
