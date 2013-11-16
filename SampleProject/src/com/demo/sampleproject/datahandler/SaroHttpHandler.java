package com.demo.sampleproject.datahandler;

import android.util.JsonReader;
import android.util.Log;

import com.demo.sampleproject.AppInfo;
import com.demo.sampleproject.R;
import com.demo.sampleproject.datahandler.collection.SaroList;
import com.demo.sampleproject.datahandler.object.SaroEntry;
import com.demo.sampleproject.datahandler.util.AsyncHttpHandler;
import com.demo.sampleproject.datahandler.util.HttpHandler;
import com.demo.sampleproject.datahandler.util.KabantayJsonAsyncHttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kevinpanuelos on 11/16/13.
 */
public class SaroHttpHandler extends KabantayJsonAsyncHttpHandler<SaroList> {

    @Override
    protected SaroList processJsonDataArray(JsonReader reader) {
        SaroList results = new SaroList();

        try {
            while (reader.hasNext()) {
                results.add(processSaroEntry(reader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    private SaroEntry processSaroEntry(JsonReader reader) throws IOException {
        SaroEntry entry = new SaroEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("year")) {
                entry.setYear(reader.nextInt());
            }
            else if (name.equals("department_code")) {
                entry.setDepartmentCode(reader.nextString());
            }
            else if (name.equals("agency_code")) {
                entry.setAgencyCode(reader.nextString());
            }
            else if (name.equals("operating_unit")) {
                entry.setOperatingUnit(reader.nextString());
            }
            else if (name.equals("spf_code")) {
                entry.setSpfCode(reader.nextString());
            }
            else if (name.equals("sub_spf_code")) {
                entry.setSubSpfCode(reader.nextString());
            }
            else if (name.equals("description")) {
                entry.setDescription(reader.nextString());
            }
            else if (name.equals("fund_code")) {
                entry.setFundCode(reader.nextString());
            }
            else if (name.equals("region")) {
                entry.setRegion(reader.nextString());
            }
            else if (name.equals("appro_src")) {
                entry.setAppropriationSource(reader.nextString());
            }
            else if (name.equals("purpose")) {
                entry.setPurpose(reader.nextString());
            }
            else if (name.equals("program_code")) {
                entry.setProgramCode(reader.nextString());
            }
            else if (name.equals("program_description")) {
                entry.setProgramDescription(reader.nextString());
            }
            else if (name.equals("object_code")) {
                entry.setObjectCode(reader.nextString());
            }
            else if (name.equals("object_description")) {
                entry.setObjectDescription(reader.nextString());
            }
            else if (name.equals("amount")) {
                entry.setAmount(reader.nextLong());
            }
            else if (name.equals("issue_date")) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                try {
                    cal.setTime(sdf.parse(reader.nextString()));
                    entry.setIssueDate(cal);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("SAROHTTPHANDLER", "cannot parse time... format may have changed (MM/dd/yyyy HH:mm:ss format is accepted)");
                    entry.setIssueDate(null);
                }
            }
            else if (name.equals("barcode_no")) {
                entry.setBarcodeNumber(reader.nextString());
            }
            else if (name.equals("saro_no")) {
                entry.setSaroNumber(reader.nextString());
            }
            else if (name.equals("id")) {
                entry.setId(reader.nextString());
            }
            else {
                Log.e("SAROHTTPHANDLER", "schema for this request may have changed... " +
                        "it's the government's fault, if anyone asks.");
            }
        }
        reader.endObject();
        return entry;
    }

}

