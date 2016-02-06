package Recycler;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barri.pruebas.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import DataBase.CustomDB;
import DataBase.Usuario;

/**
 * Created by barri on 3/2/16.
 */
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder> {

    ArrayList<Usuario> usuarios;

    public MyCustomAdapter(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewholder, int position) {
        adapterViewholder.posicion.setText(String.valueOf(position+1)+". ");
        adapterViewholder.user.setText(usuarios.get(position).getUser());
        if (usuarios.get(position).getPuntuacion() == 999) adapterViewholder.puntuacion.setText("Sin puntuacion");
        else adapterViewholder.puntuacion.setText(String.valueOf(usuarios.get(position).getPuntuacion()));

    }

    @Override
    public int getItemCount() {
        //Debemos retornar el tamaño de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrará nada.
        return usuarios.size();
    }



    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */

        public TextView posicion;
        public TextView user;
        public TextView puntuacion;

        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.posicion = (TextView) itemView.findViewById(R.id.posicion);
            this.user = (TextView) itemView.findViewById(R.id.user);
            this.puntuacion = (TextView) itemView.findViewById(R.id.puntuacion);
        }
    }
}
