package com.example.jayghodasara.myyyyyy;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class OneFragment extends Fragment {

    ViewPager viewPager;

    RecyclerView recyclerView;

public static int i=0;
    FirebaseDatabase f;
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref3;
    DatabaseReference ref4;
    Task<Void> ref5;
    String m_Text;
    public OneFragment() {


    }

   // @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one, container,false);

        recyclerView=(RecyclerView)rootView.findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        f= FirebaseDatabase.getInstance();



        ref=f.getReference();
        ref2=ref.child("Mens");
return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<food,foodviewholder> FBRA=new FirebaseRecyclerAdapter<food, foodviewholder>(
                food.class,
                R.layout.list_item,
                foodviewholder.class,
                ref2
        ) {


            @Override
            public foodviewholder onCreateViewHolder(ViewGroup parent, int viewType) {


                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            protected void populateViewHolder(final foodviewholder viewHolder, final food model, final int position) {


                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String name=model.getName();
                        final String s=model.getPrice();

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Enter Quantity");


                        final EditText input = new EditText(getContext());

                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);


                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m_Text = input.getText().toString();
                                String PRICE=s;

                                int total=Integer.parseInt(PRICE)*Integer.parseInt(m_Text);
                                i++;
                                SharedPreferences II=getContext().getSharedPreferences("I", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ee=II.edit();
                                ee.putInt("value i",i);
                                ee.commit();
                                SharedPreferences ss=getContext().getSharedPreferences("Total"+i,Context.MODE_PRIVATE);
                                SharedPreferences.Editor e=ss.edit();
                                e.putInt("Tot",total);
                                e.commit();


                                SharedPreferences s=getContext().getSharedPreferences("Values", Context.MODE_PRIVATE);
                                String UserName=s.getString("Name",null);
                                ref3=f.getReference("Users").child(UserName);
                                ref4=ref3.child("CART").push();
                                ref5=ref4.child("ItemName").setValue(name);
                                ref5=ref4.child("Quantity").setValue(m_Text);
                                ref5=ref4.child("Price").setValue(PRICE);


                           //     Toast.makeText(getContext(), name +" "+ PRICE +" "+ m_Text+" "+i, Toast.LENGTH_LONG).show();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                        builder.show();

                    }
                });


                viewHolder.setName(model.getName());

                viewHolder.setImage(getActivity().getApplicationContext(), model.getImg());
                viewHolder.setPrice(model.getPrice());
            }
        };
        recyclerView.setAdapter(FBRA);


    }




    public static class foodviewholder extends RecyclerView.ViewHolder
    {
        View mview;
        Button b;


        public foodviewholder(View itemView) {
            super(itemView);
            mview=itemView;
b=(Button)itemView.findViewById(R.id.btn);

        }

        public void setName(String name)
        {
            TextView n= (TextView)mview.findViewById(R.id.text);

            n.setText(name);
        }

        public void setPrice(String price)
        {
            TextView p= (TextView)mview.findViewById(R.id.price);
            p.setText(price);
        }

        public void setImage(Context c,String image)
        {
            ImageView i= (ImageView)mview.findViewById(R.id.img);
            Glide.with(c).load(image).into(i);


        }



    }













}