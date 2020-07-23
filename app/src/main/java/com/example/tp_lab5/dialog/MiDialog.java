package com.example.tp_lab5.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tp_lab5.R;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MiDialog  extends DialogFragment {

    private String titulo;
    private String mensaje;

    public MiDialog(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder b = new AlertDialog.Builder(
                super.getActivity()
        );
        b.setTitle(this.titulo);
        b.setMessage(this.mensaje);

        LayoutInflater l = LayoutInflater.from(getActivity());
        View v = l.inflate(R.layout.dialog, null);
        b.setView(v);

        MiClickDialogListener cd = new MiClickDialogListener();
        b.setPositiveButton("OK", cd);
        return b.create();
    }
}