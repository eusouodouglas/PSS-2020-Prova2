/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.LoginView;

/**
 *
 * @author Douglas
 */
public class LoginPresenter {

    private Connection conn;
    private ResultSet rst;
    private PreparedStatement pst;

    private LoginView view = null;
    private static LoginPresenter INSTANCIA = null;

    public static LoginPresenter getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new LoginPresenter();
        }
        return INSTANCIA;
    }

    private LoginPresenter() {
        configTela();
    }

    private void configTela() {
        LoginView a = new LoginView();
        a.setVisible(true);

        a.getBtnLoginInicial().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logar(a);
            }

        });

    }

    private void logar(LoginView a) {

        String uname, pwd;

        boolean status = false;

        uname = a.getTxtUsuario().getText();
        pwd = String.valueOf(a.getTxtSenha().getPassword());

        Usuario usuario = new Usuario(uname, pwd);

        try {
            UsuarioDAO dao = UsuarioDAO.getUsuarioDAOInstance();

            boolean ExisteAdm = (dao.ExisteAdminCadastrado());

            if (ExisteAdm) {
                if (status = dao.login(usuario)) {

                    if (EAdmin(usuario)) {

                        MenuPresenter.getInstance(usuario);
                    } else {

                        usuario.setAdmin(0);
                        MenuPresenter.getInstance(usuario);

                    }

                } else if (a.getTxtUsuario().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Campo de usuário vazio");

                } else if (a.getTxtSenha().getPassword().length < 1) {
                    JOptionPane.showMessageDialog(null, "Campo de senha vazio");

                } else {
                    JOptionPane.showMessageDialog(null, "Cadastro não existe!");

                }
            } else {
                System.out.println("Não há administrador. Tornando o presente usuário em admin");
                Usuario u = new Usuario(10, uname, pwd, 1);
                dao.inserir(usuario);
                MenuPresenter.getInstance(usuario);

            }

        } catch (Exception ex) {
            Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean EAdmin(Usuario usuario) {

        boolean status = false;
        String nome = usuario.getNome();

        UsuarioDAO dao;
        try {
            dao = UsuarioDAO.getUsuarioDAOInstance();

            if (dao.EAdm(nome)) {

                return true;

            }

        } catch (Exception ex) {
            Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
