package realtime_event_ticketing_system.cli.util;

import realtime_event_ticketing_system.cli.models.TicketPool;
import realtime_event_ticketing_system.cli.models.Vendor;

import java.util.ArrayList;


public class TableFormatter {

    private String[] columns;
    private int[] columnWidths;
    private ArrayList<Object> dataset;


    public TableFormatter() {
    }

    public TableFormatter(String[] columns, int[] columnWidths, ArrayList<Object> dataset) {
        this.columns = columns;
        this.columnWidths = columnWidths;
        this.dataset = dataset;
        createTable();
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public ArrayList<Object> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<Object> dataset) {
        this.dataset = dataset;
    }

    public int[] getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(int[] columnWidths) {
        this.columnWidths = columnWidths;
    }

    public void showTable() {
        createTable();
    }

    private void lineSeparator() {
        for (int width : columnWidths) {
            System.out.print("===");
            for (int j = 0; j < width; j++) {
                System.out.print("=");
            }
        }
        System.out.print("=\n|");
    }

    private void printVendorRow() {
        for (Object data : dataset) {
            Vendor vendor = (Vendor) data;

            System.out.println("| " + String.format("%0" + columnWidths[0] + "d", vendor.getId()) +
                    " | " + "");
        }
    }

    private void printTicketRow() {

    }

    private void createTable() {
        // Print the header line
        lineSeparator();

        // Print the column headers
        for (int i = 0; i < columns.length; i++) {
            System.out.printf(" %-" + columnWidths[i] + "s |", columns[i]);
        }
        System.out.println();

        // Print another line of separators below headers
        lineSeparator();

        if(!dataset.isEmpty()) {
            if (dataset.getFirst() instanceof TicketPool) {
                printTicketRow();

            } else if (dataset.getLast() instanceof Vendor) {
                printVendorRow();
            }
        }
    }
}
