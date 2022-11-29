package com.iviv.texteditor;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener {
    JMenuItem open;
    JMenuItem save;
    JTextArea textArea;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text Editor");
        setSize(600, 600);
        setLocationRelativeTo(null);;

        // text area and menu bar
        textArea = constructTextArea();
        add(textArea);
        setJMenuBar(constructMenuBar());

        setVisible(true);
    }

    private JTextArea constructTextArea() {
        textArea = new JTextArea();
        textArea.setSize(getPreferredSize());
        textArea.setLineWrap(true);
        return textArea;
    }

    private JMenuBar constructMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");

        open.addActionListener(this);
        save.addActionListener(this);

        menu.add(open);
        menu.add(save);
        
        menuBar.add(menu);
        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {

        }
        else if (e.getSource() == save) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                PrintWriter fileOut = null;
                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println();
                }
                catch (FileNotFoundException exc) {
                    System.out.println("Couldn't save file.");
                }
                finally {
                    if (fileOut != null) {
                        fileOut.close();
                    }
                }
            }
        }
    }
}
