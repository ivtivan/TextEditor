package com.iviv.texteditor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReplaceWidnow extends JFrame implements ActionListener {
    private ActionExecutor actionExecutor;
    private JTextField oldWord;
    private JTextField newWord;

    public ReplaceWidnow(ActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
        setBasicProperties();
        
        add(generateMainPanel());
        
        setVisible(true);
    }
    
    private void setBasicProperties() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Replace");
        setSize(300, 200);
        setLocationRelativeTo(null);
    }
    
    private JPanel generateMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        JLabel oldWordLabel = new JLabel("Replace:");
        JLabel newWordLabel = new JLabel("With:");

        oldWord = new JTextField();
        oldWord.setPreferredSize(new Dimension(30, 10));
        newWord = new JTextField(30);
        newWord.setPreferredSize(new Dimension(30, 10));
        
        JButton btnReplace = new JButton("Replace");
        JButton btnCancel = new JButton("Cancel");;

        btnReplace.addActionListener(this);
        btnCancel.addActionListener(this);

        mainPanel.add(oldWordLabel);
        mainPanel.add(oldWord);
        mainPanel.add(newWordLabel);
        mainPanel.add(newWord);
        mainPanel.add(btnReplace);
        mainPanel.add(btnCancel);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Replace")) {
            actionExecutor.executeCommand("Replace All Old New", oldWord.getText(), newWord.getText());
        }
        dispose();
    }

}
