package com.edu.idat.inventario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProductoActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etDescripcion, etCantidad;
    Button btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etCantidad = (EditText) findViewById(R.id.etCantidad);

        btGuardar = (Button) findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(this);

        if (!Configuracion.NUEVO_PRODUCTO){


            String nombre = getIntent().getStringExtra("Nombre");
            etNombre.setText(nombre);

            String descripcion = getIntent().getStringExtra("Descripcion");
            etDescripcion.setText(descripcion);

            int cantidad = getIntent().getIntExtra("Cantidad",0);
            etCantidad.setText(String.valueOf(cantidad));

        }
    }

    @Override
    public void onClick(View view) {
        // Crear variables que permitan capturar los textos de los EditText
        String nombre, descripcion;
        int id,cantidad;

        //Obtener los textos de los EditText
        //Considerar aquellos casos en los cuales la variable no es String.

        nombre = etNombre.getText().toString();
        descripcion = etDescripcion.getText().toString();
        cantidad = Integer.valueOf(etCantidad.getText().toString());

        //Crear nueva instancia de la clase Producto
        Producto producto = new Producto(nombre, descripcion, cantidad);

        //Crear nueva instancia del manejador de bd
        ManejadorBD manejadorBD = new ManejadorBD(this);

        if (!Configuracion.NUEVO_PRODUCTO){
            producto.setId(getIntent().getIntExtra("ID",0));
            manejadorBD.modificarProducto(producto);
        }
        else {
            manejadorBD.agregarProducto(producto);
        }
        finish();
    }
}