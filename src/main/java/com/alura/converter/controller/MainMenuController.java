package com.alura.converter.controller;

import com.alura.converter.ConverterApp;
import com.alura.converter.enums.CurrencyUnit;
import com.alura.converter.enums.TemperatureUnit;
import com.alura.converter.util.CurrencyConverter;
import com.alura.converter.util.TemperatureConverter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

;

public class MainMenuController implements Initializable {

    @FXML
    private AnchorPane base_pane;

    @FXML
    private Pane alura_logo_pane;

    @FXML
    private ImageView alura_logo_image;

    @FXML
    private Pane background_pane;

    @FXML
    private Pane currency_pane;

    @FXML
    private Label currency_view_title;

    @FXML
    private JFXButton currency_convert_button;

    @FXML
    private JFXTextField currency_amount_textfield;

    @FXML
    private Label currency_amount_label;

    @FXML
    private ComboBox<CurrencyUnit> currency_from_combobox;

    @FXML
    private ComboBox<CurrencyUnit> currency_to_combobox;

    @FXML
    private ImageView swap_currencies_image;

    @FXML
    private Label currency_convertion_result_label;

    @FXML
    private Pane temperature_pane;

    @FXML
    private Label temperature_view_title;

    @FXML
    private JFXButton temperature_convert_button;

    @FXML
    private Label temperature_amount_label;

    @FXML
    private JFXTextField temperature_amount_textfield;

    @FXML
    private ComboBox<TemperatureUnit> temperature_from_combobox;

    @FXML
    private ComboBox<TemperatureUnit> temperature_to_combobox;

    @FXML
    private ImageView swap_temperatures_image;

    @FXML
    private Label temperature_convertion_result_label;

    @FXML
    private Label main_view_title;

    @FXML
    private JFXButton currency_button;

    @FXML
    private JFXButton temperature_button;

    @FXML
    private Label credits_title_label;

    @FXML
    private Label credits_subtitle_label;

    @FXML
    private Label currency_from_label;

    @FXML
    private Label currency_to_label;

    @FXML
    private Label temperature_from_label;

