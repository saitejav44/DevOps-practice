package com.example.chatin.chatin;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


class SectionsPagerAdaptor extends FragmentPagerAdapter {
    public SectionsPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;

            case 1:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;

            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return "Chats";
            case 1:
                return "Requests";
            case 2:
                return "Friends";
            default: return null;
        }
    }
}
