package ru.netology.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldCreateMeeting() {

        open("http://localhost:9999/");
        $("[data-test-id=city] [placeholder=Город]").setValue("Ульяновск");
        String s = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [placeholder='Дата встречи']").setValue(s);
        $("[data-test-id=name] [name=name]").setValue("Денисов Валентин");
        $("[data-test-id=phone] [name=phone]").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + s));
    }

}
