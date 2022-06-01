package com.example.batch54.utils.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidityTest {

    //email validation
    @Test
    public void isEmailTestCorrect1(){
        assertEquals(true,new Validity().isEmailValid("mahadi@diu.edu.bd"));
    }

    @Test
    public void isEmailTestCorrect2(){
        assertEquals(false,new Validity().isEmailValid("mahadi@diu.edu"));
    }

    @Test
    public void isEmailTestCorrect3(){
        assertEquals(false,new Validity().isEmailValid("mahadi hasan"));
    }

    @Test
    public void isEmailTestCorrect4(){
        assertEquals(false,new Validity().isEmailValid("mahadi@diuedu.bd"));
    }

    //name test
    @Test
    public void isNameTestCorrect1(){
        assertEquals(true, new Validity().isNameValid("mahadi"));
    }

    @Test
    public void isNameTestCorrect2(){
        assertEquals(false, new Validity().isNameValid("ow"));
    }


    // student id validation
    @Test
    public void isStudentIDtTestCorrect1(){
        assertEquals(true,new Validity().isStudentIdValid("192-15-13518"));
    }

    @Test
    public void isStudentIDtTestCorrect2(){
        assertEquals(true,new Validity().isStudentIdValid("092-15-13511"));
    }

    @Test
    public void isStudentIDtTestCorrect3(){
        assertEquals(false,new Validity().isStudentIdValid("192-1-13518"));
    }

    @Test
    public void isStudentIDtTestCorrect4(){
        assertEquals(false,new Validity().isStudentIdValid("192-15-1318"));
    }

    @Test
    public void isStudentIDtTestCorrect5(){
        assertEquals(false,new Validity().isStudentIdValid("192-15-135183"));
    }
}