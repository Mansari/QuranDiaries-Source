package world.islam.qurandiaries;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {
    EditText SurahNumber;
    EditText AyahNumber;
    EditText DiaryNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void PreviewNote(View view) {
        SurahNumber = (EditText)this.findViewById(R.id.txtSurahNumber);
        AyahNumber = (EditText)this.findViewById(R.id.txtAyaNumber);
        DiaryNote = (EditText)this.findViewById(R.id.txtDiaryNote);

        QuranDBRepo repo = new QuranDBRepo(this);
        QuranJournalEntry note = new QuranJournalEntry();
        note.surah_Number = Integer.parseInt(SurahNumber.getText().toString());
        note.ayah_Number = Integer.parseInt(AyahNumber.getText().toString());
        note.content = DiaryNote.getText().toString();
        Integer _Student_Id = repo.insert(note);
        Toast.makeText(this,"New entry inserted!",Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this)
                .setTitle("Entry Details")
                .setMessage("This is what you have entered:\r\nSurah #:" + SurahNumber.getText() + " - Ayah #: " + AyahNumber.getText() + "\r\nDiary Note: " + DiaryNote.getText() )
                //Add the Surah #, Aya #, and Note
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
