package com.iviv.texteditor.Views;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iviv.texteditor.Controllers.ReplaceController;

public class ReplaceWidnow extends JFrame implements ActionListener {
    private ReplaceController replaceController;
    private JTextField oldWord;
    private JTextField newWord;

    public ReplaceWidnow(TextEditor targetEditor) {
        replaceController = new ReplaceController(targetEditor);
        setBasicProperties();
        
        add(generateMainPanel());
        
        setVisible(true);
    }

    private void setBasicProperties() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Replace");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private JPanel generateMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        JLabel oldWordLabel = new JLabel("Replace:");
        JLabel newWordLabel = new JLabel("With:");

        oldWord = new JTextField();
        newWord = new JTextField();
        
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
            replaceController.executeCommand("Replace All Old New", oldWord.getText(), newWord.getText());
        }
        dispose();
    }
}
