<%@page import="ws.WebServiceSalaoBeleza"%>
<%@page import="classes.Horario"%>
<%@page import="java.util.ArrayList"%>
<%
    Object result = request.getParameter("result");

    if (result == null) {
        result = "";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>Teste de cadastro</div>
        <form action="testeSalaoBeleza.jsp" method="post">
            nome:
            <input type="text" name="nome" id="nome" /><br>
            telefone:
            <input type="text" name="telefone" id="telefone" /><br>
            email:
            <input type="text" name="email" id="email" /><br>
            <input type="submit" value="Ok"/><br>
            <label id="lblResultado" name="lblResultado"><%= result%></label>

        </form> 

        <div>Teste de reserva</div>
        <form action="testeSalaoBeleza.jsp" method="post">
            data:
            <input type="text" name="data" id="data" /><br>
            hora:
            <input type="text" name="hora" id="hora" /><br>
            procedimento:
            <input type="text" name="procedimento" id="procedimento" /><br>
            código do cliente:
            <input type="text" name="id_cliente" id="id_cliente" /><br>
            <input type="submit" value="Ok"/><br>
            <label id="lblResultado" name="lblResultado"><%= result%></label>

        </form> 

        <table border="1">

            <%
                ArrayList<Horario> lista = new ArrayList<Horario>();
                WebServiceSalaoBeleza service = new WebServiceSalaoBeleza();
                lista = service.listaDeHorariosDisponiveis("14/12/2014", 2, 2);
                //lista = new ReservaDAO().buscarMesasReservadas("", "");
                for (int i = 0; i < lista.size(); i++) {
            %>

            <tr>
                <td><%= lista.get(i).getId()%></td>
                <td><%= lista.get(i).getHora()%></td>
            </tr>

            <%                }
            %>
        </table>

    </body>
</html>