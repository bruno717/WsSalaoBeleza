/*
 * @author Bruno
 */
package ws;

import classes.AgendaCliente;
import classes.Pessoa;
import classes.Funcao;
import classes.Funcionario;
import classes.Horario;
import classes.Procedimento;
import classes.Reserva;
import dao.PessoaDAO;
import dao.FuncaoDAO;
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
     * Operação para chamar o método gravarPessoa
     *
     * @param pessoa
     * @return String
     */
    @WebMethod(operationName = "inserePessoa")
    public String inserePessoa(@WebParam(name = "pessoa") Pessoa pessoa) {
        String resp;
        PessoaDAO dao = new PessoaDAO();
        resp = dao.gravarPessoa(pessoa);

        if (resp.equals("OK")) {
            return "Pessoa gravado com sucesso!";
        } else {
            return resp;
        }
    }

    /**
     * Operação para chamar o método excluirPessoa
     *
     * @param pessoa
     * @return String
     */
    @WebMethod(operationName = "deletaPessoa")
    public String deletaPessoa(@WebParam(name = "pessoa") Pessoa pessoa) {

        String resp;
        PessoaDAO dao = new PessoaDAO();
        resp = dao.validaExclusaoPessoa(pessoa);

        if (resp.equals("OK")) {
            resp = dao.excluirPessoa(pessoa);
            if (resp.equals("OK")) {
                return "Pessoa excluída!";
            } else {
                return resp;
            }
        } else {
            return "Pessoa não existe!";
        }
    }

    /**
     * Operação para chamar o método alterarReserva
     *
     * @param pessoa
     * @return String
     */
    @WebMethod(operationName = "atualizaPessoa")
    public String atualizaCliente(@WebParam(name = "pessoa") Pessoa pessoa) {

        String resp;
        PessoaDAO dao = new PessoaDAO();
        resp = dao.alterarPessoa(pessoa);

        if (resp.equals("OK")) {
            return "Pessoa alterado com sucesso!";
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
     * @param idReserva
     * @return String
     */
    @WebMethod(operationName = "deletaReserva")
    public String deletaReserva(@WebParam(name = "idReserva") int idReserva) {

        String resp;
        ReservaDAO dao = new ReservaDAO();

        resp = dao.excluirReserva(idReserva);

        return resp;

    }

    /**
     * Método que busca lista de horários disponíveis
     *
     * @param data
     * @param funcionario
     * @param cliente
     * @return ArrayList<>
     */
    @WebMethod(operationName = "listaDeHorariosDisponiveis")
    public ArrayList<Horario> listaDeHorariosDisponiveis(@WebParam(name = "data") String data, @WebParam(name = "funcionario") int funcionario, @WebParam(name = "cliente") int cliente) {

        HorarioDAO dao = new HorarioDAO();
        ArrayList<Horario> listaHorario;
        listaHorario = dao.buscarHorariosReservados(data, funcionario, cliente);

        return listaHorario;
    }

    /**
     * Operação de Web service
     *
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

    /**
     * Operação de Web service
     *
     * @return
     */
    @WebMethod(operationName = "listaDeFuncoes")
    public ArrayList<Funcao> listaDeFuncoes() {

        ArrayList<Funcao> listaFuncoes;
        FuncaoDAO dao = new FuncaoDAO();
        listaFuncoes = dao.listarFuncao();

        return listaFuncoes;
    }

    /**
     * Operação de Web service
     *
     * @param idFuncao
     * @return
     */
    @WebMethod(operationName = "listaDeFuncionarios")
    public ArrayList<Funcionario> listaDeFuncionarios(@WebParam(name = "idFuncao") int idFuncao) {

        ArrayList<Funcionario> listaFuncionarios;
        FuncionarioDAO dao = new FuncionarioDAO();
        listaFuncionarios = dao.listarFuncionario(idFuncao);

        return listaFuncionarios;
    }

    /**
     * Operação de Web service
     *
     * @param idCliente
     * @return
     */
    @WebMethod(operationName = "listaAgendaCliente")
    public ArrayList<AgendaCliente> listaAgendaCliente(@WebParam(name = "idCliente") int idCliente) {

        ArrayList<AgendaCliente> agendaCliente;

        ReservaDAO dao = new ReservaDAO();
        agendaCliente = dao.agendaCliente(idCliente);

        return agendaCliente;
    }

    /**
     * Operação de Web service
     *
     * @param pessoa
     * @return
     */
    @WebMethod(operationName = "loginPessoa")
    public Pessoa loginPessoa(@WebParam(name = "pessoa") Pessoa pessoa) {

        PessoaDAO dao = new PessoaDAO();
        Pessoa p;
        p = dao.pesquisaPessoa(pessoa);

        return p;
    }

}
