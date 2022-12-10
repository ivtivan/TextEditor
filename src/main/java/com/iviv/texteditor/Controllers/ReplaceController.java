package com.iviv.texteditor.Controllers;

import com.iviv.texteditor.Views.TextEditor;

public class ReplaceController {
    private TextEditor targetEditor;

    public ReplaceController(TextEditor targetEditor) {
        this.targetEditor = targetEditor;
    }

    public void executeCommand(String command, String... params) {
        switch (command) {
            case "Replace All Old New": replace(params[0], params[1]); break;
            default: passMessage("Command not found."); break;
        }
    }

    public void replace(String oldWord, String newWord) {
        targetEditor.getTextArea().setText(targetEditor.getTextArea().getText().replace(oldWord, newWord));
    }

    private void passMessage(String message) {
        targetEditor.showMessage(message);
    }
}
