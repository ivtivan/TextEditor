package com.iviv.texteditor.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import com.iviv.texteditor.Views.TextEditor;

/*
 * Executes commands passed through the file menu
 */
public class FileController extends ActionController {
    public FileController(TextEditor targetEditor) {
        super(targetEditor);
    }

    @Override
    public void executeCommand(String command, String... params) {
        switch (command) {
            case "New": reset(); break;
            case "New Window": openNewWindow(); break;
            case "Open": open(); break;
            case "Save": save(); break;
            case "Print": print(); break;
            case "Exit": exit(); break;
            case "Cut": cut(); break;
            case "Copy": copy(); break;
            case "Paste": paste(); break;
            case "Date": appendDate(); break;
            default: passMessage("Command not found."); break;
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
                getTargetEditor().getTextArea().setText(text.toString());
                br.close();
            }
            catch(Exception e) {
                passMessage(e.getMessage());
            }
            finally {
                try {
                    br.close();
                }
                catch (Exception e) {
                    passMessage(e.getMessage());
                }
            }
        }
    }

    private void openNewWindow() {
        new TextEditor();
    }
    
    private void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file, false));
                bw.write(getTargetEditor().getTextArea().getText());
            }
            catch (Exception e) {
                passMessage(e.getMessage());
            }
            finally {
                if (bw != null) {
                    try {
                        bw.flush();
                        bw.close();
                    }
                    catch (Exception e) {
                        passMessage(e.getMessage());;
                    }
                }
            }
        }
    }

    private void print() {
        try {
            getTargetEditor().getTextArea().print();
        }
        catch (Exception e) {
            passMessage(e.getMessage());
        }
    }

    private void exit() {
        getTargetEditor().dispose();
    }

    private void reset() {
        getTargetEditor().getTextArea().setText("");
    }

    private void cut() {
        getTargetEditor().getTextArea().cut();
    }

    private void copy() {
        getTargetEditor().getTextArea().copy();
    }

    private void paste() {
        getTargetEditor().getTextArea().paste();
    }

    private void appendDate() {
        getTargetEditor().getTextArea().append(java.time.LocalDate.now().toString());
    }

}
