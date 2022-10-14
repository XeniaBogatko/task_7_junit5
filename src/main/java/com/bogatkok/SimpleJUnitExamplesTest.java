package com.bogatkok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleJUnitExamplesTest {

    @Disabled("comment T017-15050")
    @Test
    void simpleTest0(){
        Assertions.assertEquals(67,67);
    }

    @DisplayName("Check that profile page will be open...")
    @Test
    void simpleTest1(){

    }

}
