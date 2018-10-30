/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import model.OrdemServico;
import state.StateTabelaManterOrdemServico;
import state.TabelaManterHistoriaState;
import view.TabelaManterOSView;


/**
 *
 * @author Josep
 */
public class TabelaManterOSPresenter {
    private static TabelaManterOSPresenter instance;
    private final TabelaManterOSView view;
    private StateTabelaManterOrdemServico state;
    private DefaultTableModel tablemodel;
    
    private TabelaManterOSPresenter(){        
        this.view = new TabelaManterOSView();
        this.configurarView();
    }
    
    
    public static TabelaManterOSPresenter getInstance(){
        if(instance == null){
            instance = new TabelaManterOSPresenter();
        }
        return instance;
    }   
     
    public void configurarView() {
        this.resetActionListeners();
        this.setPosicao();          
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    TabelaManterOSPresenter.getInstance().fecharView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        this.visibilidadeCampos(false, false, false, false);
     }
   
    public void visualizar(OrdemServico os){
        this.state = new TabelaManterHistoriaState(this);
        this.state.visualizar(os);
    }
    
    public void fecharView() {
        TabelaManterOSPresenter.instance = null;
        this.view.dispose();
    }

    private void setPosicao() {
        Dimension d = TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);
    }
    
    public void resetActionListeners(){
    for (ActionListener act : this.view.getjButtonCancelar().getActionListeners()) {
            this.view.getjButtonCancelar().removeActionListener(act);
        }

        for (ActionListener act : this.view.getjButtonVisualizar().getActionListeners()) {
            this.view.getjButtonVisualizar().removeActionListener(act);
        }
    }

    public TabelaManterOSView getView() {
        return view;
    }

    public StateTabelaManterOrdemServico getState() {
        return state;
    }

    public DefaultTableModel getTablemodel() {
        return tablemodel;
    }

    public void setTablemodel(DefaultTableModel tablemodel) {
        this.tablemodel = tablemodel;
    }
   
    public int setJanelaConfirmacao(String mensagem) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        switch (JOptionPane.showConfirmDialog(null, mensagem, "Mensagem", JOptionPane.YES_NO_OPTION)) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        return -1;
    }
    
    public void visibilidadeCampos(boolean b1, boolean b2, boolean b3, boolean b4){
        this.view.getjLabel1().setVisible(b1);
        this.view.getjTextField1().setVisible(b2);
        this.view.getjLabel2().setVisible(b3);
        this.view.getjTextField2().setVisible(b4);    
    }
    
    public void preencherCampos(String s1, String s2, String s3, String s4){
        this.view.getjLabel1().setText(s1);
        this.view.getjTextField1().setText(s2);
        this.view.getjLabel2().setText(s3);
        this.view.getjTextField2().setText(s4);    
    }
    
    public void bloquearTextFields(boolean b1, boolean b2){
        this.view.getjTextField1().setEnabled(b1);
        this.view.getjTextField2().setEnabled(b2);    
    }
  
}
