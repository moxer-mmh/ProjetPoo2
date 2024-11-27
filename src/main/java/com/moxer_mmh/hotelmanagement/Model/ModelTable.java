package com.moxer_mmh.hotelmanagement.Model;
import javax.swing.table.DefaultTableModel;

public class ModelTable extends DefaultTableModel {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ModelTable(Object[][] data, Object[] columnNames) {
	        super(data, columnNames);
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false; // Make all cells non-editable
	    }
	}