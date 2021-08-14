package icc;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * Clase para fabricar el formato de celdas en una tabla.
 */
public class FabricaFormatoCelda<S, T>
    implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    /* La alineación de la celda. */
    private TextAlignment alineacion;
    /* El formato de la celda. */
    private String formato;

    /**
     * Regresa la alineación de la celda.
     * @return la alineación de la celda.
     */
    public TextAlignment getAlineacion() {
        return alineacion;
    }

    /**
     * Define la alineación de la celda.
     * @param alineacion la alineación de la celda.
     */
    public void setAlineacion(TextAlignment alineacion) {
        this.alineacion = alineacion;
    }

    /**
     * Regresa el formato de la celda. 
     * @return el formato de la celda.
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Define el formato de la celda. 
     * @param formato el formato de la celda.
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * Sobrecarga el método que define el texto en la celda dependiendo del
     * valor dentro de la misma.
     * @param p la columa de la celda.
     */
    @Override
    @SuppressWarnings("unchecked")
    public TableCell<S, T> call(TableColumn<S, T> p) {
        /* Clase interna anónima. */
        TableCell<S, T> cell = new TableCell<S, T>() {
                @Override
                public void updateItem(Object elemento, boolean vacio) {
                    if (elemento == getItem()) {
                        return;
                    }
                    super.updateItem((T) elemento, vacio);
                    if (elemento == null) {
                        super.setText(null);
                        super.setGraphic(null);
                    } else if (formato != null) {
                        /* Sólo formateamos enteros y dobles, y sólo si el
                         * formato está definido. */
                        if (elemento instanceof Integer ||
                            elemento instanceof Double)
                            super.setText(String.format("%"+formato, elemento));
                        else
                            super.setText(elemento.toString());
                    } else if (elemento instanceof Node) {
                        super.setText(null);
                        super.setGraphic((Node)elemento);
                    } else {
                        super.setText(elemento.toString());
                        super.setGraphic(null);
                    }
                }
            };
        /* Alineamos según la alineación. */
        cell.setTextAlignment(alineacion);
        switch (alineacion) {
        case CENTER:
            cell.setAlignment(Pos.CENTER);
            break;
        case RIGHT:
            cell.setAlignment(Pos.CENTER_RIGHT);
            break;
        default:
            cell.setAlignment(Pos.CENTER_LEFT);
            break;
        }
        return cell;
    }
}
