/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandincluir;

import command.ICommandManterOS;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class IncluirRegistroNMSNiveisServicoCommand implements ICommandManterOS{

private static IncluirRegistroNMSNiveisServicoCommand instance;

    private IncluirRegistroNMSNiveisServicoCommand() {

    }

    public static IncluirRegistroNMSNiveisServicoCommand getInstance() {
        if (instance == null) {
            instance = new IncluirRegistroNMSNiveisServicoCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
            /*presenter.getView().getjButtonAvancar().addActionListener((e1) -> {

            });

            presenter.getView().getjButtonVoltar().addActionListener((e) -> {
                presenter.visualizar(2, os);
            });
            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.getView().getjButtonVoltar().setEnabled(false);
                presenter.resetarBotoesTextFielLabels();
                presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);

                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    presenter.fecharView();
                });

                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    //Atualizar A OS   

                    //salvar no banco
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");

                    presenter.getView().getjButtonAvancar().setEnabled(true);
                    presenter.getView().getjButtonVoltar().setEnabled(true);
                    presenter.getView().getjButtonEditar().setText("Editar");
                    //Passar a nova OS atualizada
                    presenter.visualizar(3, os);
                });
            });
            */
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
}