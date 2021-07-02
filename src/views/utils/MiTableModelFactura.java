package views.utils;
import models.proveedor.Item;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MiTableModelFactura extends AbstractTableModel {
    protected String[] columnNames = new String[] {"Id","Articulo ", "Cantidad","Precio"};
    protected Class[] columnClasses = new Class[] {Integer.class,String.class, Float.class, Float.class };
    private List<Item> lista = new ArrayList<Item>();

    public String getColumName(int col) { return columnNames[col];}
    public Class getColumnClass(int col) {return columnClasses[col];}
    @Override
    public int getRowCount(){
        return lista.size();
    }
    @Override
    public int getColumnCount(){
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)

        {
            case 0: return lista.get(rowIndex).getID();
            case 1: return lista.get(rowIndex).getNombre();
            case 2: return lista.get(rowIndex).getCantidad();
            case 3: return lista.get(rowIndex).getPrecio();

            default: return null;
        }
    }

    public int add(Integer id,String nombre, Integer cantidad, Double precio){
        lista.add(new Item(id,nombre,cantidad,precio));
        return lista.size();
    }
}
