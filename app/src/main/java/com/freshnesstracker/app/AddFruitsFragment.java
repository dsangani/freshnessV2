package com.freshnesstracker.app;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.freshnesstracker.app.R.layout.fragment_add_fruits;
/**
 * Created by Sakura on 6/4/14.
 */
public class AddFruitsFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "purchaseDatePicker";
    private static final String TAG2 = "spinnerDebug";
    private static final String TAG3 = "datePicker";
    private static final String TAG4 = "doneButton";

    private int purchaseMonth = 0;
    private int purchaseDay = 0;
    private int purchaseYear = 0;
    private String selectedFruit = "";
    private String purchaseDate = "";

    private ArrayAdapter<CharSequence> pickFruitSpinnerAdapter;
    private Button addFruitButton;
    private DatePicker purchaseDatePicker;
    private GregorianCalendar purchaseDateCalendar;
    private Spinner pickFruitSpinner;
    private View addFruitsFragView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        addFruitsFragView = inflater.inflate(fragment_add_fruits, container, false);

        setupSpinner();
        setupDatePicker();
        setupDoneButton();

        // Inflate the layout for this fragment
        return addFruitsFragView;
    }

    private void setupDoneButton() {
        addFruitButton = (Button) addFruitsFragView.findViewById(R.id.add_fruits_button);

        addFruitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // After Done is pressed, get the selected fruit and the purchase date.
                Log.d(TAG4, selectedFruit + " " + String.format("%d/%d/%d", getPurchaseMonth(), getPurchaseDay(), getPurchaseYear()));
            }
        });
    }

    private void setupSpinner() {
        pickFruitSpinner = (Spinner) addFruitsFragView.findViewById(R.id.pick_a_fruit_spinner);
        pickFruitSpinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
         pickFruitSpinnerAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.fruits_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        pickFruitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        pickFruitSpinner.setAdapter(pickFruitSpinnerAdapter);
    }

    private void setupDatePicker() {
        purchaseDatePicker = (DatePicker) addFruitsFragView.findViewById(R.id.select_purchase_date_datepicker);

        purchaseDateCalendar = new GregorianCalendar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Setting maximum selected date to current date for API >= 11 (3.0)
            purchaseDatePicker.setMaxDate(System.currentTimeMillis());
        }
        else {
            // Set maximum selected date to current date for API < 11 (3.0)
            final int minYear = purchaseDateCalendar.get(Calendar.YEAR);
            final int minMonth = purchaseDateCalendar.get(Calendar.MONTH);
            final int minDay = purchaseDateCalendar.get(Calendar.DAY_OF_MONTH);

            purchaseDatePicker.init(minYear, minMonth, minDay,
                    new DatePicker.OnDateChangedListener() {

                        public void onDateChanged(DatePicker view, int year,
                                                  int month, int day) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, month, day);

                            if (purchaseDateCalendar.after(newDate)) {
                                view.init(minYear, minMonth, minDay, this);
                            }
                        }
                    });
            Log.w(TAG, "Setting current date restriction in API < 11 (3.0)");
        }

        Log.d(TAG3, String.format("%d/%d/%d", getPurchaseMonth(), getPurchaseDay(), getPurchaseYear()));
    }

    @Override
    /*
    * @param parent The AdapterView where the selection happened
    * @param view The view within the AdapterView that was clicked
    * @param position The position of the view in the adapter
    * @param id The row id of the item that is selected
    * */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedFruit = new String(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public int getPurchaseMonth() {
        // Months start from 0, hence +1
        return purchaseDatePicker.getMonth() + 1;
    }

    public int getPurchaseDay() {
        return purchaseDatePicker.getDayOfMonth();
    }

    public int getPurchaseYear() {
        return purchaseDatePicker.getYear();
    }
}
