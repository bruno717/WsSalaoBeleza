package dao;

import classes.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bruno.oliveira
 */
public class FuncionarioDAO {

    /**
     * Método que grava funcionário no banco
     *
     * @param funcionario
     * @return resp String
     */
    public String gravarFuncionario(Funcionario funcionario) {
        String resp;
        String sql;
        try {
            Connection con = Conecta.getConexao();
            sql = "INSERT INTO funcionarios(nome_funcionario,telefone_funcionario,"
                    + "celular_funcionario, cidade_funcionario,"
                    + "estado_funcionario, cep_funcionario, email_funcionario,"
                    + "id_funcao) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getTelefone());
            ps.setString(3, funcionario.getCelular());
            ps.setString(4, funcionario.getCidade());
            ps.setString(5, funcionario.getEstado());
            ps.setString(6, funcionario.getCep());
            ps.setString(7, funcionario.getEmail());
            ps.setInt(8, funcionario.getIdFuncao());

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
     * Método que exclui funcionario no banco
     *
     * @param funcionario
     * @return resp String
     */
    public String excluirFuncionario(Funcionario funcionario) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, funcionario.getId());
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
     * Método que pesquisa os funcionarios no banco
     *
     * @param idFuncao
     * @return resp String
     */
    public ArrayList<Funcionario> listarFuncionario(int idFuncao) {
        ArrayList<Funcionario> arrayFuncionario = new ArrayList<Funcionario>();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM funcionarios WHERE id_funcao =" + idFuncao + " ORDER BY nome_funcionario";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome_funcionario"));
                funcionario.setTelefone(rs.getString("telefone_funcionario"));
                funcionario.setCelular(rs.getString("celular_funcionario"));
                funcionario.setCidade(rs.getString("cidade_funcionario"));
                funcionario.setEstado(rs.getString("estado_funcionario"));
                funcionario.setCep(rs.getString("cep_funcionario"));
                funcionario.setEmail(rs.getString("email_funcionario"));
                funcionario.setIdFuncao(rs.getInt("id_funcao"));

                arrayFuncionario.add(funcionario);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(listarFuncionario) " + e.toString());
        }
        return arrayFuncionario;
    }

    /**
     * Método que pesquisa um funcionário no banco
     *
     * @param idFuncionario
     * @return resp String
     */
    public String buscarFuncionario(int idFuncionario) {
        String funcionario = "";
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM funcionarios WHERE id_funcionario =" + idFuncionario;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                funcionario = rs.getString("nome_funcionario");
                
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(buscarFuncionario) " + e.toString());
        }
        return funcionario;
    }
}
