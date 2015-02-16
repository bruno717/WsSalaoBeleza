package dao;

import classes.Funcao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author bruno.oliveira
 */
public class FuncaoDAO {

    /**
     * Método que grava funcao no banco
     *
     * @param funcao
     * @return resp String
     */
    public String gravarFuncao(Funcao funcao) {
        String resp;
        String sql;
        try {
            Connection con = Conecta.getConexao();
            sql = "INSERT INTO funcao(nome_funcao) VALUES(?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, funcao.getNome());
            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(gravarFuncao) " + e.toString();
        }
        return resp;
    }

    /**
     * Método que pesquisa as funções no banco
     *
     * @return resp String
     */
    public ArrayList<Funcao> listarFuncao() {
        ArrayList<Funcao> arrayFuncao = new ArrayList<Funcao>();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM funcoes ORDER BY nome_funcao";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Funcao funcao = new Funcao();

                funcao.setId(rs.getInt("id_funcao"));
                funcao.setNome(rs.getString("nome_funcao"));

                arrayFuncao.add(funcao);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(listarFuncao) " + e.toString());
        }
        return arrayFuncao;
    }

}
