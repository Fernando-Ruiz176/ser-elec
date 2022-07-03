package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Electrodomestico;
import modelo.OrdenDeTrabajo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import dao.ClienteDAOImp;
import dao.ElectrodomesticoDAO;
import dao.ElectrodomesticoDAOImp;
import dao.OtDAO;
import dao.OtDAOImp;


public class OtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private OtDAO otDAO;
	private ElectrodomesticoDAO electrodomesticoDAO;
	
	@Override
	public void init() throws ServletException{
		super.init();
		this.electrodomesticoDAO 	= new ElectrodomesticoDAOImp(null);
		this.otDAO					= new OtDAOImp(this.electrodomesticoDAO);
	}
	
    public OtController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch (accion) {
		case "listar":
			List<OrdenDeTrabajo> ot = null;
			try {
				ot = otDAO.findAllOrdenesDeTrabajo();				
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("ot", ot);	
			request.getRequestDispatcher("/WEB-INF/jsp/vista/ot-listado.jsp").forward(request, response);
			break;
			
		case "verOT":
			// toma el id de la ot a editar
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				// la identifica a traves de un query y la asigna a un objeto odt
				OrdenDeTrabajo otEdit = otDAO.findOrdenDeTrabajoById(id);
				
				// envia el objeto odt a la pagina de editar orden, luego redirecciona hacia la misma
				request.setAttribute("odt", otEdit);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/odt-solo-lectura.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			break;
			
		case "editar":
			// toma el id de la ot a editar
			int otId = Integer.parseInt(request.getParameter("id"));
			try {
				// la identifica a traves de un query y la asigna a un objeto odt
				OrdenDeTrabajo odtEdit = otDAO.findOrdenDeTrabajoById(otId);
				
				// envia el objeto odt a la pagina de editar orden, luego redirecciona hacia la misma
				request.setAttribute("odt", odtEdit);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/formulario-editar-orden.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			break;
		
		
		default:
			response.sendError(500);
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String nombreProducto;
		String fallaProducto;
		switch(accion) {
		case "finalizarEdit":
			// toma los id de los objetos asociados a la orden de trabajo
			int idOt 				= Integer.parseInt(request.getParameter("otId"));
			int clienteId 			= Integer.parseInt(request.getParameter("clienteId"));
			int productoId			= Integer.parseInt(request.getParameter("electrodomesticoId"));
			
			// toma el estado de la orden
			String estadoOrden 		= request.getParameter("estadoOrden");
			
			// toma los datos de los objetos asociados
				// electrodomestico
	
			nombreProducto 			= request.getParameter("nombreProducto");
			fallaProducto 			= request.getParameter("fallaProducto");
			
				// cliente
			String nombreCliente 	= request.getParameter("nombreCliente");
			String telefonoCliente 	= request.getParameter("telefonoCliente");
			String direccionCliente = request.getParameter("direccionCliente");
			
			// con todos los datos recabados, intenta editar el registro correspondiente de cada tabla
			try {
				Electrodomestico electrodomestico = electrodomesticoDAO.findElectrodomesticoById(productoId);
				Cliente cliente = electrodomestico.getCliente_id();
				
				// setea los nuevos datos en el objeto traido
				electrodomestico.setNombre(nombreProducto);
				electrodomestico.setFalla(fallaProducto);
				
				cliente.setNombre(nombreCliente);
				cliente.setTelefono(telefonoCliente);
				cliente.setDireccion(direccionCliente);
				
				// envia a editar los objetos ahora con los datos actualizados
				electrodomesticoDAO.editElectrodomestico(electrodomestico);
				ClienteDAOImp clienteDAO = null;
				clienteDAO.editCliente(cliente);
				
				// envia el objeto odt con los unicos datos relevantes, pues la fecha de actualizacion se hara en el mismo metodo de editar, y lo demas no se deberia cambiar en ningun caso.
				otDAO.editOrdenDeTrabajo(new OrdenDeTrabajo(idOt,estadoOrden));
				request.setAttribute("success", 2);
				request.getRequestDispatcher("index.jsp").forward(request, response);	
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			
		default:
			response.sendError(500);
			
		}
		
		
	}

}
