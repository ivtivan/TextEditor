package com.iviv.texteditor.Views;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iviv.texteditor.Controllers.ActionExecutor;

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

        searchTerm = new JTextField(15);
        
        JButton btnFindNext = new JButton("Find Next");
        JButton btnFindPrev = new JButton("Find Prev");
        JButton btnFindAll = new JButton("Find All");
        JButton btnCancel = new JButton("Cancel");;

        btnFindNext.addActionListener(this);
        btnFindPrev.addActionListener(this);
        btnFindAll.addActionListener(this);
        btnCancel.addActionListener(this);

        mainPanel.add(searchTermLabel);
        mainPanel.add(searchTerm);
        mainPanel.add(btnFindPrev);
        mainPanel.add(btnFindNext);
        mainPanel.add(btnFindAll);
        mainPanel.add(btnCancel);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) {
            dispose();
            return;
        }
        actionExecutor.executeCommand(e.getActionCommand(), searchTerm.getText());
        if (e.getActionCommand().equals("Find All")) {
            dispose();
        }
    }
}
