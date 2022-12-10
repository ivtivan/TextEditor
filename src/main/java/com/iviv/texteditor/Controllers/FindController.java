package com.iviv.texteditor.Controllers;

import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.HighlightPainter;

import com.iviv.texteditor.Views.TextEditor;

public class FindController extends ActionController {
    private int searchIndex;

    public FindController(TextEditor targetEditor) {
        super(targetEditor);
        searchIndex = -1;
    }

    public void executeCommand(String command, String... params) {
        switch (command) {
            case "Find Prev": findPrev(params[0]); break;
            case "Find Next": findNext(params[0]); break;
            case "Find All": findAll(params[0]); break;
            default: passMessage("Command not found."); break;
        }
    }

    private void highlightSearchResult(String searchTerm) {
        HighlightPainter painter = new DefaultHighlightPainter(Color.LIGHT_GRAY);
        try
        {
            getTargetEditor().getTextArea().getHighlighter().addHighlight(searchIndex, searchIndex + searchTerm.length(), painter);
        }
        catch(BadLocationException e) {
            passMessage(e.getMessage());
        }
    }

    private void findNextIndex(String searchTerm) {
        searchIndex = getTargetEditor().getTextArea().getText().indexOf(searchTerm, searchIndex + 1);
    }

    private void resetSearchIndex() {
        searchIndex = -1;
    }

    public void findAll(String searchTerm) {
        findNextIndex(searchTerm);
        while (searchIndex != -1) {
            highlightSearchResult(searchTerm);
            findNextIndex(searchTerm);
        }
    }

    public void findPrev(String searchTerm) {
        findNextIndex(searchTerm);
        if (searchIndex == -1) {
            resetSearchIndex();
            passMessage("No search results.");
        }

        highlightSearchResult(searchTerm);
    }

    public void findNext(String searchTerm) {
        findNextIndex(searchTerm);
        if (searchIndex == -1) {
            resetSearchIndex();
            passMessage("No search results.");
        }

        highlightSearchResult(searchTerm);
    }
}
