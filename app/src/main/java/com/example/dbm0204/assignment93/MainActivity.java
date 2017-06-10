package com.example.dbm0204.assignment93;
import java.util.ArrayList;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
public class MainActivity extends Activity {
    private static ArrayList<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAdapter adapter = new MyAdapter(this, generateData());
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

                Item clickedObj = (Item) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,
                        "Clicked item:\n" +
                                clickedObj.getNumber() + ": " +
                                clickedObj.getName(),
                        Toast.LENGTH_LONG).show();
            }});
        registerForContextMenu(listView);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Select an Action");
        menu.add(0,v.getId(),0,"CALL");
        menu.add(0,v.getId(),0, "SMS");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        ArrayList<Item> list =generateData();
        if(item.getTitle()=="CALL"){
            Toast.makeText(getApplicationContext(),"Calling Code",Toast.LENGTH_LONG).show();
            PackageManager pm = getApplicationContext().getPackageManager();
            int haspm = pm.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,getApplicationContext().getPackageName());
            if(haspm!=PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "000000000"));
                startActivity(callIntent);
            }
        }
        else if(item.getTitle()=="SMS"){
            try {
                Toast.makeText(getApplicationContext(),"Calling Code",Toast.LENGTH_LONG).show();
                PackageManager pm = getApplicationContext().getPackageManager();
                int haspm = pm.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,getApplicationContext().getPackageName());
                if(haspm!=PackageManager.PERMISSION_GRANTED) {
                    Uri uri = Uri.parse("smsto:" + "000000000");
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    smsIntent.putExtra("sms_body", "SMS application launches from ___ example");
                    startActivity(smsIntent);
                    }
                } catch (Exception e){
                Toast.makeText(this,"SMS failed please try again later!",Toast.LENGTH_LONG).show();
                e.printStackTrace();
                }
            return false;
            }
        return true;
    }

    private ArrayList<Item> generateData(){
        items = new ArrayList<Item>();
        items.add(new Item("Ben Mathew","00000000"));
        items.add(new Item("Mathew Ben ","111111111"));
        items.add(new Item("Benjamin Mathew","222222222"));
        return items;
    }
}

