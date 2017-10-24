package com.edu.idat.inventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class ProductoAdapter extends BaseAdapter {

    ArrayList<Producto> productos;
    Context contexto;
    LayoutInflater layoutInflater;

    public ProductoAdapter(ArrayList<Producto> productos, Context contexto) {
        this.productos = productos;
        this.contexto = contexto;
        layoutInflater = (LayoutInflater)
                contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Producto getItem(int posicion) {
        return productos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View vista = layoutInflater.inflate(R.layout.item_main, null);
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.tvNombre = vista.findViewById(R.id.tvNombre);
        viewHolder.tvDescripcion = vista.findViewById(R.id.tvDescripcion);
        viewHolder.tvCantidad = vista.findViewById(R.id.tvCantidad);

        String nombre = productos.get(posicion).getNombre();
        String descripcion = productos.get(posicion).getDescripcion();
        int cantidad = productos.get(posicion).getCantidad();

        viewHolder.tvNombre.setText(nombre);
        viewHolder.tvDescripcion.setText(descripcion);
        viewHolder.tvCantidad.setText(String.valueOf(cantidad));

        return vista;
    }

    public static class ViewHolder {
        TextView tvNombre, tvDescripcion, tvCantidad;
    }


}
