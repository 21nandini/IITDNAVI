package com.example.assistech.iitdnavi;

/**
 * Created by moon on 27/7/17.
 */


import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;



import java.util.List;

/**
 * Created by moon on 26/7/17.
 */

public class ImageLabelAdapter extends RecyclerViewCursorAdapter<ImageLabelAdapter.ViewHolder>{


    public ImageLabelAdapter(Cursor cursor){
        super(null);

        swapCursor(cursor);
    }

    @Override
    public ImageLabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView cardView =  (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new ViewHolder(cardView);

    }


    /*--------------Setting values to all views---------------------------*/
    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        CardView cardView=holder.cardView;
        String description =cursor.getString(1);
        byte []image= cursor.getBlob(2);
        Bitmap bitmap= DbBitmapUtility.getImage(image);

        ImageView imageView= (ImageView) cardView.findViewById((R.id.info_image));


        imageView.setImageBitmap(bitmap);



        TextView textViewDes = (TextView) cardView.findViewById(R.id.description_Text);
        textViewDes.setText(description);

    }



    public static class ViewHolder extends  RecyclerView.ViewHolder{

        private CardView cardView;
        public ViewHolder(CardView v){
            super(v);
            cardView=v;
        }

    }
}