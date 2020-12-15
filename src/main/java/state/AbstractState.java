/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import model.Usuario;
import presenter.InclusaoPresenter;

/**
 *
 * @author Douglas
 */
public abstract class AbstractState {
    
    protected InclusaoPresenter presenter;
   
       public AbstractState(InclusaoPresenter presenter) {
       this.presenter = presenter;
    }
    public void inclusao(){};
    public void edicao(Usuario u){};
    public void visualizacao(Usuario u){};

    
    
    
    
}
