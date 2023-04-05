

package org.jabref.gui.search;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jabref.gui.StateManager;

public class HistoryDialogViewModel {
    private final StateManager stateManager;

    public HistoryDialogViewModel(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public ObservableList<String> getHistory() {
        ObservableList<String> list = this.stateManager.getWholeSearchHistory();
        FXCollections.reverse(list);
        return list;
    }

    public void clear() {
        this.stateManager.clearSearchHistory();
    }

    public void delete(String searchHistory) {
        this.stateManager.deleteSearchHistory(searchHistory);
    }
}
