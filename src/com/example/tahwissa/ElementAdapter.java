package com.example.tahwissa;
import com.example.tahwissa.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

//Define a new class called "ElementAdapter" that extends the ArrayAdapter<Element> class. 
//This class will be used to link the view with the data
public class ElementAdapter extends ArrayAdapter<Element>{
	
	//The class has three fields: a context, a layoutResourceId and an array of data.
	Context context; 
    int layoutResourceId;    
    Element data[] = null;
    
    //Define a constructor that takes the three arguments and add code to instantiate the added fields
    public ElementAdapter(Context context, int layoutResourceId, Element[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    
    //By Default, the ArrayAdapter<T> only takes TextView as referenced resource. Since we want to do more complex display,
    //so we have to override it like the following.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	//convertView is the old value used to gain performance and not load all the items to the listView.
    	//Check this link for further reading: http://goo.gl/hG6Lp3 
        View row = convertView;
        ElementHolder holder = null;
        
        if(row == null)
        {
            //If the convertView is null, so there are no old view, we need to inflate our layout into it.
        	LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            //ElementHolder is a class defined below, used to hold an element of the list
            holder = new ElementHolder();
            holder.name = (TextView)row.findViewById(R.id.label);
            holder.rating = (RatingBar) row.findViewById(R.id.ratingBar1);
            
            //setTag is used to store data within the view, further reading: http://goo.gl/WHzchb
            row.setTag(holder);
        }
        else
        {
        	//Else (if the convertView exists so we just use it by getting its data
            holder = (ElementHolder) row.getTag();
        }
        
        //Now retrieve the element by using the position and set the name and rating as the following
        Element element = data[position];
        holder.name.setText(element.name);
        holder.rating.setRating(element.rating);
        
        //Finally return the row
        return row;
    }
    
    //Define a new class in order to use the ViewHolder pattern
    //Further reading: http://goo.gl/R6PMG
    static class ElementHolder
    {
        TextView name;
        RatingBar rating;
    }

}
