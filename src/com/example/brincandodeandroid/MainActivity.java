package com.example.brincandodeandroid;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView list;
	Button button;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(new coisoAdapter(this));
        
        button = (Button) findViewById(R.id.button1);
        
        registerListClickCallback();
        registerButtonClickCallback();
        createButtons();
    }

	private void registerListClickCallback() {
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {
				String message = "Voce clicou no item "
						+(position + 1);

				TextView title = (TextView) viewClicked.findViewById(R.id.textView1);
				title.setText("clicaro nimim");
				
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
			}
		});
	}
	
    private void registerButtonClickCallback() {
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String message = "Clicou no botãozinho!";
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
			}
		});
	}

	private void createButtons() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.layoutinho);
		ll.setOrientation(LinearLayout.VERTICAL);

		 for(int i = 0; i < 3; i++) {
	         CheckBox ch = new CheckBox(getApplicationContext());
	         ch.setText("Opção "+(i+1));
	         ch.setId(i);
	         ch.setButtonDrawable(R.drawable.checkbox_selector2);
	         
	         ch.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					CheckBox c = (CheckBox) v;
					unselectCheckboxes(c.getId());
					Toast.makeText(MainActivity.this, "Clicou no "+c.getId(), Toast.LENGTH_LONG).show();
				}
				
				public void unselectCheckboxes(int id){
					LinearLayout ll = (LinearLayout) findViewById(R.id.layoutinho);

					for(int i=0;i<3;i++){
						if(i != id){
							CheckBox cb = (CheckBox) ll.findViewById(i);
							cb.setChecked(false);
						}
					}
				}
			});
	         
	        ll.addView(ch);
	     }
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

class singleRow
{
	String title;
	String description;
	
	public singleRow(String title, String description) {
		this.title = title;
		this.description = description;
	}
}

class coisoAdapter extends BaseAdapter
{

	ArrayList<singleRow> list;
	Context context;
	
	public coisoAdapter(Context c) {
		
		context = c;
		list = new ArrayList<singleRow>();
		
		String[] titles = {"titulo1", "titulo2", "titulo3", "titulo4", "titulo5"};
		String[] descriptions = {"description1", "description2", "description3", "description4", "description5"};
		
		for(int i=0;i<5;i++){
			list.add(new singleRow(titles[i], descriptions[i]));
		}
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	//para cada linha da view
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//usar inflator para criar um objeto novo
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//cria uma linha da tabela com esse layout ae
		View row = inflater.inflate(R.layout.single_row, parent, false);
		
		TextView title = (TextView) row.findViewById(R.id.textView1);
		TextView description = (TextView) row.findViewById(R.id.textView2);
		
		singleRow temp = list.get(position);
		title.setText(temp.title);
		description.setText(temp.description);
		
		return row;
	}
	
}
