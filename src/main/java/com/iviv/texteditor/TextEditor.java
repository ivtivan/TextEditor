package com.iviv.texteditor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    ActionExecutor actionExecutor;

    public TextEditor() {
        actionExecutor = new ActionExecutor(this);
        setBasicProperties();

        // scrollPane for textarea
        add(constructScrollableTextArea());
        setJMenuBar(constructMenuBar());

        setVisible(true);
    }


    private void setBasicProperties() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text Editor");
        setSize(600, 600);
        setLocationRelativeTo(null);;
    }

    private JTextArea constructTextArea() {
        textArea = new JTextArea();
        textArea.setSize(getPreferredSize());
        textArea.setLineWrap(true);
        
        actionExecutor.setTargetTextArea(textArea);
        
        return textArea;
    }

    private JScrollPane constructScrollableTextArea() {
        JScrollPane scrollPane= new JScrollPane(constructTextArea());
        scrollPane.setSize(getPreferredSize());
        return scrollPane;
    }

    private JMenuBar constructMenuBar() {
        JMenuBar menuBar = new JMenuBar();   
        menuBar.add(constructMenuFile());
        menuBar.add(constructMenuEdit());

        return menuBar;
    }

    private JMenu constructMenuFile() {
        JMenu menuFile = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem newWindow = new JMenuItem("New Window");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem printFile = new JMenuItem("Print");

        newFile.addActionListener(this);
        newWindow.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);

        menuFile.add(newFile);
        menuFile.add(newWindow);
        menuFile.add(openFile);
        menuFile.add(saveFile);
        menuFile.add(printFile);
        return menuFile;
    }

    private JMenu constructMenuEdit() {
        JMenu menuEdit = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        menuEdit.add(cut);
        menuEdit.add(copy);
        menuEdit.add(paste);
        return menuEdit;
    }

    public void actionPerformed(ActionEvent e) {
        actionExecutor.executeCommand(e.getActionCommand());
    }
}
