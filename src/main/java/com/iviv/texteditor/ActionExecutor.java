package com.iviv.texteditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
/*
 *  Executes actions based on ActionEvents from the TextEditor 
 *  Creates Panels when errors occur;
 */
public class ActionExecutor {
    private TextEditor targetEditor;

    public ActionExecutor(TextEditor targetFrame) {
        this.targetEditor = targetFrame;
    }

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
            case "Find": spawnFind(); break;
            case "Replace": spawnReplace(); break;
            case "Replace All Old New": replace(params[0], params[1]); break;
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
                targetEditor.getTextArea().setText(text.toString());
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
                bw.write(targetEditor.getTextArea().getText());
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
            targetEditor.getTextArea().print();
        }
        catch (Exception e) {
            passMessage(e.getMessage());
        }
    }

    private void exit() {
        targetEditor.dispose();
    }

    private void reset() {
        targetEditor.getTextArea().setText("");
    }

    private void cut() {
        targetEditor.getTextArea().cut();
    }

    private void copy() {
        targetEditor.getTextArea().copy();
    }

    private void paste() {
        targetEditor.getTextArea().paste();
    }

    private void appendDate() {
        targetEditor.getTextArea().append(java.time.LocalDate.now().toString());
    }

    private void spawnFind() {

    }

    private void spawnReplace() {
        targetEditor.addChildFrame(new ReplaceWidnow(this));
    }

    public void replace(String oldWord, String newWord) {
        targetEditor.getTextArea().setText(targetEditor.getTextArea().getText().replace(oldWord, newWord));
    }

    private void passMessage(String message) {
        targetEditor.showMessage(message);
    }
}