import org.junit.Test;
import view.Login;

import java.awt.*;
import java.text.NumberFormat;

public class TestProgram {


    @Test
    public void testLogin(){
        int number = 1 ;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(4);
        numberFormat.setGroupingUsed(false);
        for (int time = 0 ; time < 10; time++){
            String s = numberFormat.format(number);
            System.out.println(s);
            number = Integer.parseInt(s);
            number++;
        }
    }
}
