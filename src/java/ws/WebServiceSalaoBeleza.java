/*
 * @author Bruno
 */
package ws;

import classes.Cliente;
import classes.Funcionario;
import classes.Horario;
import classes.Procedimento;
import classes.Reserva;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.HorarioDAO;
import dao.ProcedimentoDAO;
import dao.ReservaDAO;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "WebServiceSalacaoBeleza")
public class WebServiceSalaoBeleza {

    /**
     * Operação para chamar o método gravarCliente
     *
     * @param cliente
     * @return String
     */
    @WebMethod(operationName = "insereCliente")
    public String insereCliente(@WebParam(name = "cliente") Cliente cliente) {
        String resp;
        ClienteDAO dao = new ClienteDAO();
        resp = dao.gravarCliente(cliente);

        if (resp.equals("OK")) {
            return "Cliente gravado com sucesso!";
        } else {
            return resp;
        }
    }

    /**
     * Operação para chamar o método excluirCliente
     *
     * @param cliente
     * @return String
     */
    @WebMethod(operationName = "deletaCliente")
    public String deletaCliente(@WebParam(name = "cliente") Cliente cliente) {

        String resp;
        ClienteDAO dao = new ClienteDAO();
        resp = dao.validaExclusaoCliente(cliente);

        if (resp.equals("OK")) {
            resp = dao.excluirCliente(cliente);
            if (resp.equals("OK")) {
                return "Cliente excluído!";
            } else {
                return resp;
            }
        } else {
            return "Cliente não existe!";
        }
    }

    /**
     * Operação para chamar o método alterarReserva
     *
     * @param cliente
     * @return String
     */
    @WebMethod(operationName = "atualizaCliente")
    public String atualizaCliente(@WebParam(name = "cliente") Cliente cliente) {

        String resp;
        ClienteDAO dao = new ClienteDAO();
        resp = dao.alterarCliente(cliente);

        if (resp.equals("OK")) {
            return "Cliente alterado com sucesso!";
        } else {
            return resp;
        }
    }

    /**
     * Operação para chamar o método gravarReserva
     *
     * @param reserva
     * @return String
     */
    @WebMethod(operationName = "insereReserva")
    public String insereReserva(@WebParam(name = "reserva") Reserva reserva) {
        String resp;
        ReservaDAO dao = new ReservaDAO();
        resp = dao.gravarReserva(reserva);

        if (resp.equals("OK")) {
            return "Reserva feita com sucesso!";
        } else {
            return resp;
        }
    }

    /**
     * Operação para chamar o método excluirReserva
     *
     * @param reserva
     * @return String
     */
    @WebMethod(operationName = "deletaReserva")
    public String deletaReserva(@WebParam(name = "reserva") Reserva reserva) {

        String resp;
        ReservaDAO dao = new ReservaDAO();
        resp = dao.validaExclusaoReserva(reserva);

        if (resp.equals("OK")) {
            resp = dao.excluirReserva(reserva);
            if (resp.equals("OK")) {
                return "Reserva cancelada!";
            } else {
                return resp;
            }
        } else {
            return "Você não tem reserva nesta data e horário!";
        }
    }

    /**
     * Método que busca lista de horários disponíveis
     *
     * @param data
     * @param funcionario
     * @return ArrayList<>
     */
    @WebMethod(operationName = "listaDeHorariosDisponiveis")
    public ArrayList<Horario> listaDeHorariosDisponiveis(@WebParam(name = "data") 
            String data, @WebParam(name = "funcionario") int funcionario) {

        HorarioDAO dao = new HorarioDAO();
        ArrayList<Horario> listaHorario;
        listaHorario = dao.buscarHorariosReservados(data, funcionario);

        return listaHorario;
    }

    /**
     * Operação de Web service
     * @return 
     */
    @WebMethod(operationName = "listaDeFuncionarios")
    public ArrayList<Funcionario> listaDeFuncionarios() {
        
        ArrayList<Funcionario> listaFuncionarios;
        FuncionarioDAO dao = new FuncionarioDAO();
        listaFuncionarios = dao.listarFuncionario();
        
        return listaFuncionarios;
    }

    /**
     * Operação de Web service
     * @param idFuncao
     * @return 
     */
    @WebMethod(operationName = "listaDeProcedimentos")
    public ArrayList<Procedimento> listaDeProcedimentos(@WebParam(name = "idFuncao") int idFuncao) {
        
        ArrayList<Procedimento> listaProcedimentos;
        ProcedimentoDAO dao = new ProcedimentoDAO();
        listaProcedimentos = dao.listarProcedimentos(idFuncao);
        
        return listaProcedimentos;
    }


}
