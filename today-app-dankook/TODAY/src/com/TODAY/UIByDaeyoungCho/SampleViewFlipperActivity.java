package com.TODAY.UIByDaeyoungCho;

import java.io.IOException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

/**
 * ViewFlipper
 *
 * @author Mike
 */
public class SampleViewFlipperActivity extends Activity {

	ScreenViewFlipper flipper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create a DataGridView instance
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        try {
			flipper = new ScreenViewFlipper(this);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        setContentView(flipper, params);

    }
}