package de.hochschule.trier.autocomplete;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_ENTRIES = 5;
    private AutoCompleteTextView[] autoCompleteTextViews;
    private Button finishButton;

    // Vordefinierte Liste von Einkaufsprodukten
    private static final String[] PRODUKTE = new String[] {
            "Äpfel", "Bananen", "Backpapier", "Brot", "Butter", "Bionade", "Bitburger",
            "Chips", "Cola", "Champignons",
            "Duschgel", "Datteln",
            "Eier", "Essig",
            "Fisch", "Fleisch", "Frischkäse",
            "Gemüse", "Gurken",
            "Haferflocken", "Honig",
            "Iced Tea",
            "Joghurt", "Juice",
            "Käse", "Kartoffeln", "Ketchup",
            "Limonade",
            "Milch", "Mayonnaise", "Mineralwasser",
            "Nudeln", "Nüsse",
            "Orangen", "Olivenöl",
            "Paprika", "Pizza", "Putzmittel",
            "Quark",
            "Reis", "Rosinen",
            "Salz", "Saft", "Schokolade", "Spaghetti",
            "Tomaten", "Tofu", "Toilettenpapier",
            "Vanille Eis",
            "Wasser", "Waschmittel", "Wein",
            "Zahnpasta", "Zitronen", "Zucker"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisierung der AutoCompleteTextViews
        autoCompleteTextViews = new AutoCompleteTextView[MAX_ENTRIES];
        autoCompleteTextViews[0] = findViewById(R.id.autoCompleteItem1);
        autoCompleteTextViews[1] = findViewById(R.id.autoCompleteItem2);
        autoCompleteTextViews[2] = findViewById(R.id.autoCompleteItem3);
        autoCompleteTextViews[3] = findViewById(R.id.autoCompleteItem4);
        autoCompleteTextViews[4] = findViewById(R.id.autoCompleteItem5);

        // Adapter für Vorschlagsliste einrichten
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, PRODUKTE);

        // Adapter für alle AutoCompleteTextViews setzen
        for (AutoCompleteTextView autoCompleteTextView : autoCompleteTextViews) {
            autoCompleteTextView.setAdapter(adapter);
            autoCompleteTextView.setThreshold(1); // Nach einem Buchstaben Vorschläge zeigen
        }

        // fertig button
        finishButton = findViewById(R.id.buttonFinish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShoppingList();
            }
        });
    }

    /**
     * Zeigt den erstellten Warenkorb als Toast an
     * Leere Einträge werden nicht angezeigt
     */
    private void showShoppingList() {
        StringBuilder shoppingList = new StringBuilder("Einkaufsliste: \n");
        boolean hasItems = false;

        for (AutoCompleteTextView autoCompleteTextView : autoCompleteTextViews) {
            String item = autoCompleteTextView.getText().toString().trim();
            if (!item.isEmpty()) {
                shoppingList.append("- ").append(item).append("\n");
                hasItems = true;
            }
        }

        if (hasItems) {
            Toast.makeText(this, shoppingList.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Keine Artikel in der Einkaufsliste!", Toast.LENGTH_SHORT).show();
        }
    }
}