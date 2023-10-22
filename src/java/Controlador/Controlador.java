package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto pr = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    int idc;
    int idp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            if (menu.equals("Principal")) {
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if (menu.equals("Empleado")) {
                switch (accion) {
                    case "Listar":
                        List lista = edao.listar();
                        request.setAttribute("empleados", lista);
                        break;
                    case "Agregar":
                        String dni = request.getParameter("txtDni");
                        String nom = request.getParameter("txtNombres");
                        String tel = request.getParameter("txtTel");
                        String est = request.getParameter("txtEstado");
                        String user = request.getParameter("txtUsuario");
                        em.setDni(dni);
                        em.setNom(nom);
                        em.setTel(tel);
                        em.setEstado(est);
                        em.setUser(user);
                        edao.agregar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        ide = Integer.parseInt(request.getParameter("id"));
                        Empleado e = edao.listarId(ide);
                        request.setAttribute("empleado", e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1 = request.getParameter("txtDni");
                        String nom1 = request.getParameter("txtNombres");
                        String tel1 = request.getParameter("txtTel");
                        String est1 = request.getParameter("txtEstado");
                        String user1 = request.getParameter("txtUsuario");
                        em.setDni(dni1);
                        em.setNom(nom1);
                        em.setTel(tel1);
                        em.setEstado(est1);
                        em.setUser(user1);
                        em.setId(ide);
                        edao.actualizar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Eliminar":
                        ide = Integer.parseInt(request.getParameter("id"));
                        edao.eliminar(ide);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }
            if (menu.equals("Cliente")) {
                switch (accion) {
                    case "Listar":
                        List lista = cdao.listar();
                        request.setAttribute("clientes", lista);
                        break;
                    case "Agregar":
                        String dni = request.getParameter("txtDni");
                        String nom = request.getParameter("txtNombres");
                        String dir = request.getParameter("txtDireccion");
                        String est = request.getParameter("txtEstado");
                        cl.setDni(dni);
                        cl.setNom(nom);
                        cl.setDireccion(dir);
                        cl.setEstado(est);
                        cdao.agregar(cl);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idc = Integer.parseInt(request.getParameter("id"));
                        Cliente c = cdao.listarId(idc);
                        request.setAttribute("cliente", c);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1 = request.getParameter("txtDni");
                        String nom1 = request.getParameter("txtNombres");
                        String dir1 = request.getParameter("txtDireccion");
                        String est1 = request.getParameter("txtEstado");
                        cl.setDni(dni1);
                        cl.setNom(nom1);
                        cl.setDireccion(dir1);
                        cl.setEstado(est1);
                        cl.setId(idc);
                        cdao.actualizar(cl);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Eliminar":
                        idc = Integer.parseInt(request.getParameter("id"));
                        cdao.eliminar(idc);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Cliente.jsp").forward(request, response);
            }
            if (menu.equals("Producto")) {
                switch (accion) {
                    case "Listar":
                        List lista = pdao.listar();
                        request.setAttribute("productos", lista);
                        break;
                    case "Agregar":
                        String nom = request.getParameter("txtNombres");
                        double precio = Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock = Integer.parseInt(request.getParameter("txtStock"));
                        String est = request.getParameter("txtEstado");
                        pr.setNom(nom);
                        pr.setPrecio(precio);
                        pr.setStock(stock);
                        pr.setEstado(est);
                        pdao.agregar(pr);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;

                    case "Editar":
                        idp = Integer.parseInt(request.getParameter("id"));
                        Producto p = pdao.listarId(idp);
                        request.setAttribute("producto", p);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nom1 = request.getParameter("txtNombres");
                        double precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                        String est1 = request.getParameter("txtEstado");
                        pr.setNom(nom1);
                        pr.setPrecio(precio1);
                        pr.setStock(stock1);
                        pr.setEstado(est1);
                        pr.setId(idp);
                        pdao.actualizar(pr);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Eliminar":
                        idp = Integer.parseInt(request.getParameter("id"));
                        pdao.eliminar(idp);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
            if (menu.equals("NuevaVenta")) {
                switch (accion) {
                    case "BuscarCliente":
                        String dni = request.getParameter("codigocliente");
                        cl.setDni(dni);
                        cl = cdao.buscar(dni);
                        request.setAttribute("cl", cl);
                        break;
                    default:
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
