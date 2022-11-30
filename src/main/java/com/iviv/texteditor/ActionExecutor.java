package com.iviv.texteditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 *  Executes actions based on ActionEvents from the TextEditor 
 *  Creates Panels when errors occur;
 */
public class ActionExecutor {
    private JTextArea targetTextArea;
    private JFrame targetFrame;

    public ActionExecutor(JFrame targetFrame) {
        this.targetFrame = targetFrame;
    }

    public void setTargetTextArea(JTextArea targetTextArea) {
        this.targetTextArea = targetTextArea;
    }

    public void executeCommand(String command) {
        switch (command) {
            case "New": reset(); break;
            case "Open": open(); break;
            case "Save": save(); break;
            case "Print": print(); break;
            case "Cut": cut(); break;
            case "Copy": copy(); break;
            case "Paste": paste(); break;
            default: showMessage("Command not found."); break;
        }
    }

    private void open() {
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
                targetTextArea.setText(text.toString());
                br.close();
            }
            catch(Exception e) {
                showMessage(e.getMessage());
            }
            finally {
                try {
                    br.close();
                }
                catch (Exception e) {
                    showMessage(e.getMessage());
                }
            }
        }
    }
    
    private void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file, false));
                bw.write(targetTextArea.getText());
            }
            catch (Exception e) {
                showMessage(e.getMessage());
            }
            finally {
                if (bw != null) {
                    try {
                        bw.flush();
                        bw.close();
                    }
                    catch (Exception e) {
                        showMessage(e.getMessage());;
                    }
                }
            }
        }
    }
    private void print() {
        try {
            targetTextArea.print();
        }
        catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    private void reset() {
        targetTextArea.setText("");
    }

    private void cut() {
        targetTextArea.cut();
    }

    private void copy() {
        targetTextArea.copy();
    }

    private void paste() {
        targetTextArea.paste();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(targetFrame, message);
    }
}