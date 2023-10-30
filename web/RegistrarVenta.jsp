<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "Modelo.Empleado"%>
<% 
    HttpSession sesion = request.getSession();
    Empleado emp = (Empleado) sesion.getAttribute("usuario");
    if(emp != null){
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <title>VENTAS</title>
        <style>
            @media print{
                .parte01, .btn, .accion{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-lg-5 parte01">
                <div class="card">
                    <form action="Controlador?menu=NuevaVenta" method="POST">                        
                        <div class="card-body">
                            <!-- DATOS DEL PRODUCTO -->
                            <div class="form-group">
                                <label>Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigocliente" value="${cliente.getDni()}" class="form-control" placeholder="Codigo">
                                    <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">
                                </div>                            
                                <div class="col-sm-6">
                                    <input type="text" name="nombrescliente" value="${cliente.getNom()}" placeholder="Datos Cliente" class="form-control">
                                </div>                                                                                    
                            </div>
                            <!-- DATOS DEL PRODUCTO -->
                            <div class="form-group">
                                <label>Datos Producto</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigoproducto" value="${producto.getId()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">Buscar</button>
                                </div>                            
                                <div class="col-sm-6">
                                    <input type="text" name="nomproducto" value="${producto.getNom()}" placeholder="Datos Producto" class="form-control">
                                </div>                                                                                    
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="precio" value="${producto.getPrecio()}" class="form-control" placeholder="S/.0.00">                                
                                </div>                            
                                <div class="col-sm-3">
                                    <input type="number" value="1" name="cant" placeholder="" class="form-control">
                                </div>                               
                                <div class="col-sm-3">
                                    <input type="text" name="stock" value="${producto.getStock()}" placeholder="Stock" class="form-control">
                                </div>          
                            </div>
                            <!-- BOTON AGREGAR PRODUCTO AL REGISTRO -->
                            <div class="form-group">
                                <div class="col-sm">
                                    <button type="submit" name="accion" value="Agregar" class="btn btn-outline-info">Agregar Producto</button>
                                </div>
                            </div>
                        </div>
                    </form>                    
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex col-sm-5 ml-auto">
                            <label>Nro.Serie: </label>
                            <input type="text" name="NroSerie" value="${nserie}" class="form-control">
                        </div>
                        <br>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>Codigo</th>
                                        <th>Descripcion</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>SubTotal</th>
                                        <th class="accion">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${lista}">
                                        <tr>
                                            <td>${list.getItem()}</td>
                                            <td>${list.getIdProducto()}</td>
                                            <td>${list.getDescripcionPr()}</td>
                                            <td>${list.getPrecio()}</td>
                                            <td>${list.getCantidad()}</td>
                                            <td>${list.getSubtotal()}</td>      
                                            <td class="d-flex">
                                                <a href="#" class="btn btn-warning">Editar</a>
                                                <a href="#" class="btn btn-danger" style="margin-left: 10px">Eliminar</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" onclick="print()" class="btn btn-success">Generar Venta</a>                            
                            <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                        </div>
                        <div class="col-sm-3 ml-auto">
                            <input type="text" name="txtTotal" value="$ ${totalpagar}0" class="form-control">
                        </div>
                    </div>
                </div>                
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    </body>
</html>
<%
    }else{
    request.getRequestDispatcher("index.jsp").forward(request , response);
}
%>