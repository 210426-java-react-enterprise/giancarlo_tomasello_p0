package com.revature.p0.util;

import com.revature.p0.models.AppUser;
import com.revature.p0.screens.LoginScreen;
import com.revature.p0.screens.Screen;

public class ScreenRouter {
    private LinkedList<Screen> screens = new LinkedList<>();
    private AppUser user;

    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }

    public void navigate(String route){
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if(screen.getRoute().equals(route)){
                screen.render();
            }
        }
    }


    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

}
