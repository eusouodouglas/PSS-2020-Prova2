package presenter;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import view.CadastroUsuarioView;



public class CadastroFuncionarioPresenter {

    private CadastroUsuarioView view;

    public CadastroFuncionarioPresenter() {
        configurarTela();

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

        view.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        });
    }

    private void salvar() throws Exception {
        
       

        int idUsuario = Integer.parseInt(view.getTxtCodigo().getText());
        String nome = view.getTxtNome().getText();
        String senha = view.getTxtCargo().getText();
        int admin = Integer.parseInt(view.getTxtSalario().getText());
        
        
        Usuario usuario = new Usuario(idUsuario, nome, senha, admin);

        UsuarioDAO.getUsuarioDAOInstance().inserir(usuario);

        JOptionPane.showMessageDialog(view, "Usuário " + usuario.getNome() + " cadastrado com sucesso!");
        view.dispose();

        
        new VisualizacaoUsuarioPresenter();

    
   }
    private void configurarTela() {
        this.view = new CadastroUsuarioView();
        view.setTitle("Cadastrar Usuário");
        view.setVisible(true);

    }

    public CadastroUsuarioView getView() {
        return view;
    }
    

}
