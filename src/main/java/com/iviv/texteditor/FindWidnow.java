package com.iviv.texteditor;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindWidnow extends JFrame implements ActionListener {
    private ActionExecutor actionExecutor;
    private JTextField searchTerm;

    public FindWidnow(ActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
        setBasicProperties();
        
        add(generateMainPanel());
        
        setVisible(true);
    }

    private void setBasicProperties() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private JPanel generateMainPanel() {
        JPanel mainPanel = new JPanel();
        JLabel searchTermLabel = new JLabel("Search for:");

        searchTerm = new JTextField();
        
        JButton btnFindNext = new JButton("Find Next");
        JButton btnFindAll = new JButton("Find All");
        JButton btnCancel = new JButton("Cancel");;

        btnFindNext.addActionListener(this);
        btnFindAll.addActionListener(this);
        btnCancel.addActionListener(this);

        mainPanel.add(searchTermLabel);
        mainPanel.add(searchTerm);
        mainPanel.add(btnFindNext);
        mainPanel.add(btnFindAll);
        mainPanel.add(btnCancel);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (!e.getActionCommand().equals("Cancel")) {
            actionExecutor.executeCommand(e.getActionCommand(), searchTerm.getText());
        }
        dispose();
    }
}
