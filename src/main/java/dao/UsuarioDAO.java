package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import observer.Observador;
import observer.Subject;

/**
 *
 * @author Douglas
 */
public class UsuarioDAO implements Subject {

    private ArrayList<Observador> observers = new ArrayList<Observador>();

    private static UsuarioDAO INSTANCE;
    private final ConexaoSQLiteFunc conexao;

    public UsuarioDAO() throws Exception {
        conexao = ConexaoSQLiteFunc.getInstance();

    }

    @Override
    public void registerObserver(Observador o) {
        observers.add(o);

    }

    public ArrayList<Observador> getObservers() {
        return observers;
    }

    @Override
    public void notifyObservers() {

        System.out.println(observers.size());

        for (Observador o : this.observers) {
            o.update();
        }
    }

    public static UsuarioDAO getUsuarioDAOInstance() throws Exception {

        if (INSTANCE == null) {
            INSTANCE = new UsuarioDAO();
        }
        return INSTANCE;

    }

    public void inserir(Usuario usuario) throws SQLException {

        String sqlInsert = "INSERT INTO usuario("
                + "idUsuario,"
                + "nome,"
                + "senha,"
                + "admin"
                + ") VALUES(?,?,?,?)"
                + ";";

        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement(sqlInsert);

            pstmt.setInt(1, usuario.getIdUsuario());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setInt(4, usuario.getAdmin());

            pstmt.executeUpdate();
            notifyObservers();

        } catch (SQLException e) {
            throw new SQLException("Falha ao cadastrar o usuario...");
        }

    }

    public ArrayList<Usuario> getTodos() {
        String sqlSelect = "SELECT * FROM usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            ResultSet rs = conexao.getStatment().executeQuery(sqlSelect);

            while (rs.next()) {

                int idUsuario = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                int admin = rs.getInt("admin");

                Usuario f = new Usuario(idUsuario, nome, senha, admin);
                usuarios.add(f);

            }

            return usuarios;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int total() {

        String sqlSelect = "SELECT * FROM usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        int contador = 0;
        try {
            ResultSet rs = conexao.getStatment().executeQuery(sqlSelect);

            while (rs.next()) {

                int idUsuario = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                int admin = rs.getInt("admin");

                Usuario f = new Usuario(idUsuario, nome, senha, admin);
                usuarios.add(f);

                contador += 1;
            }

            return contador;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public boolean login(Usuario usuario) {

        String query = "SELECT * FROM usuario WHERE nome=? AND senha=?";
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement(query);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean ExisteAdminCadastrado() {
        String query = "SELECT * FROM usuario WHERE admin>0";
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean EAdm(String nome) {

        String query = "SELECT * FROM usuario WHERE nome=? AND admin>0";
        try {
            PreparedStatement pstmt = conexao.getConexao().prepareStatement(query);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void deletar(int codigo) {

        String sqlDelete = "Delete from usuario where idUsuario = " + codigo;

        try {
            ResultSet rs = conexao.getStatment().executeQuery(sqlDelete);

            while (rs.next()) {
                rs.getString(sqlDelete);

            }

        } catch (SQLException e) {
            System.out.println("TESTE " + e.getMessage());

        }
        notifyObservers();
    }

}
