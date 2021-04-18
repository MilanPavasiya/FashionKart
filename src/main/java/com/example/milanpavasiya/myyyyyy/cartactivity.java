package com.example.jayghodasara.myyyyyy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cartactivity extends AppCompatActivity {

    TextView t;
    DatabaseReference ref,ref2,ref3,ref4;
    FirebaseDatabase f;
    public static int TOTAL=0;
    Button b;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity);
        recyclerView=(RecyclerView)findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        t=(TextView)findViewById(R.id.textView4);
b=(Button)findViewById(R.id.btnn);
        SharedPreferences s=this.getSharedPreferences("Values",MODE_PRIVATE);
        final String UserName=s.getString("Name",null);
        f= FirebaseDatabase.getInstance();
        ref=f.getReference("Users").child(UserName);
        ref2=ref.child("CART");

        SharedPreferences II=this.getSharedPreferences("I", Context.MODE_PRIVATE);
       int vali= II.getInt("value i", 0);

        for(int i=1;i<=vali;i++)
        {
            SharedPreferences ss=this.getSharedPreferences("Total" + i, Context.MODE_PRIVATE);
            int t=ss.getInt("Tot",0);
            TOTAL=TOTAL+t;
        }

       t.setText(String.valueOf(TOTAL));
        TOTAL=0;

b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences s = getSharedPreferences("Values", MODE_PRIVATE);
        String name = s.getString("Name", null);

        moveGameRoom(f.getReference("Users").child(UserName)
                .child("CART"), f.getReference("Orders").child(name).push());
        Intent u=new Intent(cartactivity.this,Thankyou.class);
        startActivity(u);
    }
});
        final FirebaseRecyclerAdapter<food2, Foodviewholder> FBRA = new FirebaseRecyclerAdapter<food2, Foodviewholder>(
                food2.class,
                R.layout.list_item2,
                Foodviewholder.class,
                ref2
        ) {
            public Foodviewholder onCreateViewHolder(ViewGroup parent, int viewType) {


                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            protected void populateViewHolder(final Foodviewholder viewHolder, final food2 model, final int position) {


                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String name = model.getItemName();
                        final String s = model.getPrice();
                        final String q= model.getQuantity();

                      f.getReference("Users").child(UserName)
                        .child("CART").orderByChild("ItemName").equalTo(name).addChildEventListener(new ChildEventListener() {
                          @Override
                          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                              dataSnapshot.getRef().removeValue();
                          }

                          @Override
                          public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                          }

                          @Override
                          public void onChildRemoved(DataSnapshot dataSnapshot) {

                          }

                          @Override
                          public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                          }

                          @Override
                          public void onCancelled(DatabaseError databaseError) {

                          }
                      });


                    }
                });


                viewHolder.setItemName(model.getItemName());

                viewHolder.setQuantity(model.getQuantity());
                viewHolder.setPrice(model.getPrice());

            }
        };
        recyclerView.setAdapter(FBRA);


    }

    private void moveGameRoom(final DatabaseReference fromPath, final DatabaseReference toPath) {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError != null) {
                            System.out.println("Copy failed");
                        } else {
                            System.out.println("Success");

                        }
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


/*t=(TextView)findViewById(R.id.n);
        t2=(TextView)findViewById(R.id.q);
        t3=(TextView)findViewById(R.id.p);
        t4=(TextView)findViewById(R.id.totalcart);
        SharedPreferences pref2=this.getSharedPreferences("value", Context.MODE_PRIVATE);
       int i= pref2.getInt("hello",0);
        Toast.makeText(this,String.valueOf(i),Toast.LENGTH_LONG).show();
        for(int j=1;j<=i;j++)
        {
        SharedPreferences pref=this.getSharedPreferences("Mypref" + j, Context.MODE_PRIVATE);



            String name = pref.getString("Name", null);
            String price = pref.getString("Price", null);
            String quan = pref.getString("Quan", null);


            int total = Integer.parseInt(quan) * Integer.parseInt(price);
            cartprice=cartprice+total;
            String s = String.valueOf(total);
            t.append(name + "\n");
            t2.append(quan + "\n");
            t3.append(s + "\n");
            t4.setText(String.valueOf(cartprice));

         }
    }
}*/


    public static class Foodviewholder extends RecyclerView.ViewHolder {
        View mview;
        Button b;


        public Foodviewholder(View itemView) {
            super(itemView);
            mview = itemView;
            b = (Button) itemView.findViewById(R.id.btn);

        }

        public void setItemName(String name) {
            TextView n = (TextView) mview.findViewById(R.id.text);

            n.setText(name);
        }

        public void setPrice(String price) {
            TextView p = (TextView) mview.findViewById(R.id.price);
            p.setText(price);
        }

        public void setQuantity(String quantity) {
            TextView q = (TextView) mview.findViewById(R.id.quan);
            q.setText(quantity);
        }


        }


    }
