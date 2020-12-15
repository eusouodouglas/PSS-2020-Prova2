package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Imagem;

/**
 *
 * @author Douglas
 */
public class ImagemDAO  {


    private static ImagemDAO INSTANCE;
    private final ConexaoSQLiteFunc conexao;
    

    public ImagemDAO() throws Exception {
        conexao = ConexaoSQLiteFunc.getInstance();
       
    }

    

    public static ImagemDAO getImagemDAOInstance() throws Exception {

        if (INSTANCE == null) {
            INSTANCE = new ImagemDAO();
        }
        return INSTANCE;

    }

   

    public ArrayList<Imagem> getTodos() {
        String sqlSelect = "SELECT * FROM imagem";
        ArrayList<Imagem> imagem = new ArrayList<>();

        try {
            ResultSet rs = conexao.getStatment().executeQuery(sqlSelect);

            while (rs.next()) {

                int idUsuario = rs.getInt("idImagem");
                String nome = rs.getString("nome");

                Imagem i = new Imagem(idUsuario, nome);
                imagem.add(i);
               
            }

            return imagem;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    

}
