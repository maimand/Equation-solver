package com.example.ptb2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private EditText a_txt,b_txt,c_txt;
    private Button button;
    private ListView listView;
    private ArrayList<Equation> equations;
    private EquationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        equations = new ArrayList<>();
        adapter = new EquationAdapter(MainActivity.this,equations);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a_txt.getText().toString().equals("") || !isNumber(a_txt.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                }else if(b_txt.getText().toString().equals("") || !isNumber(b_txt.getText().toString())){
                    Toast.makeText(MainActivity.this,"Invalid input", Toast.LENGTH_LONG).show();
                }else if(c_txt.getText().toString().equals("") || !isNumber(c_txt.getText().toString())){
                    Toast.makeText(MainActivity.this,"Invalid input", Toast.LENGTH_LONG).show();
                }else {
                    double a = Double.parseDouble(a_txt.getText().toString());
                    double b = Double.parseDouble(b_txt.getText().toString());
                    double c = Double.parseDouble(c_txt.getText().toString());
                    Equation equation = new Equation(a,b,c);
                    equations.add(equation);
                    adapter.notifyDataSetChanged();
                }
                a_txt.setText("");
                b_txt.setText("");
                c_txt.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Details.class);
                Equation equation = equations.get(i);
                intent.putExtra("data", equation);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                equations.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public boolean isNumber(String str) {
        try {
            double v = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

    public void findId(){
        a_txt = findViewById(R.id.a_edit_text);
        b_txt = findViewById(R.id.b_edit_text);
        c_txt = findViewById(R.id.c_edit_text);
        button = findViewById(R.id.solve_button);
        listView = findViewById(R.id.history);
    }

    class EquationAdapter extends ArrayAdapter<Equation>{
        private LayoutInflater inflater;
        public EquationAdapter(Context context, ArrayList<Equation> equations){
            super(context,0,equations);
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.custom_list_view, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Equation equation = getItem(position);

            viewHolder.equation_text.setText(equation.toString());
            viewHolder.x_text.setText(equation.resultOut());

            return convertView;

        }
    }

    private class ViewHolder{
        private final TextView x_text, equation_text;

        private ViewHolder(View view) {
            this.x_text = view.findViewById(R.id.x_text_view);
            this.equation_text = view.findViewById(R.id.equation_text_view);
        }
    }

//    class EquationAdapter extends BaseAdapter{
//        Context context;
//        int layout;
//        ArrayList<Equation> equations;
//        public EquationAdapter(Context context, int layout, ArrayList<Equation> equations){
//            this.context = context;
//            this.layout = layout;
//            this.equations = equations;
//        }
//
//        @Override
//        public int getCount() {
//            return equations.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(this.layout, viewGroup,false);
//
//            TextView equation = findViewById(R.id.equation_text_view);
//            TextView x1_text_view = findViewById(R.id.x1_text_view);
//            TextView x2_text_view = findViewById(R.id.x2_text_view);
//
//            equation.setText(equations.get(i).toString());
//            double x1 = equations.get(i).resultCompute().get(0);
//            double x2 = equations.get(i).resultCompute().get(1);
//
//            DecimalFormat f = new DecimalFormat("##.00");
//            x1_text_view.setText(f.format(x1));
//            x2_text_view.setText(f.format(x2));
//            return view;
//        }
//    }
}