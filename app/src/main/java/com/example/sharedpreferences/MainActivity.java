package com.example.sharedpreferences;

/**
 * SharedPreferences w Android Studio to prosty sposób na przechowywanie
 * niewielkich ilości danych w formacie klucz-wartość. Jest idealny do
 * zapisywania danych takich jak preferencje użytkownika, ustawienia
 * aplikacji, czy drobne informacje, które mają zostać zachowane między
 * sesjami aplikacji.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Deklaracja widoków: pole tekstowe do wprowadzania imienia, tekst powitalny i przycisk do zapisu
    private EditText editTextName;
    private TextView textViewGreeting;
    private Button buttonSave;

    // Deklaracja obiektu SharedPreferences
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja widoków poprzez połączenie ich z elementami w pliku layoutu XML
        editTextName = findViewById(R.id.editTextName);
        textViewGreeting = findViewById(R.id.textViewGreeting);
        buttonSave = findViewById(R.id.buttonSave);

        // Inicjalizacja SharedPreferences
        // "UserPrefs" to nazwa pliku, w którym będą przechowywane dane
        // MODE_PRIVATE oznacza, że dane będą dostępne tylko w tej aplikacji
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Odczytanie zapisanej wartości z SharedPreferences
        // Metoda getString() zwraca zapisane dane pod kluczem "userName" lub domyślną wartość "Użytkowniku"
        String savedName = sharedPreferences.getString("userName", "Użytkowniku");

        // Ustawienie powitalnego tekstu w TextView, na podstawie odczytanego imienia
        textViewGreeting.setText("Witaj, " + savedName + "!");

        // Ustawienie działania przycisku zapisu
        // Kiedy użytkownik kliknie przycisk "Zapisz imię", uruchomi się poniższy kod
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobranie tekstu wpisanego przez użytkownika w pole EditText
                String name = editTextName.getText().toString();

                // Otworzenie edytora SharedPreferences, aby móc zapisać nowe dane
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Umieszczenie wartości w SharedPreferences: klucz to "userName", a wartość to imię
                editor.putString("userName", name);

                // Zastosowanie zmian (asynchronicznie) - zapisanie danych do SharedPreferences
                editor.apply();

                // Aktualizacja powitania, aby od razu wyświetlić nowe imię
                textViewGreeting.setText("Witaj, " + name + "!");
            }
        });
    }
}

/**
 * Działanie aplikacji:
 * 1. Po uruchomieniu aplikacji, aplikacja próbuje odczytać zapisane imię z SharedPreferences.
 *    Jeśli nie znajdzie imienia, wyświetli domyślne powitanie: "Witaj, Użytkowniku!".
 * 2. Użytkownik może wpisać swoje imię i kliknąć przycisk "Zapisz imię", co zapisuje jego imię w SharedPreferences.
 * 3. Po kliknięciu przycisku, imię jest natychmiast wyświetlane w powitaniu.
 * 4. Przy kolejnym uruchomieniu aplikacji, wcześniej zapisane imię zostanie ponownie odczytane z SharedPreferences
 *    i wyświetlone.
 */
