/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import presenter.InclusaoPresenter;
import view.InclusaoView;

/**
 *
 * @author Douglas
 */
public class VisualizacaoState extends AbstractState {

    private InclusaoView view;

    public VisualizacaoState(InclusaoPresenter presenter) {

        super(presenter);
        view = presenter.getView();

    }

    @Override
    public void visualizacao(Usuario usuario) {

        configuraView(usuario);
        view.setTitle("Detalhes do usuario");
        this.view.moveToFront();

        view.getTxtId().setText("" + usuario.getIdUsuario());
        view.getTxtNome().setText(usuario.getNome());
        view.getTxtSenha().setText("" + usuario.getSenha());
        view.getTxtAdmin().setText("" + usuario.getAdmin());

        view.getBtnEditar().setVisible(false);
        view.getBtnSalvar().setVisible(false);
        view.getBtnSalvarEdicao().setVisible(false);
        view.getTxtId().setEnabled(true);
        view.getTxtNome().setEnabled(false);
        view.getTxtSenha().setEnabled(false);
        view.getTxtSalario().setEnabled(false);

    }

    private void configuraView(Usuario usuario) {

        view.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        });

        view.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    view.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }
        });
        view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    view.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }
        });

    }
}
