/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import model.Usuario;
import observer.Observador;
import view.MenuView;

/**
 *
 * @author Douglas
 */
public class MenuPresenter implements Observador {

    private MenuView view = null;
    private static MenuPresenter INSTANCIA = null;

    private UsuarioDAO usuarioDAO;

    private MenuPresenter(Usuario usuario) {

        configTela(usuario);

    }

    public static MenuPresenter getInstance(Usuario usuario) {
        if (INSTANCIA == null) {
            INSTANCIA = new MenuPresenter(usuario);
        }
        return INSTANCIA;
    }

    private void configTela(Usuario usuario) {

        this.view = new MenuView();
        view.setTitle("Menu");

        try {
            this.usuarioDAO = UsuarioDAO.getUsuarioDAOInstance();
        } catch (Exception ex) {
            Logger.getLogger(MenuPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

        usuarioDAO.registerObserver(this);

        this.view.getMnCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnCadastrar();
            }

        });

        this.view.getMnVisualizarImagens().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnVisualizarImagens();
            }

        });

        view.getTxtNomeRodape().setText(String.valueOf(usuario.getNome()));
        String adm;
        System.out.println(usuario.getAdmin());
        if (usuario.getAdmin() < 1) {

            this.view.getMnMenuUsuario().setVisible(false);
            this.view.getTxtTotal().setVisible(false);
            this.view.getLbTotal().setVisible(false);
            adm = "Usuário comum";
        } else {

            adm = "Administrador";
        }
        view.getTxtAdminRodape().setText(String.valueOf(adm));

        this.view.getMnVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mnVisualizar();
            }

        });

        this.view.getTxtTotal().setEditable(false);

        view.setVisible(true);
        this.view.getTelaPrincipal().setVisible(true);
        update();

        view.setVisible(true);
        this.view.getTelaPrincipal().setVisible(true);

        this.view.getMnExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        });
    }

    private void mnVisualizarImagens() {
        try {

            this.view.getTelaPrincipal().add(new ImagemPresenter().getView());

        } catch (Exception ex) {
            Logger.getLogger(MenuPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void mnCadastrar() {

        InclusaoPresenter presenter = new InclusaoPresenter();
        this.view.getTelaPrincipal().add(presenter.getView());

        presenter.inclusao();

    }

    private void mnVisualizar() {

        try {

            this.view.getTelaPrincipal().add(new VisualizacaoUsuarioPresenter().getView());

        } catch (Exception ex) {
            Logger.getLogger(MenuPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JDesktopPane getDesktopPane() {
        return this.view.getTelaPrincipal();
    }

    @Override
    public void update() {

        view.getTxtTotal().setText(String.valueOf(usuarioDAO.total()));

    }

}
