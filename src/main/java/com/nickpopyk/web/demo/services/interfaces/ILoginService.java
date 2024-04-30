package com.nickpopyk.web.demo.services.interfaces;

import com.nickpopyk.web.demo.gui.pages.HomePage;
import com.nickpopyk.web.demo.models.User;

public interface ILoginService {
    HomePage login();
    HomePage login(User user);
    HomePage login(String password, String email);
}
