/**
 * @author bruno.oliveira
 */
package dao;

import classes.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import util.Util;

public class ReservaDAO {

    /**
     * Método que grava reserva no banco
     *
     * @param reserva
     * @return resp String
     */
    public String gravarReserva(Reserva reserva) {
        String resp;
        String sql;
        try {
            Connection con = Conecta.getConexao();
            sql = "INSERT INTO reservas(data_reserva, id_horario, proced_reserva,"
                    + " id_cliente, id_funcionario) VALUES(?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setDate(1, Util.formataDataBanco(reserva.getData()));
            ps.setInt(2, reserva.getIdHora());
            ps.setString(3, reserva.getProcedimento());
            ps.setInt(4, reserva.getIdCliente());
            ps.setInt(5, reserva.getIdFuncionario());

            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(gravarReserva) " + e.toString();
        }
        return resp;
    }

    /**
     * Método que exclui reserva no banco
     *
     * @param reserva
     * @return resp String
     */
    public String excluirReserva(Reserva reserva) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "DELETE FROM reservas WHERE data_reserva=? AND id_horario=? AND id_cliente=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Util.formataDataBanco(reserva.getData()));
            ps.setInt(2, reserva.getIdHora());
            ps.setInt(3, reserva.getIdCliente());
            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(excluirReserva) " + e.toString();
        }
        return resp;
    }

    /**
     * Método que valida reserva no banco antes de chamar o médodo de excluir
     *
     * @param reserva
     * @return resp String
     */
    public String validaExclusaoReserva(Reserva reserva) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM reservas WHERE data_reserva='" + Util.formataDataBanco(reserva.getData())
                    + "' AND id_horario='" + reserva.getIdHora()
                    + "' AND id_cliente=" + reserva.getIdCliente();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                resp = "OK";
            } else {
                resp = "vazio";
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(validaExclusaoReserva) " + e.toString());
            return "ERRO:(validaExclusaoReserva) " + e.toString();
        }
        return resp;
    }

    public ArrayList<Reserva> agendaFuncionario(String dataPadraoBrasileiro) {
        ArrayList<Reserva> arrayAgenda = new ArrayList<Reserva>();
        Date dataPadraoBanco = Util.formataDataBanco(dataPadraoBrasileiro);
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM reservas WHERE data_reserva=" + dataPadraoBanco;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Reserva reserva = new Reserva();

                reserva.setId(rs.getInt("id_reserva"));
                reserva.setData(Util.formataDataPadrao(rs.getDate("data_reserva").toString()));
                reserva.setIdHora(rs.getInt("id_horario"));
                reserva.setProcedimento(rs.getString("proced_reserva"));
                reserva.setIdCliente(rs.getInt("id_cliente"));
                reserva.setIdFuncionario(rs.getInt("id_funcionario"));

                arrayAgenda.add(reserva);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO(agendaFuncionario): " + e.toString());
        }
        return arrayAgenda;
    }
}
