package com.example.barri.pruebas;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import DataBase.CustomDB;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Memory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Memory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Memory extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8, carta9, carta10, carta11, carta12, carta13, carta14, carta15, carta16, b_reiniciar;
    private TextView tv_puntuacion;
    private int ids[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private boolean primera = true;
    private int puntuacion = 0;
    int primera_carta = 0;
    int segunda_carta = 0;
    int primera_carta_handler = 0;
    int segunda_carta_handler = 0;
    private int background_primera = 0;
    private int background = 0;
    private int aciertos = 0;
    private String user;

    private CustomDB cdb;

    public Memory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Memory.
     */
    // TODO: Rename and change types and number of parameters
    public static Memory newInstance(String param1, String param2) {
        Memory fragment = new Memory();
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
        View view = inflater.inflate(R.layout.fragment_memory, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) user = bundle.getString("user");
        
        tv_puntuacion = (TextView) view.findViewById(R.id.tv_puntuacion);
        carta1 = (Button) view.findViewById(R.id.carta1);
        carta2 = (Button) view.findViewById(R.id.carta2);
        carta3 = (Button) view.findViewById(R.id.carta3);
        carta4 = (Button) view.findViewById(R.id.carta4);
        carta5 = (Button) view.findViewById(R.id.carta5);
        carta6 = (Button) view.findViewById(R.id.carta6);
        carta7 = (Button) view.findViewById(R.id.carta7);
        carta8 = (Button) view.findViewById(R.id.carta8);
        carta9 = (Button) view.findViewById(R.id.carta9);
        carta10 = (Button) view.findViewById(R.id.carta10);
        carta11 = (Button) view.findViewById(R.id.carta11);
        carta12 = (Button) view.findViewById(R.id.carta12);
        carta13 = (Button) view.findViewById(R.id.carta13);
        carta14 = (Button) view.findViewById(R.id.carta14);
        carta15 = (Button) view.findViewById(R.id.carta15);
        carta16 = (Button) view.findViewById(R.id.carta16);
        b_reiniciar = (Button) view.findViewById(R.id.b_reiniciar);

        shuffleArray(ids);

        b_reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primera_carta = 0;
                segunda_carta = 0;
                primera_carta_handler = 0;
                segunda_carta_handler = 0;
                background = 0;
                background_primera = 0;
                aciertos = 0;
                puntuacion = 0;
                primera = true;
                tv_puntuacion.setText(String.valueOf(puntuacion));
                shuffleArray(ids);

                carta1.setEnabled(true);
                carta2.setEnabled(true);
                carta3.setEnabled(true);
                carta4.setEnabled(true);
                carta5.setEnabled(true);
                carta6.setEnabled(true);
                carta7.setEnabled(true);
                carta8.setEnabled(true);
                carta9.setEnabled(true);
                carta10.setEnabled(true);
                carta11.setEnabled(true);
                carta12.setEnabled(true);
                carta13.setEnabled(true);
                carta14.setEnabled(true);
                carta15.setEnabled(true);
                carta16.setEnabled(true);

                carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                carta16.setBackgroundResource(R.drawable.magic_the_gathering);
            }
        });

        carta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 1 && segunda_carta != 1 && primera_carta_handler != 1 && segunda_carta_handler != 1) {
                    if (ids[0] == 1 || ids[0] == 2) {
                        background = 1;
                        carta1.setBackgroundResource(R.drawable.java);
                    } else if (ids[0] == 3 || ids[0] == 4) {
                        background = 2;
                        carta1.setBackgroundResource(R.drawable.android);
                    } else if (ids[0] == 5 || ids[0] == 6) {
                        background = 3;
                        carta1.setBackgroundResource(R.drawable.ios);
                    } else if (ids[0] == 7 || ids[0] == 8) {
                        background = 4;
                        carta1.setBackgroundResource(R.drawable.kde);
                    } else if (ids[0] == 9 || ids[0] == 10) {
                        background = 5;
                        carta1.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[0] == 11 || ids[0] == 12) {
                        background = 6;
                        carta1.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[0] == 13 || ids[0] == 14) {
                        background = 7;
                        carta1.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[0] == 15 || ids[0] == 16) {
                        background = 8;
                        carta1.setBackgroundResource(R.drawable.intel);
                    }

                    if (primera) {
                        primera = false;
                        primera_carta = 1;
                        background_primera = background;
                    } else {
                        segunda_carta = 1;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta1.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion) {
                                    cdb.setPuntuacion(user, puntuacion);
                                }
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);


                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta1.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 2 && segunda_carta != 2 && primera_carta_handler != 2 && segunda_carta_handler != 2) {

                    if (ids[1] == 1 || ids[1] == 2) {
                        background = 1;
                        carta2.setBackgroundResource(R.drawable.java);
                    } else if (ids[1] == 3 || ids[1] == 4) {
                        background = 2;
                        carta2.setBackgroundResource(R.drawable.android);
                    } else if (ids[1] == 5 || ids[1] == 6) {
                        background = 3;
                        carta2.setBackgroundResource(R.drawable.ios);
                    } else if (ids[1] == 7 || ids[1] == 8) {
                        background = 4;
                        carta2.setBackgroundResource(R.drawable.kde);
                    } else if (ids[1] == 9 || ids[1] == 10) {
                        background = 5;
                        carta2.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[1] == 11 || ids[1] == 12) {
                        background = 6;
                        carta2.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[1] == 13 || ids[1] == 14) {
                        background = 7;
                        carta2.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[1] == 15 || ids[1] == 16) {
                        background = 8;
                        carta2.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 2;
                        background_primera = background;
                    } else {
                        segunda_carta = 2;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta2.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);


                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta2.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 3 && segunda_carta != 3 && primera_carta_handler != 3 && segunda_carta_handler != 3) {

                    if (ids[2] == 1 || ids[2] == 2) {
                        background = 1;
                        carta3.setBackgroundResource(R.drawable.java);
                    } else if (ids[2] == 3 || ids[2] == 4) {
                        background = 2;
                        carta3.setBackgroundResource(R.drawable.android);
                    } else if (ids[2] == 5 || ids[2] == 6) {
                        background = 3;
                        carta3.setBackgroundResource(R.drawable.ios);
                    } else if (ids[2] == 7 || ids[2] == 8) {
                        background = 4;
                        carta3.setBackgroundResource(R.drawable.kde);
                    } else if (ids[2] == 9 || ids[2] == 10) {
                        background = 5;
                        carta3.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[2] == 11 || ids[2] == 12) {
                        background = 6;
                        carta3.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[2] == 13 || ids[2] == 14) {
                        background = 7;
                        carta3.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[2] == 15 || ids[2] == 16) {
                        background = 8;
                        carta3.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 3;
                        background_primera = background;
                    } else {
                        segunda_carta = 3;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta3.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta3.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 4 && segunda_carta != 4 && primera_carta_handler != 4 && segunda_carta_handler != 4) {

                    if (ids[3] == 1 || ids[3] == 2) {
                        background = 1;
                        carta4.setBackgroundResource(R.drawable.java);
                    } else if (ids[3] == 3 || ids[3] == 4) {
                        background = 2;
                        carta4.setBackgroundResource(R.drawable.android);
                    } else if (ids[3] == 5 || ids[3] == 6) {
                        background = 3;
                        carta4.setBackgroundResource(R.drawable.ios);
                    } else if (ids[3] == 7 || ids[3] == 8) {
                        background = 4;
                        carta4.setBackgroundResource(R.drawable.kde);
                    } else if (ids[3] == 9 || ids[3] == 10) {
                        background = 5;
                        carta4.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[3] == 11 || ids[3] == 12) {
                        background = 6;
                        carta4.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[3] == 13 || ids[3] == 14) {
                        background = 7;
                        carta4.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[3] == 15 || ids[3] == 16) {
                        background = 8;
                        carta4.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 4;
                        background_primera = background;
                    } else {
                        segunda_carta = 4;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta4.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta4.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 5 && segunda_carta != 5 && primera_carta_handler != 5 && segunda_carta_handler != 5) {

                    if (ids[4] == 1 || ids[4] == 2) {
                        background = 1;
                        carta5.setBackgroundResource(R.drawable.java);
                    } else if (ids[4] == 3 || ids[4] == 4) {
                        background = 2;
                        carta5.setBackgroundResource(R.drawable.android);
                    } else if (ids[4] == 5 || ids[4] == 6) {
                        background = 3;
                        carta5.setBackgroundResource(R.drawable.ios);
                    } else if (ids[4] == 7 || ids[4] == 8) {
                        background = 4;
                        carta5.setBackgroundResource(R.drawable.kde);
                    } else if (ids[4] == 9 || ids[4] == 10) {
                        background = 5;
                        carta5.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[4] == 11 || ids[4] == 12) {
                        background = 6;
                        carta5.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[4] == 13 || ids[4] == 14) {
                        background = 7;
                        carta5.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[4] == 15 || ids[4] == 16) {
                        background = 8;
                        carta5.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 5;
                        background_primera = background;
                    } else {
                        segunda_carta = 5;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta5.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta5.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 6 && segunda_carta != 6 && primera_carta_handler != 6 && segunda_carta_handler != 6) {

                    if (ids[5] == 1 || ids[5] == 2) {
                        background = 1;
                        carta6.setBackgroundResource(R.drawable.java);
                    } else if (ids[5] == 3 || ids[5] == 4) {
                        background = 2;
                        carta6.setBackgroundResource(R.drawable.android);
                    } else if (ids[5] == 5 || ids[5] == 6) {
                        background = 3;
                        carta6.setBackgroundResource(R.drawable.ios);
                    } else if (ids[5] == 7 || ids[5] == 8) {
                        background = 4;
                        carta6.setBackgroundResource(R.drawable.kde);
                    } else if (ids[5] == 9 || ids[5] == 10) {
                        background = 5;
                        carta6.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[5] == 11 || ids[5] == 12) {
                        background = 6;
                        carta6.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[5] == 13 || ids[5] == 14) {
                        background = 7;
                        carta6.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[5] == 15 || ids[5] == 16) {
                        background = 8;
                        carta6.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 6;
                        background_primera = background;
                    } else {
                        segunda_carta = 6;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta6.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta6.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 7 && segunda_carta != 7 && primera_carta_handler != 7 && segunda_carta_handler != 7) {

                    if (ids[6] == 1 || ids[6] == 2) {
                        background = 1;
                        carta7.setBackgroundResource(R.drawable.java);
                    } else if (ids[6] == 3 || ids[6] == 4) {
                        background = 2;
                        carta7.setBackgroundResource(R.drawable.android);
                    } else if (ids[6] == 5 || ids[6] == 6) {
                        background = 3;
                        carta7.setBackgroundResource(R.drawable.ios);
                    } else if (ids[6] == 7 || ids[6] == 8) {
                        background = 4;
                        carta7.setBackgroundResource(R.drawable.kde);
                    } else if (ids[6] == 9 || ids[6] == 10) {
                        background = 5;
                        carta7.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[6] == 11 || ids[6] == 12) {
                        background = 6;
                        carta7.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[6] == 13 || ids[6] == 14) {
                        background = 7;
                        carta7.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[6] == 15 || ids[6] == 16) {
                        background = 8;
                        carta7.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 7;
                        background_primera = background;
                    } else {
                        segunda_carta = 7;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta7.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta7.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 8 && segunda_carta != 8 && primera_carta_handler != 8 && segunda_carta_handler != 8) {

                    if (ids[7] == 1 || ids[7] == 2) {
                        background = 1;
                        carta8.setBackgroundResource(R.drawable.java);
                    } else if (ids[7] == 3 || ids[7] == 4) {
                        background = 2;
                        carta8.setBackgroundResource(R.drawable.android);
                    } else if (ids[7] == 5 || ids[7] == 6) {
                        background = 3;
                        carta8.setBackgroundResource(R.drawable.ios);
                    } else if (ids[7] == 7 || ids[7] == 8) {
                        background = 4;
                        carta8.setBackgroundResource(R.drawable.kde);
                    } else if (ids[7] == 9 || ids[7] == 10) {
                        background = 5;
                        carta8.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[7] == 11 || ids[7] == 12) {
                        background = 6;
                        carta8.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[7] == 13 || ids[7] == 14) {
                        background = 7;
                        carta8.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[7] == 15 || ids[7] == 16) {
                        background = 8;
                        carta8.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 8;
                        background_primera = background;
                    } else {
                        segunda_carta = 8;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta8.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta8.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 9 && segunda_carta != 9 && primera_carta_handler != 10 && segunda_carta_handler != 10) {

                    if (ids[8] == 1 || ids[8] == 2) {
                        background = 1;
                        carta9.setBackgroundResource(R.drawable.java);
                    } else if (ids[8] == 3 || ids[8] == 4) {
                        background = 2;
                        carta9.setBackgroundResource(R.drawable.android);
                    } else if (ids[8] == 5 || ids[8] == 6) {
                        background = 3;
                        carta9.setBackgroundResource(R.drawable.ios);
                    } else if (ids[8] == 7 || ids[8] == 8) {
                        background = 4;
                        carta9.setBackgroundResource(R.drawable.kde);
                    } else if (ids[8] == 9 || ids[8] == 10) {
                        background = 5;
                        carta9.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[8] == 11 || ids[8] == 12) {
                        background = 6;
                        carta9.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[8] == 13 || ids[8] == 14) {
                        background = 7;
                        carta9.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[8] == 15 || ids[8] == 16) {
                        background = 8;
                        carta9.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 9;
                        background_primera = background;
                    } else {
                        segunda_carta = 9;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta9.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta9.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 10 && segunda_carta != 10 && primera_carta_handler != 10 && segunda_carta_handler != 10) {

                    if (ids[9] == 1 || ids[9] == 2) {
                        background = 1;
                        carta10.setBackgroundResource(R.drawable.java);
                    } else if (ids[9] == 3 || ids[9] == 4) {
                        background = 2;
                        carta10.setBackgroundResource(R.drawable.android);
                    } else if (ids[9] == 5 || ids[9] == 6) {
                        background = 3;
                        carta10.setBackgroundResource(R.drawable.ios);
                    } else if (ids[9] == 7 || ids[9] == 8) {
                        background = 4;
                        carta10.setBackgroundResource(R.drawable.kde);
                    } else if (ids[9] == 9 || ids[9] == 10) {
                        background = 5;
                        carta10.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[9] == 11 || ids[9] == 12) {
                        background = 6;
                        carta10.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[9] == 13 || ids[9] == 14) {
                        background = 7;
                        carta10.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[9] == 15 || ids[9] == 16) {
                        background = 8;
                        carta10.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 10;
                        background_primera = background;
                    } else {
                        segunda_carta = 10;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta10.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta10.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 11 && segunda_carta != 11 && primera_carta_handler != 11 && segunda_carta_handler != 11) {

                    if (ids[10] == 1 || ids[10] == 2) {
                        background = 1;
                        carta11.setBackgroundResource(R.drawable.java);
                    } else if (ids[10] == 3 || ids[10] == 4) {
                        background = 2;
                        carta11.setBackgroundResource(R.drawable.android);
                    } else if (ids[10] == 5 || ids[10] == 6) {
                        background = 3;
                        carta11.setBackgroundResource(R.drawable.ios);
                    } else if (ids[10] == 7 || ids[10] == 8) {
                        background = 4;
                        carta11.setBackgroundResource(R.drawable.kde);
                    } else if (ids[10] == 9 || ids[10] == 10) {
                        background = 5;
                        carta11.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[10] == 11 || ids[10] == 12) {
                        background = 6;
                        carta11.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[10] == 13 || ids[10] == 14) {
                        background = 7;
                        carta11.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[10] == 15 || ids[10] == 16) {
                        background = 8;
                        carta11.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 11;
                        background_primera = background;
                    } else {
                        segunda_carta = 11;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta11.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta11.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 12 && segunda_carta != 12 && primera_carta_handler != 12 && segunda_carta_handler != 12) {

                    if (ids[11] == 1 || ids[11] == 2) {
                        background = 1;
                        carta12.setBackgroundResource(R.drawable.java);
                    } else if (ids[11] == 3 || ids[11] == 4) {
                        background = 2;
                        carta12.setBackgroundResource(R.drawable.android);
                    } else if (ids[11] == 5 || ids[11] == 6) {
                        background = 3;
                        carta12.setBackgroundResource(R.drawable.ios);
                    } else if (ids[11] == 7 || ids[11] == 8) {
                        background = 4;
                        carta12.setBackgroundResource(R.drawable.kde);
                    } else if (ids[11] == 9 || ids[11] == 10) {
                        background = 5;
                        carta12.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[11] == 11 || ids[11] == 12) {
                        background = 6;
                        carta12.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[11] == 13 || ids[11] == 14) {
                        background = 7;
                        carta12.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[11] == 15 || ids[11] == 16) {
                        background = 8;
                        carta12.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 12;
                        background_primera = background;
                    } else {
                        segunda_carta = 12;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta12.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta12.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 13 && segunda_carta != 13 && primera_carta_handler != 13 && segunda_carta_handler != 13) {

                    if (ids[12] == 1 || ids[12] == 2) {
                        background = 1;
                        carta13.setBackgroundResource(R.drawable.java);
                    } else if (ids[12] == 3 || ids[12] == 4) {
                        background = 2;
                        carta13.setBackgroundResource(R.drawable.android);
                    } else if (ids[12] == 5 || ids[12] == 6) {
                        background = 3;
                        carta13.setBackgroundResource(R.drawable.ios);
                    } else if (ids[12] == 7 || ids[12] == 8) {
                        background = 4;
                        carta13.setBackgroundResource(R.drawable.kde);
                    } else if (ids[12] == 9 || ids[12] == 10) {
                        background = 5;
                        carta13.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[12] == 11 || ids[12] == 12) {
                        background = 6;
                        carta13.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[12] == 13 || ids[12] == 14) {
                        background = 7;
                        carta13.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[12] == 15 || ids[12] == 16) {
                        background = 8;
                        carta13.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 13;
                        background_primera = background;
                    } else {
                        segunda_carta = 13;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta13.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta13.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 14 && segunda_carta != 14 && primera_carta_handler != 14 && segunda_carta_handler != 14) {

                    if (ids[13] == 1 || ids[13] == 2) {
                        background = 1;
                        carta14.setBackgroundResource(R.drawable.java);
                    } else if (ids[13] == 3 || ids[13] == 4) {
                        background = 2;
                        carta14.setBackgroundResource(R.drawable.android);
                    } else if (ids[13] == 5 || ids[13] == 6) {
                        background = 3;
                        carta14.setBackgroundResource(R.drawable.ios);
                    } else if (ids[13] == 7 || ids[13] == 8) {
                        background = 4;
                        carta14.setBackgroundResource(R.drawable.kde);
                    } else if (ids[13] == 9 || ids[13] == 10) {
                        background = 5;
                        carta14.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[13] == 11 || ids[13] == 12) {
                        background = 6;
                        carta14.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[13] == 13 || ids[13] == 14) {
                        background = 7;
                        carta14.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[13] == 15 || ids[13] == 16) {
                        background = 8;
                        carta14.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 14;
                        background_primera = background;
                    } else {
                        segunda_carta = 14;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta14.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta14.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 15 && segunda_carta != 15 && primera_carta_handler != 15 && segunda_carta_handler != 15) {

                    if (ids[14] == 1 || ids[14] == 2) {
                        background = 1;
                        carta15.setBackgroundResource(R.drawable.java);
                    } else if (ids[14] == 3 || ids[14] == 4) {
                        background = 2;
                        carta15.setBackgroundResource(R.drawable.android);
                    } else if (ids[14] == 5 || ids[14] == 6) {
                        background = 3;
                        carta15.setBackgroundResource(R.drawable.ios);
                    } else if (ids[14] == 7 || ids[14] == 8) {
                        background = 4;
                        carta15.setBackgroundResource(R.drawable.kde);
                    } else if (ids[14] == 9 || ids[14] == 10) {
                        background = 5;
                        carta15.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[14] == 11 || ids[14] == 12) {
                        background = 6;
                        carta15.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[14] == 13 || ids[14] == 14) {
                        background = 7;
                        carta15.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[14] == 15 || ids[14] == 16) {
                        background = 8;
                        carta15.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 15;
                        background_primera = background;
                    } else {
                        segunda_carta = 15;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 16)
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta15.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 16)
                                    carta16.setEnabled(false);

                                primera_carta = 0;
                                carta15.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });

        carta16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primera_carta != 16 && segunda_carta != 16 && primera_carta_handler != 16 && segunda_carta_handler != 16) {

                    if (ids[15] == 1 || ids[15] == 2) {
                        background = 1;
                        carta16.setBackgroundResource(R.drawable.java);
                    } else if (ids[15] == 3 || ids[15] == 4) {
                        background = 2;
                        carta16.setBackgroundResource(R.drawable.android);
                    } else if (ids[15] == 5 || ids[15] == 6) {
                        background = 3;
                        carta16.setBackgroundResource(R.drawable.ios);
                    } else if (ids[15] == 7 || ids[15] == 8) {
                        background = 4;
                        carta16.setBackgroundResource(R.drawable.kde);
                    } else if (ids[15] == 9 || ids[15] == 10) {
                        background = 5;
                        carta16.setBackgroundResource(R.drawable.spotify);
                    } else if (ids[15] == 11 || ids[15] == 12) {
                        background = 6;
                        carta16.setBackgroundResource(R.drawable.netscape);
                    } else if (ids[15] == 13 || ids[15] == 14) {
                        background = 7;
                        carta16.setBackgroundResource(R.drawable.nokia);
                    } else if (ids[15] == 15 || ids[15] == 16) {
                        background = 8;
                        carta16.setBackgroundResource(R.drawable.intel);
                    }
                    if (primera) {
                        primera = false;
                        primera_carta = 16;
                        background_primera = background;
                    } else {
                        segunda_carta = 16;
                        if (background_primera != background) {
                            primera_carta_handler = primera_carta;
                            segunda_carta_handler = segunda_carta;
                            primera_carta = 0;
                            segunda_carta = 0;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (primera_carta_handler == 1)
                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 2)
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 3)
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 4)
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 5)
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 6)
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 7)
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 8)
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 9)
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 10)
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 11)
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 12)
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 13)
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 14)
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                    else if (primera_carta_handler == 15)
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);

                                    primera_carta_handler = 0;

                                    carta16.setBackgroundResource(R.drawable.magic_the_gathering);

                                    segunda_carta_handler = 0;
                                }
                            }, 1000);
                        } else {
                            aciertos++;
                            if (aciertos == 8) {
                                puntuacion++;
                                tv_puntuacion.setText(String.valueOf(puntuacion));

                                cdb = new CustomDB(getContext());
                                if (cdb.getPuntuacion(user) > puntuacion)
                                    cdb.setPuntuacion(user, puntuacion);
                                cdb.close();

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Memory completado");
                                builder.setMessage("Has completado el memory. Quieres reiniciar el juego?");

                                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        primera_carta = 0;
                                        segunda_carta = 0;
                                        background = 0;
                                        background_primera = 0;
                                        puntuacion = 0;
                                        tv_puntuacion.setText(String.valueOf(puntuacion));
                                        shuffleArray(ids);

                                        carta1.setEnabled(true);
                                        carta2.setEnabled(true);
                                        carta3.setEnabled(true);
                                        carta4.setEnabled(true);
                                        carta5.setEnabled(true);
                                        carta6.setEnabled(true);
                                        carta7.setEnabled(true);
                                        carta8.setEnabled(true);
                                        carta9.setEnabled(true);
                                        carta10.setEnabled(true);
                                        carta11.setEnabled(true);
                                        carta12.setEnabled(true);
                                        carta13.setEnabled(true);
                                        carta14.setEnabled(true);
                                        carta15.setEnabled(true);
                                        carta16.setEnabled(true);

                                        carta1.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta2.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta3.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta4.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta5.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta6.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta7.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta8.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta9.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta10.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta11.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta12.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta13.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta14.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta15.setBackgroundResource(R.drawable.magic_the_gathering);
                                        carta16.setBackgroundResource(R.drawable.magic_the_gathering);
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        carta1.setEnabled(false);
                                        carta2.setEnabled(false);
                                        carta3.setEnabled(false);
                                        carta4.setEnabled(false);
                                        carta5.setEnabled(false);
                                        carta6.setEnabled(false);
                                        carta7.setEnabled(false);
                                        carta8.setEnabled(false);
                                        carta9.setEnabled(false);
                                        carta10.setEnabled(false);
                                        carta11.setEnabled(false);
                                        carta12.setEnabled(false);
                                        carta13.setEnabled(false);
                                        carta14.setEnabled(false);
                                        carta15.setEnabled(false);
                                        carta16.setEnabled(false);

                                        cdb = new CustomDB(getContext());
                                        cdb.setNotificacion(user, "Para reiniciar el juego selecciona la opcion en el menu");
                                        cdb.close();
                                        Toast.makeText(getContext(), "Para reiniciar el juego selecciona la opcion en el menu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else {
                                if (primera_carta == 1)
                                    carta1.setEnabled(false);
                                else if (primera_carta == 2)
                                    carta2.setEnabled(false);
                                else if (primera_carta == 3)
                                    carta3.setEnabled(false);
                                else if (primera_carta == 4)
                                    carta4.setEnabled(false);
                                else if (primera_carta == 5)
                                    carta5.setEnabled(false);
                                else if (primera_carta == 6)
                                    carta6.setEnabled(false);
                                else if (primera_carta == 7)
                                    carta7.setEnabled(false);
                                else if (primera_carta == 8)
                                    carta8.setEnabled(false);
                                else if (primera_carta == 9)
                                    carta9.setEnabled(false);
                                else if (primera_carta == 10)
                                    carta10.setEnabled(false);
                                else if (primera_carta == 11)
                                    carta11.setEnabled(false);
                                else if (primera_carta == 12)
                                    carta12.setEnabled(false);
                                else if (primera_carta == 13)
                                    carta13.setEnabled(false);
                                else if (primera_carta == 14)
                                    carta14.setEnabled(false);
                                else if (primera_carta == 15)
                                    carta15.setEnabled(false);

                                primera_carta = 0;
                                carta16.setEnabled(false);
                            }
                        }
                        if (aciertos != 8) {
                            puntuacion++;
                            tv_puntuacion.setText(String.valueOf(puntuacion));
                        } else {
                            puntuacion = 0;
                            aciertos = 0;
                        }
                        primera = true;
                    }
                }
            }
        });


        return view;
    }

    static void shuffleArray(int[] array)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
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
