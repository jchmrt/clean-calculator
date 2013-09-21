package home.jmstudios.calc;

import java.io.OutputStreamWriter;
import java.util.Map;

import home.jmstudios.calc.R;
import home.jmstudios.calc.R.id;
import home.jmstudios.calc.R.layout;
import home.jmstudios.calc.R.menu;
import home.jmstudios.calc.R.style;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	EditText editText, editTextQ;
	final Context context = this;

	SlidingDrawer slidingdrawer;

	LinearLayout linearlayout1;

	boolean wrong = false, firsttime = true, visbutton = false, vs = false,
			js = false, r = false, b = false;

	Button button0, button1, button2, button3, button4, button5, button6,
			button7, button8, button9, buttonPlus, buttonMinus, buttonMultiply,
			buttonDivide, buttonPoint, buttonDel, buttonReset, button_sin,
			button_cos, button_tan, button_squared_2, button_root, button_del,
			button_dec, button_bin, button_pi, buttonEqual;

	String sum = "", one, oneAgain = "", two, twoAgain = "", three,
			threeAgain = "", four, fourAgain = "", five, fiveAgain = "", six,
			sixAgain, seven, sevenAgain = "", eight, eightAgain = "", nine,
			nineAgain = "", zero, plus1, minus, multiply, divide, equal, point,
			del, reset, dec_string = "", hex_string = "", oct_string = "",
			pi = "3.14159265359", calc = "";

	Integer countOne = 0, dec_num, unicode_value;

	Float result = 0f, result_mul = 1f, result_div = 1f;

	int pressCount = 1, sumZero, dec_flag = 0, c, i, pl = 0, cursor;

	char press;

	String EditTextMsg, bin_num, hex_num, oct_num;

	private String theme_settings;

	Float floatEditTextMsg;

	Double doubleEditTextMsg, afterSin, after_cos, after_tan,
			toRadian_doubleEditTextMsg, after_squared_2, after_root,
			after_qube;

	Vibrator vibrator;

	int sdk = android.os.Build.VERSION.SDK_INT;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_about:
			Intent aboutintent = (Intent) new Intent(this, About.class);
			startActivity(aboutintent);
			return true;

		case R.id.menu_settings:
			Intent preferencesintent = (Intent) new Intent(this,
					Preferences.class);
			startActivity(preferencesintent);
			return true;
			



		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(Main.this);
		String new_theme_settings = prefs.getString("theme", "a");
		if (new_theme_settings != theme_settings) {
			Intent intent = getIntent();
			finish();
			startActivity(intent);
			System.out.println("change");
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(Main.this);
		theme_settings = prefs.getString("theme", "a");
		if (((prefs.getString("theme", "a")).charAt(0) == 'b')) {
			setTheme(R.style.AppThemeBlue);
			setContentView(R.layout.mainblue);

		} else if (((prefs.getString("theme", "a")).charAt(0) == 'c')) {
			setTheme(R.style.AppThemeOrange);
			setContentView(R.layout.mainorange);

		} else if (((prefs.getString("theme", "a")).charAt(0) == 'd')) {
			setTheme(R.style.AppThemePurple);
			setContentView(R.layout.mainpurple);

		} else if (((prefs.getString("theme", "a")).charAt(0) == 'e')) {
			setTheme(R.style.AppThemeRed);
			setContentView(R.layout.mainred);

		} else {
			setContentView(R.layout.main);
		}
		System.out.println(prefs.getString("theme", "lightgreen"));
		super.onCreate(savedInstanceState);

		// ActionBar.setIcon(R.drawable.pushed);

		editText = (EditText) findViewById(R.id.editText1);

		slidingdrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		linearlayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

		button0 = (Button) findViewById(R.id.button0);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);

		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);

		buttonPlus = (Button) findViewById(R.id.buttonPlus);
		buttonMinus = (Button) findViewById(R.id.buttonMinus);
		buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
		buttonDivide = (Button) findViewById(R.id.buttonDivide);
		buttonPoint = (Button) findViewById(R.id.buttonPoint);

		buttonEqual = (Button) findViewById(R.id.buttonEqual);

		button_sin = (Button) findViewById(R.id.button_sin);
		button_cos = (Button) findViewById(R.id.button_cos);
		button_tan = (Button) findViewById(R.id.button_tan);
		button_root = (Button) findViewById(R.id.button_root);
		/*
		 * button_squared_2 = (Button) findViewById(R.id.button_squared_2);
		 * 
		 * button_del = (Button) findViewById(R.id.button_del); button_dec =
		 * (Button) findViewById(R.id.button_dec); button_bin = (Button)
		 * findViewById(R.id.button_bin);
		 */
		buttonReset = (Button) findViewById(R.id.buttonReset);

		// button_del=(Button)findViewById(R.id.button_del);

		editText.setText(result.toString());

		EditText result = (EditText) findViewById(R.id.editText1);
		// Getting fonts
		Typeface light_font = Typeface.createFromAsset(getAssets(),
				"Roboto-Light.ttf");
		Typeface thin_font = Typeface.createFromAsset(getAssets(),
				"Roboto-Thin.ttf");
		Typeface medium_font = Typeface.createFromAsset(getAssets(),
				"Roboto-Medium.ttf");
		Typeface condensedbold_font = Typeface.createFromAsset(getAssets(),
				"Roboto-BoldCondensed.ttf");

		// Setting fonts
		result.setTypeface(light_font);
		buttonEqual.setTypeface(thin_font);
		button0.setTypeface(thin_font);
		button1.setTypeface(thin_font);
		button2.setTypeface(thin_font);
		button3.setTypeface(thin_font);
		button4.setTypeface(thin_font);
		button5.setTypeface(thin_font);
		button6.setTypeface(thin_font);
		button7.setTypeface(thin_font);
		button8.setTypeface(thin_font);
		button9.setTypeface(thin_font);
		buttonMinus.setTypeface(thin_font);
		buttonMultiply.setTypeface(thin_font);
		buttonDivide.setTypeface(thin_font);
		buttonPoint.setTypeface(thin_font);
		buttonPlus.setTypeface(thin_font);
		buttonReset.setTypeface(condensedbold_font);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		editText.setSelection(editText.getText().length());
		
		editText.setOnLongClickListener(new OnLongClickListener() { 
	        @Override
	        public boolean onLongClick(View v) {
	        	InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    		mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	            return true;
	        }
	    });

	}

	// public void onClickListenerView(View v) {
	// slidingdrawer.animateClose();
	// en}

	public void addText(String text) {
		int length = text.length();
		
		if (calc.length() == 0) {
			calc += text;
			editText.setText(calc);
			editText.setSelection(length);
		} else {
			
			if (editText.getSelectionStart() == editText.getSelectionEnd()) {
				cursor = editText.getSelectionStart();
				calc = calc.substring(0, cursor) + text + calc.substring(cursor);
				editText.setText(calc);
				if (cursor == (editText.getText().length() - 1)) {
					editText.setSelection(editText.getText().length());
				} else {
					editText.setSelection(cursor + length);
				}
			}
			else {
				int begin = editText.getSelectionStart();
				int end = editText.getSelectionEnd();
				calc = calc.substring(0, begin) + text + calc.substring(end);
				editText.setText(calc);
				editText.setSelection(begin + length);
			}
			
		}
		
	}

	public void onClickListener0(View v) {
		if (b) {

		}

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
		}*/

		addText("0");

	}

	public void onClickListener1(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("1");
	}

	public void onClickListener2(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("2");
	}

	public void onClickListener3(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("3");
	}

	public void onClickListener4(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("4");
	}

	public void onClickListener5(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("5");
	}

	public void onClickListener6(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("6");
	}

	public void onClickListener7(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("7");
	}

	public void onClickListener8(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("8");
	}

	public void onClickListener9(View v) {

		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("9");
	}

	public void onClickListenerPlus(View v) {
		press = '+';
		addText("+");
	}

	public void onClickListenerMinus(View v) {
		press = '-';
		addText("-");
	}

	public void onClickListenerMultiply(View v) {
		press = '*';
		addText("*");
	}

	public void onClickListenerDivide(View v) {
		press = '/';
		addText("/");
	}

	public void onClickListenerPoint(View v) {

		int error = 0;

		if (sum != null) {
			for (int i = 0; i < sum.length(); i++) {
				if (sum.charAt(i) == '.') {
					error = 1;
					Context context = getApplicationContext();
					CharSequence text = "Only one point allowed.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					break;
				}
			}

		}

		if (error == 0) {
			if (sum == null) {
				calc += "0.1";
			} else {
				addText(".");
			}
		}

	}

	public void onClickListenerEqual(View v) {

		MathEval math = new MathEval();
		try {
			editText.setText(Double.toString(math.evaluate(calc)));
			calc = editText.getText().toString();
			press = '=';
			editText.setSelection(editText.getText().length());
		} catch (Exception err) {
			Context context = getApplicationContext();
			CharSequence text = "Input error.";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			// TODO Here is equal
		}
	}

	public void onClickListener_sin(View v) {
		addText("sin(");
	}

	public void onClickListener_cos(View v) {
		addText("cos(");
	}

	public void onClickListener_tan(View v) {
		addText("tan(");
	}

	public void onClickListener_asin(View v) {
		addText("asin(");
	}

	public void onClickListener_acos(View v) {
		addText("acos(");
	}

	public void onClickListener_atan(View v) {
		addText("atan(");
	}

	public void onClickListener_squared_2(View v) {
		addText("^2");
	}

	public void onClickListener_exp(View v) {
		addText("^");
	}

	public void onClickListener_root(View v) {
		addText("sqrt(");
	}

	public void onClickListener_openpar(View v) {
		addText("(");
	}

	public void onClickListener_closepar(View v) {
		addText(")");
	}

	public void onClickListener_mod(View v) {
		addText("%");
	}

	public void onClickListener_pi(View v) {
		/*if (press == '=') {
		onClickListenerReset(buttonReset);
		calc = "";
	}*/
		addText("3.14159265359");
	}

	public void onClickListener_del(View v) {

		/*
		 * if (sum != "") { StringBuilder stringBuilder = new StringBuilder(80);
		 * 
		 * stringBuilder.append(sum);
		 * 
		 * sum = stringBuilder.deleteCharAt(stringBuilder.length() - 1)
		 * .toString();
		 * 
		 * editText.setText(sum); }
		 */
		// calc = removeLastChar(calc);
		System.out.println("ja");
		if (editText.getSelectionStart() > 0 | editText.getSelectionEnd() > 0) {
			
			
			if (editText.getSelectionStart() == editText.getSelectionEnd()) {
				if (calc.length() !=0) {
					cursor = editText.getSelectionStart();
					calc = calc.substring(0, (cursor - 1)) + calc.substring(cursor);
					editText.setText(calc);
					if (cursor == (editText.getText().length() + 1)) {
						editText.setSelection(editText.getText().length());
					} else {
						editText.setSelection(cursor - 1);
					}
					System.out.println("ksja");
				} else {
					
					editText.setText(calc);
					//editText.setSelection(cursor - 1);
				}
			}
			else {
				int begin = editText.getSelectionStart();
				int end = editText.getSelectionEnd();
				calc = calc.substring(0, begin) + calc.substring(end);
				editText.setText(calc);
				editText.setSelection(begin);
			}
			
		}
	}

	public void onClickListenerReset(View v) {
		sum = "";
		countOne = 0;

		result = 0f;
		result_mul = 1f;
		result_div = 1f;
		press = ' ';
		c = 0;

		editText.setText(result.toString());
		editText.setSelection(editText.getText().length());
		calc = "";
	}

	public void onClickListener_dec(View v) {
		addText("dec(");
	}

	public void onClickListener_bin(View v) {
		addText("bin(");
	}

	public void onClickListener_hex(View v) {
		addText("hex(");
	}

	public void onClickListener_oct(View v) {
		addText("oct(");
	}

	/*
	 * public void onClickListener_percent(View v) { int t = 0; for (int s =
	 * (calc.length() - 1); s >= 0; s -= 1) { if
	 * ((!Character.isDigit(calc.charAt(s))) && (calc.charAt(s) != '.')) { t =
	 * s; s = -1; } } String begin = "", end = ""; if (t != 0) { begin =
	 * calc.substring(0, t + 1); end = calc.substring(t + 1); } else { begin =
	 * ""; end = calc.substring(t); } try { double pernu =
	 * (Double.parseDouble(end)) / 100; end = Double.toString(pernu); } catch
	 * (Exception err) { // end=""; } calc = begin + end;
	 * System.out.println(calc); editText.setText(calc);
	 * editText.setSelection(editText.getText().length()); }
	 */

	public void onClickListener_input(View v) {
		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}
	
	
}
