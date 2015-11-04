package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ds.etrip.Adapter.Custom_List_Adapter;
import com.ds.etrip.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hymei on 2015/10/30.
 */
public class Frag_Rent extends Fragment {
    View v;
    List<String> categorys,products;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categorys=new ArrayList<>();
        products=new ArrayList<>();
        for (int i=0;i<10;i++){
            categorys.add("CategoryItem:"+String.valueOf(i));
            products.add("ProductItem:"+String.valueOf(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_rent,container,false);

        ListView category,product;
        category= (ListView) v.findViewById(R.id.rent_list_category);
        product= (ListView) v.findViewById(R.id.rent_list_product);

        ListAdapter categoryAdapter=new Custom_List_Adapter<>(getActivity(),R.layout.item_rent_category,categorys);
        ListAdapter productsAdapter=new Custom_List_Adapter<>(getActivity(),R.layout.item_rent_product,products);
        product.setAdapter(productsAdapter);
        category.setAdapter(categoryAdapter);
        product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),Act_RentDetail.class));
            }
        });
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"Category",Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
