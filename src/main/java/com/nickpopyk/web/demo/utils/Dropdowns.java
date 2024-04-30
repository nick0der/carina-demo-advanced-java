package com.nickpopyk.web.demo.utils;

public enum Dropdowns {
    BRAND("Brand"),
    AVAILABILITY("Availability"),
    TWO_G("2G"),
    THREE_G("3G"),
    FOUR_G("4G"),
    FIVE_G("5G"),
    SIZE("Size"),
    MULTIPLE("Multiple"),
    FORM_FACTOR("Form factor"),
    KEYBOARD("Keyboard"),
    IP_CERTIFICATE("IP Certificate"),
    COLOR("Color"),
    BACK_MATERIAL("Back material"),
    FRAME_MATERIAL("Frame material"),
    OS("OS"),
    OS_VERSION("OS Version"),
    CHIPSET("Chipset"),
    CARD_SLOT("Card slot"),
    TECHNOLOGY("Technology"),
    NOTCH("Notch"),
    FLASH("Flash"),
    WLAN("WLAN (Wi-Fi)"),
    BLUETOOTH("Bluetooth"),
    REMOVABLE("Removable"),
    ORDER("Order");

    private final String value;

    Dropdowns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
