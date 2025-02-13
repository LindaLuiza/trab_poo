package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import controller.MainController;
import dtos.HospedagemDto;

public class ListarHospedagens extends JFrame {

    private static final long serialVersionUID = -6671682771481820010L;
	private JPanel contentPane;
    private JList<String> listHospedagens;
    private DefaultListModel<String> listModel;

    public ListarHospedagens() {
        setTitle("Listar Hospedagens");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        listModel = new DefaultListModel<>();
        listHospedagens = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listHospedagens);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        carregarHospedagens();
    }

    private void carregarHospedagens() {
        listModel.clear();
        List<HospedagemDto> hospedagens = MainController.getHospedagemController().getHospedagens();
        for (HospedagemDto h : hospedagens) {
            String info = String.format("ID: %s - Check-in: %s - Check-out: %s - Hóspede: %s", 
                    h.getId(), h.getCheckin(), h.getCheckout(), h.getHospede().getNome());
            listModel.addElement(info);
        }
    }
}
