package com.sunpal.appium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sunpal.config.GlobalTestcaseConfiguration;

import static org.testng.AssertJUnit.*;

public class AndroidTester extends AndroidBaseDriver {
	
	@Test
	public void upgradeDemo(){
		WebElement el = driver.findElement(By.xpath(".//*[@text='更新']"));
        assertEquals("更新", el.getText());
        el.click();
        driver.manage().timeouts().implicitlyWait(GlobalTestcaseConfiguration.waitTimeout, TimeUnit.MILLISECONDS);
    }

}
