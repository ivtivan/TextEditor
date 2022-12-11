package com.iviv.texteditor.Controllers;

import com.iviv.texteditor.Views.FindWidnow;
import com.iviv.texteditor.Views.ReplaceWidnow;
import com.iviv.texteditor.Views.TextEditor;

/*
 *  Passes the command to the respective controller
 *  Distributes actions based on ActionEvents from the TextEditor 
 */
public class ActionDistributor extends ActionController {
    public ActionDistributor(TextEditor targetEditor) {
       super(targetEditor);
    }

    @Override
    public void executeCommand(String command, String... params) {
        switch (command) {
            case "Find": spawnFind(); break;
            case "Replace": spawnReplace(); break;
            default: new FileController(getTargetEditor()).executeCommand(command, params);;
        }
    }

    private void spawnFind() {
        getTargetEditor().addChildFrame(new FindWidnow(getTargetEditor()));
    }

    private void spawnReplace() {
        getTargetEditor().addChildFrame(new ReplaceWidnow(getTargetEditor()));
    }
}