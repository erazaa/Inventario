package com.edu.idat.inventario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btNuevo;
    ListView lvProductos;
    ArrayList<Producto> productos;
    ProductoAdapter adapterProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProductos = (ListView) findViewById(R.id.lvProductos);
        lvProductos.setOnItemClickListener(this);

        btNuevo = (Button) findViewById(R.id.btNuevo);
        btNuevo.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        obtenerProductos();
        adapterProducto = new ProductoAdapter(productos, this);
        lvProductos.setAdapter(adapterProducto);
    }

    private void obtenerProductos() {
        //Obtener los productos de la BD

        ManejadorBD manejadorBD = new ManejadorBD(this);
        productos = manejadorBD.obtenerProductos();
    }

    @Override
    public void onClick(View view) {
        Configuracion.NUEVO_PRODUCTO = true;
        Intent intent = new Intent(this, ProductoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        Configuracion.NUEVO_PRODUCTO = false;
        Intent intent = new Intent(this, ProductoActivity.class);
        intent.putExtra("ID", productos.get(posicion).getId());
        intent.putExtra("Nombre", productos.get(posicion).getNombre());
        intent.putExtra("Descripcion", productos.get(posicion).getDescripcion());
        intent.putExtra("Cantidad", productos.get(posicion).getCantidad());

        startActivity(intent);

    }
}
