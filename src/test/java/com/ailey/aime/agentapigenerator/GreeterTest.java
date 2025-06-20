package com.ailey.aime.agentapigenerator;

import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void hello() {
        String result = Greeter.hello();
        System.out.println(result);
    }
}