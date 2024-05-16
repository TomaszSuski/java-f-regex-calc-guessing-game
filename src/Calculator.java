import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public String calcAnnuity(String yearlyAddition, int years, String interestRate, int compoundsPerYear) {
//        MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN);

        BigDecimal compounds = new BigDecimal(compoundsPerYear);
        BigDecimal rateByCompounds = new BigDecimal(interestRate).divide(compounds);
        BigDecimal rateByCompoundsPlusOne = BigDecimal.ONE.add(rateByCompounds);
        BigDecimal rateByCompoundsPlusOnePowered = rateByCompoundsPlusOne.pow(compoundsPerYear * years);
        BigDecimal dividend = rateByCompoundsPlusOnePowered.subtract(BigDecimal.ONE);
        BigDecimal interest = dividend.divide(rateByCompounds);
        BigDecimal result = new BigDecimal(yearlyAddition).multiply(interest);

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.US);

        return currencyInstance.format(result);
    }
}