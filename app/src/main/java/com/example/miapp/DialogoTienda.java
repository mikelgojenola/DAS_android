package com.example.miapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoTienda extends DialogFragment {
    ListenerdelDialogo miListener;
    public interface ListenerdelDialogo {
        void alpulsarSI();
        void alpulsarNO();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        miListener =(ListenerdelDialogo) getActivity();
        builder.setTitle("Confirmación");
        builder.setMessage("¿Deseas comprar este campeón?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                miListener.alpulsarSI();
                dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                miListener.alpulsarNO();
                dismiss();
            }
        });

        return builder.create();
    }

    public void setListener(ListenerdelDialogo listener) {
        this.miListener = listener;
    }
}
