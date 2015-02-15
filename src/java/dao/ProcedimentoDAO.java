/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Procedimento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class ProcedimentoDAO {

    /**
     * Método que pesquisa os procedimentos no banco
     *
     * @return resp String
     */
    public ArrayList<Procedimento> listarProcedimentos(int idFuncao) {
        ArrayList<Procedimento> arrayProcedimentos = new ArrayList<Procedimento>();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM procedimentos WHERE id_funcao = " + idFuncao + " ORDER BY descricao_procedimento";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Procedimento procedimento = new Procedimento();

                procedimento.setId(rs.getInt("id_procedimento"));
                procedimento.setDescricao(rs.getString("descricao_procedimento"));
                procedimento.setTempo(rs.getString("tempo_procedimento"));
                procedimento.setIdFuncao(rs.getInt("id_funcao"));

                arrayProcedimentos.add(procedimento);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(listarProcedimentos) " + e.toString());
        }
        return arrayProcedimentos;
    }

}
