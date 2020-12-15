package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import state.AbstractState;
import state.InclusaoState;
import state.VisualizacaoState;

import view.InclusaoView;

public class InclusaoPresenter {

    private AbstractState state;
    private InclusaoView view;

    public InclusaoPresenter() {

        configurarView();

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

    private void configurarView() {

        this.view = new InclusaoView();

        view.setVisible(true);
    }

    public void setState(AbstractState state) {
        this.state = state;
    }

    public InclusaoView getView() {
        return view;
    }

    public void inclusao() {

        setState(new InclusaoState(this));

        state.inclusao();

    }

    public void visualizacao(Usuario u) {

        setState(new VisualizacaoState(this));
        state.visualizacao(u);

    }

}
