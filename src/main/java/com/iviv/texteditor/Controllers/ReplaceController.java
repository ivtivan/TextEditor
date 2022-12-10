package com.iviv.texteditor.Controllers;

import com.iviv.texteditor.Views.TextEditor;

public class ReplaceController extends ActionController {
    public ReplaceController(TextEditor targetEditor) {
        super(targetEditor);
    }

    public void executeCommand(String command, String... params) {
        switch (command) {
            case "Replace All Old New": replace(params[0], params[1]); break;
            default: passMessage("Command not found."); break;
        }
    }

    public void replace(String oldWord, String newWord) {
        getTargetEditor().getTextArea().setText(getTargetEditor().getTextArea().getText().replace(oldWord, newWord));
    }
}
