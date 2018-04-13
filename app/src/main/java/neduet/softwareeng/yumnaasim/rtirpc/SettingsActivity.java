package neduet.softwareeng.yumnaasim.rtirpc;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.yumnaasim.rtirpc.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class HomePreference extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings_main);
            Preference locality = findPreference(getString(R.string.mode_key));
            bindPreferenceSummaryToFragment(locality);
            Preference number = findPreference(getString(R.string.change_number));
            bindPreferenceSummaryToFragment(number);
            Preference email = findPreference(getString(R.string.change_email));
            bindPreferenceSummaryToFragment(email);
           /* Preference lvPort = findPreference(getString(R.string.live_stream_port));
            bindPreferenceSummaryToFragment(lvPort);
            Preference channelId = findPreference(getString(R.string.chanel_key));
            bindPreferenceSummaryToFragment(channelId);*/
        }
        private void bindPreferenceSummaryToFragment(Preference preference)
        {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = sharedPreferences.getString(preference.getKey(),"");
            onPreferenceChange(preference,preferenceString);

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String value = o.toString();
            if (preference instanceof ListPreference)
            {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(value);
                if (prefIndex >=0)
                {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            }
            else {
                preference.setSummary(value);
            }
            return true;
        }

    }
}
