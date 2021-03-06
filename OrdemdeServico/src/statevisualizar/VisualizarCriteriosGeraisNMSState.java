/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import command.ICommandTabela;
import commandexcluir.ExcluirCriterioGeralNMSCommand;
import state.ManterOrdemServicoTabelaState;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import presenter.TelaPrincipalPresenter;

/**
 *
 * @author Josep
 */
public class VisualizarCriteriosGeraisNMSState extends ManterOrdemServicoTabelaState {

    private ICommandTabela command;

    public VisualizarCriteriosGeraisNMSState(TabelaManterOSPresenter presenter) {
        super(presenter);
        this.command = ExcluirCriterioGeralNMSCommand.getInstance();
    }

    @Override
    public void visualizar(Object o, OrdemServico os) {
        this.presenter.resetarConfiguracoes();
        this.presenter.getView().setTitle("Registro de Níveis Mínimos de Serviço (Visualização / Edição)");
        this.presenter.getView().getjLabelTitulo().setText("Critérios Gerais de NMS");
        this.presenter.getView().getjLabelTitulo().setVisible(true);
        this.presenter.getView().getjButtonEditar().setText("Excluir");
        this.presenter.getView().setVisible(true);

        try {
            this.preencherTabela(this.presenter, os.getNivelMinimoServico().getCriteriosGerais());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        ArrayList<CriterioGeralNMS> criterios = os.getNivelMinimoServico().getCriteriosGerais();

        this.presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (CriterioGeralNMS criterio : criterios) {
                    try {
                        if (this.presenter.getView().getjTable().getValueAt(this.presenter.getView().getjTable().getSelectedRow(), 0).equals(criterio.getCriterio())) {
                            TelaPrincipalPresenter.getInstance().getView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
                            ManterOrdemServicoPresenter.getInstance().visualizar(3, os, criterio, null);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o Critério para a visualização!");
            }
        });

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente sair? \n A janela será fechada e o restante da edição não será realizada.\n Atualizações já feitas serão mantidas.") == 0) {
                this.presenter.fecharView();
                ManterOrdemServicoPresenter.getInstance().fecharView();
            }
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                if (this.presenter.getView().getjTable().getRowCount() > 1) {
                    if (this.presenter.setJanelaConfirmacao("Deseja  mesmo Excluir este critério? ") == 0) {
                        for (CriterioGeralNMS criterio : criterios) {
                            try {
                                if (this.presenter.getView().getjTable().getValueAt(this.presenter.getView().getjTable().getSelectedRow(), 0).equals(criterio.getCriterio())) {
                                    //Realizar a exclusão do critério selecionado
                                    this.command.executar(this.presenter, criterio, os);
                                    TabelaManterOSPresenter.getInstance().visualizar(null, os, 3);
                                }
                            } catch (Exception ex) {
                                //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão deste critério pois ele é unico!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar um critério para a exclusão!");
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja seguir para Niveis de Serviço?") == 0) {
                this.presenter.visualizar(null, os, 4);
            }
        });

    }

    private DefaultTableModel montarTabela(TabelaManterOSPresenter presenter) {
        presenter.setTablemodel(new DefaultTableModel(new Object[][]{}, new String[]{"Critério", "Redutor(%)", "Aplicação", "Quantidade", "Observações", "Valor da Redução (R$)"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        return presenter.getTablemodel();
    }

    private void preencherTabela(TabelaManterOSPresenter presenter, ArrayList<CriterioGeralNMS> criterios) throws Exception {
        presenter.setTablemodel(this.montarTabela(presenter));
        for (CriterioGeralNMS criterio : criterios) {
            try {
                presenter.getTablemodel().addRow(new Object[]{
                    criterio.getCriterio(),
                    criterio.getRedutor(),
                    criterio.getAplicacao(),
                    criterio.getQuantidade(),
                    criterio.getObservacao(),
                    criterio.getValorReducao()
                });
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        presenter.getView().getjTable().setModel(presenter.getTablemodel());
    }

}
