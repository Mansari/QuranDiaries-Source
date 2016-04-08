package world.islam.qurandiaries;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListNotes extends AppCompatActivity {


    ListView listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listNotes = (ListView) this.findViewById(R.id.listNotes);

        QuranDBRepo repo = new QuranDBRepo(this);
        //can move this to a refresh button
        ArrayList<HashMap<String, String>> noteList =  repo.getNoteList();
        if(noteList.size()!=0) {
            ListAdapter adapter = new SimpleAdapter( ListNotes.this,noteList,android.R.layout.simple_list_item_2, new String[] { "surah_aya", "content"}, new int[] {android.R.id.text1, android.R.id.text2});
            listNotes.setAdapter(adapter);
        }else{
            Toast.makeText(this, "You have no notes yet ! Begin reflecting !", Toast.LENGTH_SHORT).show();
        }
    }
}
