package dao;

import classes.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bruno.oliveira
 */
public class ClienteDAO {

    /**
     * Médoto que grava cliente no banco
     *
     * @param cliente
     * @return resp String
     */
    public String gravarCliente(Cliente cliente) {
        String resp;
        String sql;
        try {
            Connection con = Conecta.getConexao();
            sql = "INSERT INTO clientes(nome_cliente, telefone_cliente, email_cliente, data_nascimento_cliente, usuario_cliente, senha_cliente) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());   
            ps.setString(4, cliente.getDataDeNascimento());
            ps.setString(5, cliente.getUsuario());
            ps.setString(6, cliente.getSenha());
            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(gravarCliente) " + e.toString();
        }
        return resp;
    }

    /**
     * Médoto que exclui cliente no banco
     *
     * @param cliente
     * @return resp String
     */
    public String excluirCliente(Cliente cliente) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "DELETE FROM clientes WHERE nome_cliente=? AND telefone_cliente=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.execute();

            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "ERRO:(excluirCliente) " + e.toString();
        }
        return resp;
    }

    /**
     * Médoto que valida o cliente no banco antes de chamar o médodo de excluir
     *
     * @param cliente
     * @return resp String
     */
    public String validaExclusaoCliente(Cliente cliente) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM clientes WHERE nome_cliente='"
                    + cliente.getNome() + "' AND telefone_cliente='"
                    + cliente.getTelefone() + "'";

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
            return "ERRO:(validaExclusaoCliente) " + e.toString();
        }
        return resp;
    }

    /**
     * Médoto que altera cliente no banco
     *
     * @param cliente
     * @return resp String
     */
    public String alterarCliente(Cliente cliente) {
        String resp;
        try {
            Connection con = Conecta.getConexao();
            String sql = "UPDATE clientes SET nome_cliente=?, telefone_cliente=?, email_cliente=?, usuario_cliente=?, senha_cliente=? WHERE id_cliente=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getUsuario());
            ps.setString(5, cliente.getSenha());
            ps.setInt(6, cliente.getId());
            ps.execute();

            resp = "OK";
            ps.close();
            con.close();
        } catch (Exception e) {
            resp = "ERRO:(alterarCliente) " + e.toString();
        }
        return resp;
    }

    /**
     * Método que pesquisa os clientes no banco
     *
     * @return resp String
     */
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> arrayCliente = new ArrayList<Cliente>();
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM clientes ORDER BY nome_cliente";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setTelefone(rs.getString("telefone_cliente"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setUsuario(rs.getString("usuario_cliente"));
                cliente.setSenha(rs.getString("senha_cliente"));

                arrayCliente.add(cliente);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO:(listarCliente) " + e.toString());
        }
        return arrayCliente;
    }
}
