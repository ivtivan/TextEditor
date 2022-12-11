package com.iviv.texteditor.Controllers;

import java.awt.Color;
import java.util.Stack;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;

import com.iviv.texteditor.Views.TextEditor;

public class FindController extends ActionController {
    private Stack<Integer> foundAt; // filled by search next; used for search prev
    HighlightPainter painter;
    String searchTerm;

    public FindController(TextEditor targetEditor) {
        super(targetEditor);
        foundAt = new Stack<>();
        painter = new DefaultHighlightPainter(Color.LIGHT_GRAY);
    }

    private void setSearchTerm(String searchTerm) {
        if (this.searchTerm == null || !this.searchTerm.equals(searchTerm)) {
            reset();
            this.searchTerm = searchTerm;
        }
    }

    private boolean isValid(String... params) {
        return !((params == null || params.length != 1 || params[0].equals("")));
    }

    @Override
    public void executeCommand(String command, String... params) {
        if (!isValid(params)) {
            return;
        }

        setSearchTerm(params[0]);
        switch (command) {
            case "Find Prev": findPrev(); break;
            case "Find Next": findNext(); break;
            case "Find All": findAll(); break;
            default: passMessage("Command not found."); break;
        }
    }

    public void reset() {
        removeAllHighlights();
        resetSearchResults();
    }

    private void removeAllHighlights() {
        getTargetEditor().getTextArea().getHighlighter().removeAllHighlights();
    }

    private void removeLastHighlight() {
        Highlight highlights[] = getTargetEditor().getTextArea().getHighlighter().getHighlights();
        
        if (highlights.length == 0) {
            return;
        }
        
        getTargetEditor().getTextArea().getHighlighter().removeHighlight(highlights[highlights.length - 1]);
    }

    private void highlightSearchResult() {
        if (foundAt.isEmpty()) {
            passMessage("Tried to highlight a word that wasn't found.");
            return;
        }

        try
        {
            getTargetEditor().getTextArea().getHighlighter().addHighlight(foundAt.peek(), foundAt.peek() + searchTerm.length(), painter);
        }
        catch(BadLocationException e) {
            passMessage(e.getMessage());
        }
    }

    private int findNextIndex() {
        int searchFrom = 0;

        if (!foundAt.isEmpty()) {
            searchFrom = foundAt.peek() + 1;
        }

        foundAt.add(getTargetEditor().getTextArea().getText().indexOf(searchTerm, searchFrom));
        return foundAt.peek();
    }

    private int findPrevIndex() {
        foundAt.pop();
        if (foundAt.isEmpty()) {
            return -1;
        }
        return foundAt.peek();
    }

    private void resetSearchResults() {
        foundAt.clear();
    }

    public void findAll() {
        findNextIndex();

        while (foundAt.peek() != -1) {
            highlightSearchResult();
            findNextIndex();
        }

        resetSearchResults();
    }

    public void findPrev() {
        if (foundAt.size() == 1 || foundAt.isEmpty()) {
            passMessage("No search results.");
            return;
        }

        findPrevIndex();

        removeLastHighlight();
        highlightSearchResult();
    }

    public void findNext() {
        findNextIndex();

        if (foundAt.peek() == -1) {
            passMessage("No search results.");
            foundAt.pop();
            return;
        }

        removeLastHighlight();
        highlightSearchResult();
    }
}
