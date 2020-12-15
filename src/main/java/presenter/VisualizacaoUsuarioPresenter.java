package presenter;

import dao.UsuarioDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import observer.Observador;
import view.VisualizacaoUsuarioView;

public class VisualizacaoUsuarioPresenter implements Observador {

    private VisualizacaoUsuarioView view;
    private DefaultTableModel tablemodel;
    private JComboBox cbBuscar;

    private UsuarioDAO usuarioDAO = null;

    public VisualizacaoUsuarioPresenter() {
        registra();
        configTela();

    }

    public void registra() {
        try {
            this.usuarioDAO = UsuarioDAO.getUsuarioDAOInstance();
        } catch (Exception ex) {
            Logger.getLogger(VisualizacaoUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.usuarioDAO.registerObserver(this);
    }

    @Override
    public void update() {

        try {
            preencherTabela();
            attTotal();

        } catch (Exception ex) {
            Logger.getLogger(VisualizacaoUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configTela() {

//        System.out.println(this.funcionarioDAO.getObservers().size());
        this.configurarView();

        this.view.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        });
        view.getBtnDeletar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    int admin;
                    if (view.getTbUsuarios().getSelectedRow() >= 0) {
                        int codigo = (int) view.getTbUsuarios().getValueAt(view.getTbUsuarios().getSelectedRow(), 0);
                        String nome = view.getTbUsuarios().getValueAt(view.getTbUsuarios().getSelectedRow(), 1).toString();
                        String senha = view.getTbUsuarios().getValueAt(view.getTbUsuarios().getSelectedRow(), 2).toString();

                        if (view.getTbUsuarios().getValueAt(view.getTbUsuarios().getSelectedRow(), 3) == "Administrador") {
                            admin = 1;
                        } else {
                            admin = 0;
                        }

                        Usuario usuario = new Usuario(codigo, nome, senha, admin);

                        UsuarioDAO.getUsuarioDAOInstance().deletar(usuario.getIdUsuario());

                        JOptionPane.showMessageDialog(view, "Usuario " + usuario.getNome() + " deletado com sucesso!");
                        view.dispose();

                    }

                    //   view.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }

            }
        }
        );

        view.setVisible(
                true);

    }

    private void attTotal() {
        try {
            int quantidadeFuncionarios = UsuarioDAO.getUsuarioDAOInstance().total();
            view.getTxtTotal().setText(quantidadeFuncionarios + "");
        } catch (Exception ex) {
            Logger.getLogger(VisualizacaoUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configurarView() {
        this.view = new VisualizacaoUsuarioView();
        this.view.moveToFront();
        try {
            this.preencherTabela();
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this.view, ex.getMessage());
        }
        this.view.setVisible(true);
        view.getTxtTotal().setEditable(false);
        attTotal();

    }

    private DefaultTableModel montarTabela() {
        this.tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"Código(#)", "Nome",
            "Senha", "Acesso"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        return tablemodel;
    }

    private void preencherTabela() throws Exception {
        this.tablemodel = this.montarTabela();
        String adm;

        for (Usuario usuario : UsuarioDAO.getUsuarioDAOInstance().getTodos()) {
            if (usuario.getAdmin() > 0) {
                adm = "Administrador";
            } else {
                adm = "Usuário comum";
            }

            this.tablemodel.addRow(new Object[]{
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getSenha(),
                adm,});

            this.view.getTbFuncionarios().setModel(tablemodel);
        }

    }

    public VisualizacaoUsuarioView getView() {
        return view;
    }

}
