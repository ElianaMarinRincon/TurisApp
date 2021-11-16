package com.example.turisapp;

import junit.framework.TestCase;

public class RegistroActivityTest extends TestCase {

    public void testValidacionClave() {
        boolean resultado = RegistroActivity.validacionClave("hola");
        assertEquals(false, resultado);
    }
}