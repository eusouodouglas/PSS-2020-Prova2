/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.ImagemDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Imagem;
import state.AbstractState;
import view.ImagemView;

/**
 *
 * @author Douglas
 */
public class ImagemPresenter {

    private DefaultTableModel tablemodel;
    private AbstractState state;
    private ImagemView view;

    public ImagemPresenter() {
        configTela();
    }

    private void configTela() {

        this.configurarView();
        view.setVisible(true);

    }

    private void configurarView() {
        this.view = new ImagemView();
        this.view.moveToFront();

        this.view.getBtnVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    detalhar();
                    //   view.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }

        });

        try {
            this.preencherTabela();
        } catch (Exception ex) {
           
            JOptionPane.showMessageDialog(this.view, ex.getMessage());
        }

        this.view.setVisible(
                true);

    }

    private DefaultTableModel montarTabela() {
        this.tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"Código(#)", "Nome da imagem"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        return tablemodel;
    }

    private void preencherTabela() throws Exception {
        this.tablemodel = this.montarTabela();

        for (Imagem imagem : ImagemDAO.getImagemDAOInstance().getTodos()) {

            this.tablemodel.addRow(new Object[]{
                imagem.getIdImagem(),
                imagem.getNome()

            });

            this.view.getTbImagens().setModel(tablemodel);
        }

    }

    private void detalhar() throws Exception {
        if (view.getTbImagens().getSelectedRow() >= 0) {
            int codigo = (int) view.getTbImagens().getValueAt(view.getTbImagens().getSelectedRow(), 0);
            String nome = view.getTbImagens().getValueAt(view.getTbImagens().getSelectedRow(), 1).toString();

            Imagem imagem = new Imagem(codigo, nome);
            JOptionPane.showMessageDialog(view, "Exbindo agora a imagem: " + imagem.getNome());
            
        }else {
            throw new Exception("Selecione uma imagem");
        }
    }

    public ImagemView getView() {
        return view;
    }
}
