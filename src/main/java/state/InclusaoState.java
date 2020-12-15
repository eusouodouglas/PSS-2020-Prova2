/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import presenter.InclusaoPresenter;
import view.InclusaoView;

/**
 *
 * @author Douglas
 */
public class InclusaoState extends AbstractState {

    private InclusaoView view;

    public InclusaoState(InclusaoPresenter presenter) {

        super(presenter);

        view = presenter.getView();
        configuraView();
        this.view.moveToFront();
    }

    @Override
    public void inclusao() {

        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    salvar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }

        });

    }

    private void salvar() {

        int idUsuario = Integer.parseInt(view.getTxtId().getText());
        String nome = view.getTxtNome().getText();
        String senha = view.getTxtSenha().getText();
        int admin = Integer.parseInt(view.getTxtSalario().getText());

        Usuario usuario = new Usuario(idUsuario, nome, senha, admin);

        try {
            UsuarioDAO.getUsuarioDAOInstance().inserir(usuario);
        } catch (Exception ex) {
            Logger.getLogger(InclusaoState.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(view, "Usuario " + usuario.getNome() + " cadastrado com sucesso!");
        view.dispose();

        presenter.visualizacao(usuario);

    }

    private void configuraView() {

        view.getBtnExcluir().setVisible(false);

        view.getBtnEditar().setVisible(false);

        view.getBtnSalvarEdicao().setVisible(false);

    }

}
