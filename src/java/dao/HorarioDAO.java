package dao;

import classes.Horario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import util.Util;

/**
 * @author bruno.oliveira
 */
public class HorarioDAO {

    /**
     * Método que busca os horarios reservados e manda esses horarios já
     * reservados para o método buscarHorariosDisponiveis que retorna um array
     * de horarios disponíveis.
     *
     * @param data
     * @param funcionario
     * @param cliente
     * @return ArrayList<>
     */
    public ArrayList<Horario> buscarHorariosReservados(String data, int funcionario, int cliente) {
        ArrayList<Horario> lista = new ArrayList<Horario>();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM reservas WHERE data_reserva = '" + Util.formataDataBanco(data) + "'"
                    + " AND id_funcionario = " + funcionario + " AND id_cliente = " + cliente;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String query = "SELECT * FROM horarios WHERE id_horario <> " + 0;
            while (rs.next()) {
                query += " AND id_horario <> ";
                query += rs.getInt("id_horario");
            }

            rs.close();
            stmt.close();
            con.close();

            lista = buscarHorariosDisponiveis(query);
        } catch (Exception e) {
            System.out.println("ERRO:(buscarHorariosReservados) " + e.toString());
        }

        return lista;
    }

    /**
     * Método que busca os horarios disponíveis e retorna para o método
     * buscarHorariosReservados um array de horarios disponíveis.
     *
     * @param sql
     * @return
     */
    private ArrayList<Horario> buscarHorariosDisponiveis(String sql) {
        ArrayList<Horario> lista = new ArrayList<Horario>();
        try {
            Connection con = Conecta.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Horario horario = new Horario();

                horario.setId(rs.getInt("id_horario"));
                horario.setHora(rs.getString("hora_horario"));
                lista.add(horario);
            }
            rs.close();
            stmt.close();
            con.close();
            return lista;
        } catch (Exception e) {
            System.out.println("ERRO:(buscarHorariosDisponiveis) " + e.toString());
            return null;
        }

    }
}
