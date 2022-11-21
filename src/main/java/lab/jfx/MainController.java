package lab.jfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;


public class MainController {
    @FXML
    private TextField txtField;
    boolean czysc = false;
    DecimalFormat df = new DecimalFormat("#.#######");

    public MainController() {

    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void onActionBtnDigit(ActionEvent event) {
        Button btn = (Button) event.getSource();
        setSign(btn.getText());
    }

    private void setSign(String cyfra) {
        if(czysc) {
            txtField.setText(cyfra);
            czysc = false;
        }
        else {
            txtField.appendText(cyfra);
        }
    }

    public void onActionBtnClear(ActionEvent event) {
        txtField.setText("");
    }

    public void onActionBtnComma(ActionEvent event) {
        String[] elements = txtField.getText().split("[\\+\\-\\*\\/\u0025]");
        if(elements[elements.length - 1].contains(",")) {
            return;
        }
        setSign(",");
    }

    public void onActionBtnBasicOperand(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String text = btn.getText();
        if (txtField.getText().length() > 0 &&
                !txtField.getText().endsWith("-") &&
                !txtField.getText().endsWith("+") &&
                !txtField.getText().endsWith("*") &&
                !txtField.getText().endsWith("/")) {
            setSign(text);
        }
    }

    public void onActionBtnEvaluate(ActionEvent event) {
        double result = Operations.eval(txtField.getText());
        txtField.setText(df.format(result));
        czysc = true;
    }

    public void onActionBtnReverse(ActionEvent event) {
        double result = Operations.reverse(txtField.getText());
        txtField.setText(df.format(result));
        czysc = true;
    }

    public void onActionBtnSqrt(ActionEvent event) {
        double result = Operations.sqrt(txtField.getText());
        txtField.setText(df.format(result));
        czysc = true;
    }

    public void onActionBtnLog(ActionEvent event) {
        double result = Operations.log(txtField.getText());
        txtField.setText(df.format(result));
        czysc = true;
    }
}