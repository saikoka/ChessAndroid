package com.example.chessandroid72;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import static com.example.chessandroid72.MainActivity.getPromotion;

public class PromotionDialog extends AppCompatDialogFragment {
    private ListView listView;
    private String[] promotionNames;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Promotion Piece");
        promotionNames = getResources().getStringArray(R.array.promotion_array);
        builder.setItems(promotionNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        getPromotion("Queen");
                        break;
                    case 1:
                        getPromotion("Knight");
                        break;
                    case 2:
                        getPromotion("Bishop");
                        break;
                    case 3:
                        getPromotion("Rook");
                        break;
                }
            }
        });
        return builder.create();
    }
}
