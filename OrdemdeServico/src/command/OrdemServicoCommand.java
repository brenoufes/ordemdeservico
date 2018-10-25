/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class OrdemServicoCommand implements ICommand{

    private static OrdemServicoCommand instance;
    
    private OrdemServicoCommand(){    
    
    }
    
    public static OrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new OrdemServicoCommand();
        }
        return instance;
    }
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
        //Incluir OS
        if(os == null){            
            //Inclusão da ordem de serviço
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if(presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.")==0){
                    presenter.fecharView();
                }                                             
            });
            
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {                       
                //Criar uma OS com os dados captados da tela!                
                presenter.avancar(1, null);        
            });
               
        }else{            
           /* //Atualização da ordem de serviço          
            presenter.getView().getjButtonVoltar().addActionListener((e) -> {
                //aqui eu vou excluir a OS completa          
            });
            
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
                presenter.visualizar(1, os);
            });

            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.getView().getjButtonVoltar().setEnabled(false);
                presenter.removeActionListeners();
                
                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    presenter.fecharView();
                });
                
                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    
                    //Atualizar a os
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                    presenter.getView().getjButtonAvancar().setEnabled(true);
                    presenter.getView().getjButtonVoltar().setEnabled(true);
                    presenter.getView().getjButtonEditar().setText("Editar");
                    
                    
                    //Passar a nova OS atualizada
                    presenter.visualizar(0, os);
                    
                });
                
            });*/
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

    
}
