/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public abstract class StateTabelaManterOrdemServico {
    
     protected TabelaManterOSPresenter presenter;  
    
    public StateTabelaManterOrdemServico(TabelaManterOSPresenter presenter) {
        this.presenter = presenter;
    }

    public void visualizar(Object o, OrdemServico os) {
        throw new IllegalStateException();
    }
    
}