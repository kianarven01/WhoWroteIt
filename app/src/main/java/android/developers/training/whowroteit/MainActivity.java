package android.developers.training.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView titleTextView;
private TextView authorTextView;
private EditText bookInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView=findViewById(R.id.titleText);
        authorTextView=findViewById(R.id.authorText);
        bookInput=findViewById(R.id.bookInput);
    }

    public void searchBooks(View view) {
        // Get the search string from the input field.
        String queryString = bookInput.getText().toString();

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty, start a FetchBook AsyncTask.
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            new FetchBook(titleTextView, authorTextView).execute(queryString);
        }
        // Otherwise update the TextView to tell the user there is no connection or no search term.
        else {
            if (queryString.length() == 0) {
                authorTextView.setText("");
                titleTextView.setText("Nosearch term");
            } else {
                authorTextView.setText("");
                titleTextView.setText("No network");
            }
        }
    }
}


