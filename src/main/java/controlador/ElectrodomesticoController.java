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
import java.time.LocalDate;
import java.util.List;

import javax.naming.NamingException;

import dao.ClienteDAO;
import dao.ClienteDAOImp;
import dao.ElectrodomesticoDAO;
import dao.ElectrodomesticoDAOImp;
import dao.OtDAO;
import dao.OtDAOImp;


public class ElectrodomesticoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ElectrodomesticoDAO electrodomesticoDAO;
	private ClienteDAO clienteDAO;
	
	@Override
	public void init() throws ServletException{
		super.init();
		this.clienteDAO = new ClienteDAOImp();
		this.electrodomesticoDAO = new ElectrodomesticoDAOImp(this.clienteDAO);
	}
       
    public ElectrodomesticoController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String vistaJSP = "";
		switch (accion) {
		case "listarpro":
			List<Electrodomestico>  electrodomesticos 	= null;
			try {
				electrodomesticos 	= electrodomesticoDAO.findAllElectrodomesticos();
			} catch(Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("electrodomesticos", electrodomesticos);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/electrodomestico-listado.jsp").forward(request, response);
			break;
		
		
		default:
			response.sendError(500);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "agregarElectrodomestico":
			// trae el id del cliente guardado para crear el objeto cliente, utilizando el metodo buscar
			int clienteId = Integer.parseInt(request.getParameter("id"));
			
			// busca el cliente registrado
			Cliente cliente = null;
			try {
				cliente = clienteDAO.findClienteById(clienteId);
			} catch (SQLException | NamingException e1) {
				e1.printStackTrace();
				response.sendError(500);
			}
			
			// trae los datos del producto
			String nombreProducto 	= request.getParameter("nombreProducto");
			String fallaProducto 	= request.getParameter("fallaProducto");
	
			// crea el producto
			Electrodomestico electrodomestico = new Electrodomestico(nombreProducto, fallaProducto, cliente);
	
			
			try {
				// añade el producto a la base de datos
				electrodomesticoDAO.createElectrodomestico(electrodomestico);
				
				// inmediatamente despues trae el ultimo electrodomestico añadido a la base de datos, reemplaza la variable del objeto antes creado y lo utiliza para crear una odt mas adelante
				electrodomestico = electrodomesticoDAO.findLastCreatedElectrodomestico();
				
				// toma valores de fecha para orden de trabajo
				LocalDate fecha = LocalDate.now();
				
				// crea una orden de trabajo con el electrodomestico en cuestion, estado fijo y la fecha de creacion
				String estado = "Pendiente";
				OrdenDeTrabajo ot = new OrdenDeTrabajo(estado, fecha, fecha, electrodomestico);
				
				// simula un "refresh" de la pagina utilizando los mismos datos que se recogieron antes
				request.setAttribute("cliente", cliente);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/ot-form.jsp").forward(request, response);		
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
			break;
			
		case "finalizar":
			clienteId = Integer.parseInt(request.getParameter("id"));
			
			try {
				Electrodomestico artefacto = electrodomesticoDAO.findElectrodomesticoByClienteId(clienteId);
				if(artefacto == null) {
					//elimina el ultimo cliente creado si no hay electrodomesticos asociados
					Cliente clienteAEliminar = clienteDAO.findLastCreatedCliente();
					clienteDAO.eliminarCliente(clienteAEliminar.getId());
					
					// redirecciona junto a una alerta de que se ha cancelado la orden
					request.setAttribute("success", 0);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}

			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			
			// redirecciona junto a una alerta de que se ha creado la orden de trabajo
			request.setAttribute("success", 1);
			request.getRequestDispatcher("index.jsp").forward(request, response);				
			break;
			
		default:
			response.sendError(500);
			
		}
	}

}
