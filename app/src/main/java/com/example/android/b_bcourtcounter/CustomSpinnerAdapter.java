package com.example.android.b_bcourtcounter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Osondu Tochukwu Andrew
 * on 1/17/2018.
 * Contact Email: tosolife@yahoo.com
 * Contact Phone No: 0803680243(WhatsApp), 08117042121.
 * Github: https://github.com/tboy44wiz
 * Linkedin: https://www.linkedin.com/in/osondu-tochukwu-81359a96
 */

class CustomSpinnerAdapter extends ArrayAdapter{

    Context myContext;
    String[] myTeamNames;
    int[] myTeamLogos;

    public CustomSpinnerAdapter(Context context, String[] teamName, int[] teamLogos) {
        super(context, R.layout.custom_spinner_layout, teamName);
        this.myContext = context;
        this.myTeamNames = teamName;
        this.myTeamLogos = teamLogos;
    }



    /*
    * This overridden method inflates the customised spinner data inside the spinner widget.
    * */
    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.custom_spinner_layout, parent, false);

        TextView spinnerTextView = rowView.findViewById(R.id.spinnerCustom_TextView);
        spinnerTextView.setText(myTeamNames[position]);

        ImageView spinnerImageView = rowView.findViewById(R.id.spinnerCustom_ImageView);
        spinnerImageView.setImageResource(myTeamLogos[position]);

        if (position == 0) {
            spinnerImageView.setVisibility(View.GONE);
        }
        return rowView;
    }

    /*
    * This overridden method returns the "getCustomView" method which inflates the customised
    * spinner data inside the spinner widget.
    * */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    /*
    * This overridden method shows the drop down list when the spinner is been clicked
    * */
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
