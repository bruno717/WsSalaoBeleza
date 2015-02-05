<%@page import="ws.WebServiceSalaoBeleza"%>
<%@page import="classes.Cliente"%>
<%@page import="classes.Reserva"%>
<%
    Cliente cliente = new Cliente();
    cliente.setNome(request.getParameter("nome"));
    cliente.setTelefone(request.getParameter("telefone"));
    cliente.setEmail(request.getParameter("email"));
    cliente.setId(1);

    /*Reserva reserva = new Reserva();
     reserva.setData(request.getParameter("data"));
     reserva.setHora(request.getParameter("hora"));
     reserva.setProcedimento(request.getParameter("procedimento"));
     reserva.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));*/
    WebServiceSalaoBeleza salaoBeleza = new WebServiceSalaoBeleza();

    String result = salaoBeleza.deletaCliente(cliente);

    response.sendRedirect("index.jsp?result=" + result);
%>