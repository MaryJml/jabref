

package org.jabref.gui.search;

import javafx.collections.ObservableList;

import org.jabref.gui.StateManager;

public class HistoryDialogViewModel {
    private final StateManager stateManager;

    public HistoryDialogViewModel(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public ObservableList<String> getHistory() {
        return this.stateManager.getWholeSearchHistory();
    }

    public void delete(String searchHistory) {
        this.stateManager.deleteSearchHistory(searchHistory);
    }
}
