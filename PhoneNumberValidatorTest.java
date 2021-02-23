package utils;

import com.example.demospringboot.utils.PhoneNumberValidator;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class PhoneNumberValidatorTest {
    private static PhoneNumberValidator validator;

    @BeforeAll
    static void setup(){
        validator= new PhoneNumberValidator();
        System.out.println("Executed @BeforeAll Method..");
    }

    @ParameterizedTest
    @CsvSource({
            "+911234567890,true",
            "+911241,false"
    })
    public void itShouldValidatePhoneNumber(String number,boolean isValid){
        boolean checked = validator.test(number);
        System.out.println("Executing Validator Method");
        Assertions.assertThat(checked).isEqualTo(isValid);
    }

    @AfterAll
    static void tearDown(){
        validator = null;
        System.out.println("Executed @AfterAll Method..");
    }

}
