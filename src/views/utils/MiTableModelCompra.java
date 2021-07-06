package views.utils;
import models.documento.ItemOrdenCompra;
import models.proveedor.Item;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class MiTableModelCompra extends AbstractTableModel {
    private List<Item> lista = new ArrayList<Item>();
    protected String[] columnNames = new String[] {"Id","Articulo ", "Cantidad","Precio"};
    protected Class[] columnClasses = new Class[] {Integer.class,String.class, Float.class, Float.class };



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


    public void removeRowAt(int row) {
        if (lista.size() > 0) {
            lista.remove(row);


        }
        fireTableRowsDeleted(row , lista.size() );
    }


}
