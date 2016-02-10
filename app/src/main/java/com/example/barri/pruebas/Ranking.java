package com.example.barri.pruebas;

import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import DataBase.CustomDB;
import DataBase.Usuario;
import Recycler.MyCustomAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Ranking.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Ranking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ranking extends Fragment {

    private CustomDB cdb;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    private Button b_reiniciar1;
    private String user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Ranking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ranking.
     */
    // TODO: Rename and change types and number of parameters
    public static Ranking newInstance(String param1, String param2) {
        Ranking fragment = new Ranking();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        b_reiniciar1 = (Button) view.findViewById(R.id.b_reiniciar1);

        Bundle bundle = this.getArguments();
        if (bundle != null) user = bundle.getString("user");

        //findViewById del layout activity_main
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(view.getContext());

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);

        b_reiniciar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdb = new CustomDB(v.getContext());
                cdb.resetPuntuacion();
                cdb.setNotificacion(user, "Para ver los cambios ves a otra activity y vuelve al Ranking");
                cdb.close();
                Toast.makeText(getContext(), "Para ver los cambios ves a otra activity y vuelve al Ranking", Toast.LENGTH_LONG).show();
            }
        });

        cdb = new CustomDB(view.getContext());
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Cursor c = cdb.getUsers();

        if (c.moveToFirst()) {
            do {
                usuarios.add(new Usuario(c.getString(c.getColumnIndex("user")), c.getInt(c.getColumnIndex("puntuacion"))));
            } while (c.moveToNext());
        }
        c.close();
        cdb.close();

        //El adapter se encarga de  adaptar un objeto definido en el codigo a una vista en xml
        //segun la estructura definida.
        //Asignamos nuestro custom Adapter
        mRecyclerView.setAdapter(new MyCustomAdapter(usuarios));


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
