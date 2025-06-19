package com.projectailey;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreeterTest {

    @Test
    void hello() {
        String result = Greeter.hello();
        System.out.println(result);
    }
}