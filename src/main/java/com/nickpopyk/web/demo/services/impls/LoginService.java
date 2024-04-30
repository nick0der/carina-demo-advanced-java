package com.nickpopyk.web.demo.services.impls;

import com.nickpopyk.web.demo.gui.components.LoginPopUp;
import com.nickpopyk.web.demo.gui.pages.HomePage;
import com.nickpopyk.web.demo.models.User;
import com.nickpopyk.web.demo.services.interfaces.ILoginService;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class LoginService implements ILoginService {

    private final LoginPopUp loginPopUp;
    private final HashSet<User> userList;
    private WebDriver driver;

    public LoginService(LoginPopUp loginPopUp, WebDriver driver) {
        this.loginPopUp = loginPopUp;
        this.driver = driver;
        userList = new HashSet<>(Arrays.asList(
                new User("thistestn123@gmail.com", "1234567890"),
                new User("mykolarpopyk@ukr.net", "0987654321"))
        );
    }

    @Override
    public HomePage login(){
        User user = userList.stream().skip(new Random().nextInt(userList.size())).findFirst().orElse(null);
        assert user != null;
        return this.login(user);
    }

    @Override
    public HomePage login(User user){
        return this.login(user.getEmail(), user.getPassword());
    }

    @Override
    public HomePage login(String email, String password){
        loginPopUp.typeEmail(email);
        loginPopUp.typePassword(password);
        loginPopUp.clickSubmitButton();
        return new HomePage(driver);
    }
}
