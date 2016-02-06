package TabSwipe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.barri.pruebas.Memory;
import com.example.barri.pruebas.Ranking;

/**
 * Created by barri on 1/2/16.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {"Memory", "Ranking"};
    String user;

    public TabsPagerAdapter(FragmentManager fm, String user) {
        super(fm);
        this.user = user;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                Fragment f = new Memory();
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                f.setArguments(bundle);
                return f;
            case 1:
                return new Ranking();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}