package dao;

import classes.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bruno.oliveira
 */
public class PessoaDAO {

    /**
     * Médoto que grava pessoa no banco
     *
     * @param pessoa
     * @return resp String
     */
    public String gravarPessoa(Pessoa pessoa) {
        String resp;
        String sql;
        try {
            Connection con = Conecta.getConexao();
            sql = "INSERT INTO pessoas(nome_pessoa, telefone_pessoa, email_pessoa,"
                    + " data_nascimento_pessoa, usuario_pessoa, senha_pessoa, perfil_pessoa) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTelefone());
            ps.setString(3, pessoa.getEmail());
            ps.setString(4, pessoa.getDataDeNascimento());
            ps.setString(5, pessoa.getUsuario());
            ps.setString(6, pessoa.getSenha());
            ps.setInt(7, pessoa.getPerfil());
            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(gravarPessoa) " + e.toString();
        }
        return resp;
    }

    /**
     * Médoto que exclui pessoa no banco
     *
     * @param pessoa
     * @return resp String
     */
    public String excluirPessoa(Pessoa pessoa) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "DELETE FROM pessoas WHERE nome_pessoa=? AND telefone_pessoa=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTelefone());
            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(excluirPessoa) " + e.toString();
        }
        return resp;
    }

    /**
     * Médoto que valida o pessoa no banco antes de chamar o médodo de excluir
     *
     * @param pessoa
     * @return resp String
     */
    public String validaExclusaoPessoa(Pessoa pessoa) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM pessoas WHERE nome_pessoa='"
                    + pessoa.getNome() + "' AND telefone_pessoa='"
                    + pessoa.getTelefone() + "'";

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
            System.out.println("ERRO_validaExclusao: " + e.toString());
            return "ERRO:(validaExclusaoPessoa) " + e.toString();
        }
        return resp;
    }

    /**
     * Médoto que altera cliente no banco
     *
     * @param pessoa
     * @return resp String
     */
    public String alterarPessoa(Pessoa pessoa) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "UPDATE pessoas SET nome_pessoa=?, telefone_pessoa=?,"
                    + " email_pessoa=?, usuario_pessoa=?, senha_pessoa=?,"
                    + " perfil_pessoa=? WHERE id_pessoa=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTelefone());
            ps.setString(3, pessoa.getEmail());
            ps.setString(4, pessoa.getUsuario());
            ps.setString(5, pessoa.getSenha());
            ps.setInt(6, pessoa.getPerfil());
            ps.setInt(7, pessoa.getId());
            ps.execute();

            resp = "OK";
            ps.close();
            con.close();
        } catch (Exception e) {
            resp = "ERRO:(alterarPessoa) " + e.toString();
        }
        return resp;
    }

    /**
     * Método que pesquisa os pessoas no banco
     *
     * @return resp String
     */
    public ArrayList<Pessoa> listarPessoas() {
        ArrayList<Pessoa> arrayPessoa = new ArrayList<Pessoa>();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM pessoas ORDER BY nome_pessoa";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();

                pessoa.setId(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome_pessoa"));
                pessoa.setTelefone(rs.getString("telefone_pessoa"));
                pessoa.setEmail(rs.getString("email_pessoa"));
                pessoa.setUsuario(rs.getString("usuario_pessoa"));
                pessoa.setSenha(rs.getString("senha_pessoa"));
                pessoa.setPerfil(rs.getInt("perfil_pessoa"));

                arrayPessoa.add(pessoa);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(listarPessoa) " + e.toString());
        }
        return arrayPessoa;
    }

    /**
     * Método que pesquisa uma pessoa no banco
     *
     * @param p
     * @return resp Pessoa
     */
    public Pessoa pesquisaPessoa(Pessoa p) {
        Pessoa pessoa = new Pessoa();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM pessoas WHERE usuario_pessoa='" 
                    + p.getUsuario() + "' AND senha_pessoa='" + p.getSenha() + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                pessoa.setId(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome_pessoa"));
                pessoa.setTelefone(rs.getString("telefone_pessoa"));
                pessoa.setEmail(rs.getString("email_pessoa"));
                pessoa.setUsuario(rs.getString("usuario_pessoa"));
                pessoa.setSenha(rs.getString("senha_pessoa"));
                pessoa.setPerfil(rs.getInt("perfil_pessoa"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(pesquisaPessoa) " + e.toString());
        }
        return pessoa;
    }
}
