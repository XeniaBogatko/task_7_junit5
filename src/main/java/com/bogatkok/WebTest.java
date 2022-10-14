package com.bogatkok;

import com.bogatkok.data.Locale;
import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    //Data Provider
    static Stream<Arguments> wikiMenuByLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.English, List.of("Talk", "Contributions", "Create account", "Log in")),
                Arguments.of(Locale.Deutsch, List.of("Diskussionsseite", "Beiträge", "Benutzerkonto erstellen", "Anmelden"))
        );
    }

    @ValueSource(strings = {"Java", "JUnit"})
    @ParameterizedTest(name = "Check search result in the header on Wiki for {0}")
    void wikiSearchHeaderTest(String data) {
        open("https://ru.wikipedia.org");
        $("#searchInput").setValue(data);
        $("#searchButton").click();
        $("#firstHeading").shouldHave(text(data));
    }

    @CsvSource(value = {
            "Java | Java[прим. 1] — строго типизированный объектно-ориентированный язык программирования общего назначения, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).",
            "JUnit | JUnit — фреймворк для модульного тестирования программного обеспечения на языке Java."
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "Check search result in the text on Wiki for {0}")
    void wikiSearchTextTest(String data, String expectedText) {
        open("https://ru.wikipedia.org");
        $("#searchInput").setValue(data);
        $("#searchButton").click();
        $("#firstHeading").shouldHave(text(data));
        $("#mw-content-text").shouldHave(text(expectedText));
    }

    @MethodSource
    @ParameterizedTest(name = "Check buttons names for locale {0}")
    void wikiMenuByLocaleTest(Locale locale, List<String> menuTexts) {
        open("https://ru.wikipedia.org");
        $$("a.interlanguage-link-target").find(text(locale.name())).click();
        $$("#p-personal a").shouldHave(CollectionCondition.texts(menuTexts));

    }

    @EnumSource(Locale.class)
    @ParameterizedTest
    void wikiLocaleTest(Locale locale) {
        open("https://ru.wikipedia.org");
        $$("a.interlanguage-link-target").find(text(locale.name())).shouldBe(visible);
    }
}


