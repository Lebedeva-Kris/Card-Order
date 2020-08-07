package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldOpenPage() {
        open("http://localhost:9999");

    }

    @Test
    void shouldDisplayRightRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79999999999");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();

        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldEnterInvalidName() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Kris777");
        form.$("[data-test-id='phone'] input").setValue("+79999999999");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();

        $("[data-test-id='name'].input_invalid");
    }

    @Test
    void shouldEnterInvalidPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Кристина");
        form.$("[data-test-id='phone'] input").setValue("123456789");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();

        $("[data-test-id='phone'].input_invalid");
    }

    @Test
    void shouldUnMarkedCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79999999999");
        form.$("[type='button']").click();

        $("[data-test-id=agreement].input_invalid");
    }

}
