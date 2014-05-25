package com.googlecode.jmeter.plugins.webdriver.config;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverConfig extends WebDriverConfig<FirefoxDriver> {

    private static final long serialVersionUID = 100L;
    private static final String GENERAL_USERAGENT_OVERRIDE = "FirefoxDriverConfig.general.useragent.override";

    Capabilities createCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, createProxy());
        return capabilities;
    }

    FirefoxProfile createProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        String userAgentOverride = getUserAgentOverride();
        if(StringUtils.isNotEmpty(userAgentOverride)) {
            profile.setPreference("general.useragent.override", userAgentOverride);
        }
        return profile;
    }

    @Override
    protected FirefoxDriver createBrowser() {
        return new FirefoxDriver(new FirefoxBinary(), createProfile(), createCapabilities());
    }

    public void setUserAgentOverride(String userAgent) {
        setProperty(GENERAL_USERAGENT_OVERRIDE, userAgent);
    }

    public String getUserAgentOverride() {
        return getPropertyAsString(GENERAL_USERAGENT_OVERRIDE);
    }
}
