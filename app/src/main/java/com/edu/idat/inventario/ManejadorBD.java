package com.edu.idat.inventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class ManejadorBD extends SQLiteOpenHelper {

    public ManejadorBD(Context context) {
        super(context, Configuracion.BD_NOMBRE, null, Configuracion.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sentencia = "CREATE TABLE " +
                Configuracion.TABLA_NOMBRE +
                " (" +
                Configuracion.CAMPO_ID + " integer primary key," +
                Configuracion.CAMPO_NOMBRE + " text," +
                Configuracion.CAMPO_DESCRIPCION + " text, " +
                Configuracion.CAMPO_CANTIDAD + " integer" +
                ")";
        sqLiteDatabase.execSQL(sentencia);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionInicial, int i1) {

    }


    public void agregarProducto(Producto producto) {
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues contenido = new ContentValues();

        contenido.put(Configuracion.CAMPO_NOMBRE, producto.getNombre());
        contenido.put(Configuracion.CAMPO_DESCRIPCION, producto.getDescripcion());
        contenido.put(Configuracion.CAMPO_CANTIDAD, producto.getCantidad());

        bd.insert(Configuracion.TABLA_NOMBRE, null, contenido);
        bd.close();


    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();

        String sentencia = "SELECT " +
                Configuracion.CAMPO_ID + "," +
                Configuracion.CAMPO_NOMBRE + "," +
                Configuracion.CAMPO_DESCRIPCION + "," +
                Configuracion.CAMPO_CANTIDAD +
                " FROM " +
                Configuracion.TABLA_NOMBRE;

        SQLiteDatabase bd = getReadableDatabase();

        Cursor cursor = bd.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {

            do {
                String nombre, descripcion;
                int id, cantidad;

                //Obtener los valores de los campos
                //Se cuenta a partir de la columna 0
                id = cursor.getInt(0);
                nombre = cursor.getString(1);
                descripcion = cursor.getString(2);
                cantidad = cursor.getInt(3);

                //Crear una instancia de la clase Producto con los valores capturados
                Producto producto = new Producto(id, nombre, descripcion, cantidad);

                //Agregar el nuevo Producto al arreglo de Producto
                productos.add(producto);
            } while (cursor.moveToNext());
        }
        return productos;


    }

    public void modificarProducto(Producto producto) {
        int id = producto.getId();
        int cantidad = producto.getCantidad();
        String nombre = producto.getNombre();
        String descripcion = producto.getDescripcion();

        String sentencia = "UPDATE " +
                Configuracion.TABLA_NOMBRE +
                " SET " +
                Configuracion.CAMPO_NOMBRE + "='" + nombre + "', " +
                Configuracion.CAMPO_DESCRIPCION + "='" + descripcion + "', " +
                Configuracion.CAMPO_CANTIDAD + "=" + cantidad + " " +
                "WHERE " +
                Configuracion.CAMPO_ID + "="  + id;

        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL(sentencia);

    }
}
