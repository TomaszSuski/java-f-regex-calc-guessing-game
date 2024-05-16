import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranscriptParser {
    public static void main(String[] args) {

            String receivedText = """
                Student Number:	1234598872			Grade:		11
                Birthdate:		01/02/2000			Gender:	M
                State ID:		8923827123

                Cumulative GPA (Weighted)		3.82
                Cumulative GPA (Unweighted)	3.46
                """;

            String regex = """
                Student\\sNumber:\\s+ # Student Number title and spaces after
                (?<studentNumber>\\d*).* # grabbing student Number
                Grade:\\s+ # Grade title and spaces after
                (?<grade>\\d*).*# grabbing student Grade
                Birthdate:\\s+ # Birthdate title and spaces after
                (?<birthMonth>\\d{2})/(?<birthDay>\\d{2})/(?<birthYear>\\d{4}).* # grabbing student Birth month, Birth day and Birth year
                Gender:\\s+ # Gender title and spaces after
                (?<gender>\\w+).* # Student Gender
                State\\sID:\\s+ # State ID title and spaces after
                (?<stateID>\\d*).* # grabbing State ID
                Cumulative\\sGPA\\s\\(Weighted\\)\\s+ # Cumulative GPA Weighted title and spaces after
                (?<cumGpaWeighted>\\d\\.\\d{2}).*? # grabbing Cumulative GPA Weighted
                Cumulative\\sGPA\\s\\(Unweighted\\)\\s+ # Cumulative GPA Unweighted title and spaces after
                (?<cumGpaUnweighted>\\d\\.\\d{2}).* # grabbing Cumulative GPA Unweighted
                """;

            Pattern pattern = Pattern.compile(regex, Pattern.COMMENTS | Pattern.DOTALL);

            Matcher matcher = pattern.matcher(receivedText);

            if (matcher.matches()) {
                System.out.println(matcher.group("studentNumber"));
                System.out.println(matcher.group("grade"));
                System.out.println(matcher.group("birthMonth"));
                System.out.println(matcher.group("birthDay"));
                System.out.println(matcher.group("birthYear"));
                System.out.println(matcher.group("gender"));
                System.out.println(matcher.group("stateID"));
                System.out.println(matcher.group("cumGpaWeighted"));
                System.out.println(matcher.group("cumGpaUnweighted"));
            }
    }
}
