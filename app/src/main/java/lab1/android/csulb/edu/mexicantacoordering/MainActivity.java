package lab1.android.csulb.edu.mexicantacoordering;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;

import  android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.app.PendingIntent;
import java.util.ArrayList;
public class MainActivity extends Activity {

    private float sum;
    private float taxable;
    private float tax;
    private StringBuilder message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message=new StringBuilder();

        final RadioGroup size = (RadioGroup) findViewById(R.id.radiogroupTacoSize);
        int idSize = size.getCheckedRadioButtonId();

        //Submit button Clicked
        final Button button = (Button) findViewById(R.id.btnOrder);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //initalize all sum to zero
                sum=taxable=tax=0.0f;

                //check size of tacos
                final RadioGroup size = (RadioGroup) findViewById(R.id.radiogroupTacoSize);
                int idSize = size.getCheckedRadioButtonId();
                RadioButton  selectedRadioButton = (RadioButton)findViewById(idSize);
                String radioButtonText = selectedRadioButton.getText().toString();

                if(radioButtonText.equals("Large")) {
                    sum += 2;
                    message.append("Size: Large " );

                }
                else {
                    sum += 1;
                    message.append("Size: Medium " );

                }

                //check type of tortilla
                final RadioGroup type =  (RadioGroup) findViewById(R.id.radiogrpTortillaType);
                int idType = type.getCheckedRadioButtonId();
                RadioButton  selectedRadioButton2 = (RadioButton)findViewById(idType);
                String radioButtonText2 = selectedRadioButton.getText().toString();

                if(radioButtonText2.equals("Corn"))
                {
                    sum += 2;
                    message.append("Tortilla: Corn " );
                }
                else{
                    sum += 1;
                    message.append("Tortilla: Flour " );

                }


                message.append("Fillings:");
                //Fillings
                final CheckBox chkBeef = (CheckBox) findViewById(R.id.chkBeef);
                if (chkBeef.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" Beef " );
                }

                final CheckBox chkChicken = (CheckBox) findViewById(R.id.chkChicken);
                if (chkChicken.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" Chicken " );
                }

                final CheckBox chkWhiteFish = (CheckBox) findViewById(R.id.chkWhiteFish);
                if (chkWhiteFish.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" WhiteFish " );
                }
                final CheckBox chkCheese = (CheckBox) findViewById(R.id.chkCheese);
                if (chkCheese.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" Cheese " );
                }

                final CheckBox chkSeaFood = (CheckBox) findViewById(R.id.chkSeafood);
                if (chkSeaFood.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" SeaFood " );
                }

                final CheckBox chkRice = (CheckBox) findViewById(R.id.chkRice);
                if (chkRice.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" Rice " );

                }

                final CheckBox chkBeans = (CheckBox) findViewById(R.id.chkBeans);
                if (chkBeans.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" Beans " );
                }

                final CheckBox chkPicoDeGalo = (CheckBox) findViewById(R.id.chkPicoDeGalo);
                if (chkPicoDeGalo.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" PicoDeGalo " );

                }

                final CheckBox chkGaucamole = (CheckBox) findViewById(R.id.chkGaucamole);
                if (chkGaucamole.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" chkGaucamole " );
                }


                final CheckBox chkLBT = (CheckBox) findViewById(R.id.chkLBT);
                if (chkLBT.isChecked()) {
                    sum = sum + 0.5f ;
                    message.append(" LBT " );
                }


                //Beverage

                final CheckBox chkSoda = (CheckBox) findViewById(R.id.chkSoda);
                final CheckBox chkCerveza = (CheckBox) findViewById(R.id.chkCerveza);
                final CheckBox chkMargarita = (CheckBox) findViewById(R.id.chkMargarita);
                final CheckBox chkTequilla = (CheckBox) findViewById(R.id.chkTequilla);
                StringBuilder taxableItems = new StringBuilder();
                taxableItems.append(" Beverages : ");
                if (chkSoda.isChecked())
                {
                    taxable = taxable + 1;
                    taxableItems.append(" Soda ");
                }

                if (chkCerveza.isChecked())
                {
                    taxable = taxable + 1.5f;
                    taxableItems.append(" chkCerveza ");
                }
                if (chkMargarita.isChecked())
                {
                    taxable = taxable + 2.25f;
                    taxableItems.append(" chkMargarita ");
                }
                if (chkTequilla.isChecked())
                {
                    taxable = taxable + 3f;
                    taxableItems.append(" chkTequilla ");
                }


                if (chkSoda.isChecked() || chkCerveza.isChecked() || chkMargarita.isChecked()|| chkTequilla.isChecked()) {
                    tax = taxable * 0.9f ;
                    sum = sum + tax + taxable;

                    String formatedtax = String.format("%.02f", tax);
                    message.append(taxableItems);
                    message.append(" Tax : "+ formatedtax);
                    formatedtax = null;
                }

                String formatedSum = String.format("%.02f", sum);
                message.append(" Total : "+ formatedSum);
                formatedSum = null;


                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> dividedMsg = smsManager.divideMessage(message.toString());
                    smsManager.sendMultipartTextMessage("5627397094", null, dividedMsg, null, null);
                    Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_LONG).show();

                }
                catch(Exception e){

                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }


            }});


    }
}
