package com.iviv.texteditor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private ActionExecutor actionExecutor;

    public TextEditor() {
        actionExecutor = new ActionExecutor(this);
        setBasicProperties();

        // scrollPane for textarea
        add(constructScrollableTextArea());
        setJMenuBar(constructMenuBar());

        setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    private void setBasicProperties() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Text Editor");
        setSize(600, 600);
        setLocationRelativeTo(null);
    }

    private JTextArea constructTextArea() {
        textArea = new JTextArea();
        textArea.setSize(getPreferredSize());
        textArea.setLineWrap(true);        
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
        JMenuItem exit = new JMenuItem("Exit");

        newFile.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        newWindow.setAccelerator(KeyStroke.getKeyStroke("shift ctrl pressed N"));
        openFile.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        printFile.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exit.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        newFile.addActionListener(this);
        newWindow.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        exit.addActionListener(this);

        menuFile.add(newFile);
        menuFile.add(newWindow);
        menuFile.add(openFile);
        menuFile.add(saveFile);
        menuFile.addSeparator();
        menuFile.add(printFile);
        menuFile.addSeparator();
        menuFile.add(exit);

        return menuFile;
    }

    private JMenu constructMenuEdit() {
        JMenu menuEdit = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem find = new JMenuItem("Find");
        JMenuItem replace = new JMenuItem("Replace");
        JMenuItem date = new JMenuItem("Date");

        cut.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        copy.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        paste.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        find.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        replace.setAccelerator(KeyStroke.getKeyStroke('H', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        find.addActionListener(this);
        replace.addActionListener(this);
        date.addActionListener(this);

        menuEdit.add(cut);
        menuEdit.add(copy);
        menuEdit.add(paste);
        menuEdit.addSeparator();
        menuEdit.add(find);
        menuEdit.add(replace);
        menuEdit.addSeparator();
        menuEdit.add(date);

        return menuEdit;
    }

    public void actionPerformed(ActionEvent e) {
        actionExecutor.executeCommand(e.getActionCommand());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
