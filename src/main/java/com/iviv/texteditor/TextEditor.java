package com.iviv.texteditor;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text Editor");
        setSize(600, 600);
        setLocationRelativeTo(null);;

        // scrollPane for textarea
        add(constructScrollPane());
        setJMenuBar(constructMenuBar());

        setVisible(true);
    }


    private JTextArea constructTextArea() {
        textArea = new JTextArea();
        textArea.setSize(getPreferredSize());
        textArea.setLineWrap(true);
        return textArea;
    }

    private JScrollPane constructScrollPane() {
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
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem printFile = new JMenuItem("Print");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);

        menuFile.add(newFile);
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
        String command = e.getActionCommand();

        if (command.equals("New")) {
            textArea.setText("");;
        }
        else if (command.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                BufferedReader br = null;
                try {
                    String readLine;
                    StringBuilder text = new StringBuilder("");
                    br = new BufferedReader(new FileReader(file));
                    while((readLine = br.readLine()) != null) {
                        text.append(readLine);
                        text.append("\n");
                    }
                    textArea.setText(text.toString());
                    br.close();
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(textArea, ex.getMessage());
                }
                finally {
                    try {
                        br.close();
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }
            }
        }
        else if (command.equals("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(file, false));
                    bw.write(textArea.getText());
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                finally {
                    if (bw != null) {
                        try {
                            bw.flush();
                            bw.close();
                        }
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage());
                        }
                    }
                }
            }
        }
        else if (command.equals("Print")) {
            try {
                textArea.print();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (command.equals("Cut")) {
            textArea.cut();
        }
        else if (command.equals("Copy")) {
            textArea.copy();
        }
        else if (command.equals("Paste")) {
            textArea.paste();
        }
    }
}
