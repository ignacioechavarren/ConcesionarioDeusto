package gui;



import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Coche;




public class CocheTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<String> headers = Arrays.asList( "Nombre", "Tipo", "Precio", "Id");
	private List<Coche> coches;

	public CocheTableModel(List<Coche> coches) {
		this.coches = coches;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override
	public int getRowCount() {
		return coches.size();
	}

	@Override
	public int getColumnCount() {
		return headers.size(); 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Coche coche = coches.get(rowIndex);
		switch (columnIndex) {
			case 0: return coche.getModelo();
			case 1: return coche.getAnyo();
			case 2: return coche.getMarca();
			case 3: return coche.getMatricula();
			case 4: return coche.getPrecio();
			default: return null;
		}
	}

}