    @FXML
    private Label temperature_to_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCurrencyList();
        loadTemperatureUnitsList();
        addValidators();
        addHandlers();
    }

    private void loadCurrencyList() {
        List<CurrencyUnit> availableCurrencies = CurrencyUnit.getAllCurrencyUnits();

        ObservableList<CurrencyUnit> countryList = FXCollections.observableArrayList(availableCurrencies).sorted();

        currency_from_combobox.setItems(countryList);
        currency_to_combobox.setItems(countryList);

        setCurrencyCountryFlags(currency_from_combobox);
        setCurrencyCountryFlags(currency_to_combobox);
    }

    private void loadTemperatureUnitsList() {
        List<TemperatureUnit> availableTemperatureUnits = TemperatureUnit.getAllTemperatureUnits();
        ObservableList<TemperatureUnit> temperatureList = FXCollections.observableArrayList(availableTemperatureUnits).sorted();

        temperature_from_combobox.setItems(temperatureList);
        temperature_to_combobox.setItems(temperatureList);
    }

    private void addValidators() {
        // Validators for numeric values at user input
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        DoubleValidator doubleValidator = new DoubleValidator();
        doubleValidator.setMessage("Enter numeric value");

        currency_amount_textfield.getValidators().addAll(doubleValidator);
        temperature_amount_textfield.getValidators().addAll(doubleValidator);
    }

    private void addHandlers() {
        currency_convert_button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
            if (validateCurrencyInput()) {
                Platform.runLater(() -> {
                    double amount = Double.parseDouble(currency_amount_textfield.getText());
                    CurrencyConverter converter = CurrencyConverter.getInstance();
                    converter.setProperties(
                            amount, currency_from_combobox.getValue(), currency_to_combobox.getValue());
                    try {
                        String result = amount + " "
                                + currency_from_combobox.getValue().getUnitName()
                                + " = %.2f " + currency_to_combobox.getValue().getUnitName();
                        double convertedCurrency = converter.Convert();
                        currency_convertion_result_label.setText(String.format(result, convertedCurrency));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
            ;
        });

        temperature_convert_button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
            if (validateTemperatureInput()) {
                Platform.runLater(() -> {
                    Platform.runLater(() -> {
                        double amount = Double.parseDouble(temperature_amount_textfield.getText());
                        double convertedTemperature =
                                TemperatureConverter.convertTemperature(
                                        amount,
                                        temperature_from_combobox.getValue(),
                                        temperature_to_combobox.getValue()
                                );
                        String result = amount + " "
                                + temperature_from_combobox.getValue().getUnitName() + " = "
                                + " %.2f " + temperature_to_combobox.getValue().getUnitName();
                        temperature_convertion_result_label.setText(String.format(result, convertedTemperature));
                    });
                });
            }
            ;
        });
    }

    private boolean validateCurrencyInput() {
        return currency_from_combobox.getSelectionModel().getSelectedIndex() != -1
                && currency_to_combobox.getSelectionModel().getSelectedIndex() != -1
                && currency_amount_textfield.validate();
    }

    private boolean validateTemperatureInput() {
        return temperature_from_combobox.getSelectionModel().getSelectedIndex() != -1
                && temperature_to_combobox.getSelectionModel().getSelectedIndex() != -1
                && temperature_amount_textfield.validate();
    }

    @FXML
    private void handleUserInteraction(MouseEvent event) {
        EventTarget elementTarget = event.getTarget();
        showConverterPane(elementTarget);
    }

    private void showConverterPane(EventTarget menuButton) {
        if (menuButton == currency_button) {
            cleanAndShowCurrencyPane();
        } else {
            cleanAndShowTemperaturePane();
        }
    }

    private void cleanAndShowCurrencyPane() {
        temperature_pane.setVisible(false);
        currency_from_combobox.setValue(null);
        currency_to_combobox.setValue(null);
        currency_amount_textfield.setText("");
        currency_pane.setVisible(true);
        startTransition(currency_pane);
    }

    private void cleanAndShowTemperaturePane() {
        currency_pane.setVisible(false);

        temperature_from_combobox.setValue(null);
        temperature_to_combobox.setValue(null);
        temperature_amount_textfield.setText("");

        temperature_pane.setVisible(true);
        startTransition(temperature_pane);
    }

    private void startTransition(Pane pane) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    private void swapComboBoxSelections(MouseEvent event) {
        EventTarget swapButton = event.getTarget();
        if (swapButton == swap_currencies_image) {
            CurrencyUnit selectedFromCurrency = currency_from_combobox.getValue();
            CurrencyUnit selectedToCurrency = currency_to_combobox.getValue();

            if (selectedFromCurrency != null && selectedToCurrency != null) {
                currency_from_combobox.setValue(selectedToCurrency);
                currency_to_combobox.setValue(selectedFromCurrency);
            }

        } else {
            TemperatureUnit selectedFromTemperature = temperature_from_combobox.getValue();
            TemperatureUnit selectedToTemperature = temperature_to_combobox.getValue();

            if (selectedFromTemperature != null && selectedToTemperature != null) {
                temperature_from_combobox.setValue(selectedToTemperature);
                temperature_to_combobox.setValue(selectedFromTemperature);
            }
        }
    }

    private void setCurrencyCountryFlags(ComboBox<CurrencyUnit> comboBox) {
        comboBox.setCellFactory(new Callback<ListView<CurrencyUnit>, ListCell<CurrencyUnit>>() {
            @Override
            public ListCell<CurrencyUnit> call(ListView<CurrencyUnit> p) {
                return new ListCell<CurrencyUnit>() {
                    @Override
                    protected void updateItem(CurrencyUnit item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image countryFlag = new Image(
                                    Objects.requireNonNull(
                                                    ConverterApp
                                                            .class.getResource(
                                                            String.format("images/%s.png", item.getUnitName()
                                                            )
                                                    )
                                            )
                                            .toExternalForm());
                            ImageView flagViewContainer = new ImageView();
                            flagViewContainer.setFitHeight(50);
                            flagViewContainer.setFitWidth(50);
                            flagViewContainer.setImage(countryFlag);
                            Label countryCurrencyLabel = new Label(item.getUnitName());
                            countryCurrencyLabel.setGraphic(flagViewContainer);
                            setGraphic(countryCurrencyLabel);
                        }
                    }
                };
            }
        });
    }

}
