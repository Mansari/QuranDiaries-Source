package world.islam.qurandiaries;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class AddNotes extends AppCompatActivity {
    EditText SurahNumber;
    EditText AyahNumber;
    EditText DiaryNote;
    Spinner spinnerSurahs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerSurahs = (Spinner) this.findViewById(R.id.spinnerSurahName);
        AyahNumber = (EditText) this.findViewById(R.id.txtAyaNumber);
        DiaryNote = (EditText) this.findViewById(R.id.txtDiaryNote);

        loadSpinnerData();

    }

    private void loadSpinnerData() {
        // database handler
        QuranDBRepo db = new QuranDBRepo(getApplicationContext());

        // Spinner Drop down elements
        List<Surah> surahList = db.getAllSurah();
        // Creating adapter for spinner
        ArrayAdapter<Surah> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, surahList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerSurahs.setAdapter(dataAdapter);
    }


    public void AddNote(View view) {

        QuranDBRepo repo = new QuranDBRepo(this);
        QuranJournalEntry note = new QuranJournalEntry();
        note.surah_Number = ((Surah) spinnerSurahs.getSelectedItem ()).surah_Number;
        note.ayah_Number = Integer.parseInt(AyahNumber.getText().toString());
        note.content = DiaryNote.getText().toString();
        Integer _Entry_Id = repo.insert(note);
        Toast.makeText(this, "New entry inserted!", Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this)
                .setTitle("Entry Details")
                .setMessage("This is what you have entered:\r\nSurah #:" + note.surah_Number + " - Ayah #: " + note.ayah_Number  + "\r\nDiary Note: " + note.content )
                //Add the Surah #, Aya #, and Note
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();

        //reset form
        //No need to reset Surah - if you are continously adding ayat then it is fine ...spinnerSurahs.setSelection(0);
        //Increment Ayah Number by one (or leave as-is?)
        AyahNumber.setText(note.ayah_Number+1);
        DiaryNote.setText("");
    }

    public void ReturnToMenu(View view)
    {
        if (AyahNumber.getText().toString().matches("") && DiaryNote.getText().toString().matches("")) {
            finish();
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm")
                    .setMessage("Are you sure you want to return? You have unsaved content.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }
                    )
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing, stay on form //todo there must be another way to do this without doing a listener perhaps
                        }
                    }
                    )
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
