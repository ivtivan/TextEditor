package com.iviv.texteditor.Controllers;

import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter.HighlightPainter;

public class FindController {
    private void highlightSearchResult(String searchTerm) {
        HighlightPainter painter = new DefaultHighlightPainter(Color.LIGHT_GRAY);
        try
        {
            targetEditor.getTextArea().getHighlighter().addHighlight(searchIndex, searchIndex + searchTerm.length(), painter);
        }
        catch(BadLocationException e) {
            passMessage(e.getMessage());
        }
    }

    private void findNextIndex(String searchTerm) {
        searchIndex = targetEditor.getTextArea().getText().indexOf(searchTerm, searchIndex + 1);
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
