package com.iviv.texteditor.Controllers;

import com.iviv.texteditor.Views.TextEditor;

// Parent class for controllers
public abstract class ActionController {
    private TextEditor targetEditor;
    public abstract void executeCommand(String command, String... params);

    protected ActionController(TextEditor targetEditor) {
        this.targetEditor = targetEditor;
    }

    protected TextEditor getTargetEditor() {
        return targetEditor;
    }

    protected void passMessage(String message) {
        targetEditor.showMessage(message);
    }
}
