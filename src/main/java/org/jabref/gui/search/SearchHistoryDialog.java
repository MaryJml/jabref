package org.jabref.gui.search;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;

import org.jabref.gui.StateManager;
import org.jabref.gui.icon.IconTheme;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ValueTableCellFactory;
import org.jabref.logic.l10n.Localization;

import com.airhacks.afterburner.views.ViewLoader;
import org.controlsfx.control.textfield.CustomTextField;

public class SearchHistoryDialog extends BaseDialog<Void> {
    @FXML private TableView<String> table;
    @FXML private TableColumn<String, String> searchContent;
    @FXML private TableColumn<String, String> delete;
    private final StateManager stateManager;
    private HistoryDialogViewModel viewModel;
    private final CustomTextField searchField;

    public SearchHistoryDialog(StateManager stateManager, CustomTextField searchField) {
        this.stateManager = stateManager;
        this.searchField = searchField;

        ViewLoader.view(this)
                  .load()
                  .setAsDialogPane(this);
    }

    @FXML
    private void initialize() {
        viewModel = new HistoryDialogViewModel(stateManager);

        table.setRowFactory(tableView -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(evt -> {
                if (!row.isEmpty() && evt.getButton() == MouseButton.PRIMARY && evt.getClickCount() == 2) {
                    String searchStringClicked = row.getItem();
                    searchField.setText(searchStringClicked);
                    this.close();
                }
            });
            return row;
        });
        table.setItems(viewModel.getHistory());

        searchContent.setEditable(false);
        searchContent.setCellFactory(TextFieldTableCell.forTableColumn());
        searchContent.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));

        delete.setEditable(false);
        delete.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));

        new ValueTableCellFactory<String, String>()
                .withGraphic(item -> IconTheme.JabRefIcons.DELETE_ENTRY.getGraphicNode())
                .withTooltip(name -> Localization.lang("Delete"))
                .withOnMouseClickedEvent(item -> evt -> viewModel.delete(item))
                .install(delete);
    }

    public void clear() {
        viewModel.clear();
    }
}
