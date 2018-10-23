/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public interface ICommand {
  
    public void executar(ManterOrdemServicoPresenter presenter);
    
    public void editar(ManterOrdemServicoPresenter presenter, OrdemServico os);
    
    public void desfazer(BuscarOrdemServicoPresenter presenter);
    

    
}
