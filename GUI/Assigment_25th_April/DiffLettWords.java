///////////////////////Andrieiev Dmytro s21353///////////////////////////

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiffLettWords {
    public static void main(String[] args) {
        // String book = "schultz_sklepy_cynamonowe_UTF8.txt";
        String book = "melville_moby_dick.txt";

        int minLen = 5; 
        try (Stream<String> lines =
                Files.lines(Paths.get(book)))
        {
            Map<Integer,List<String>> map = lines
            .flatMap( l -> Stream.of( l.split( "\\P{L}+" ) ) )
            .distinct()
            .filter(w -> w.length() >= minLen )
            .filter(w -> Stream.of(w.split("")).distinct().collect( Collectors.toList() ).size() == w.length() )
            .collect( Collectors.groupingBy( w-> w.length() ) );

            // Stream <String> res1 = lines.flatMap( l -> Stream.of( l.split( "\\P{L}+" ) ) );
            // Stream <String> res2 = res1.distinct();
            // Stream <String> res3 = res2.filter(w -> w.length() >= minLen );
            // Stream <String> res4 = res3.filter(w -> Stream.of(w.split("")).distinct().collect( Collectors.toList() ).size() == w.length());
            // Map<Integer,List<String>> map = res4.collect( Collectors.groupingBy( w-> w.length() ) );


            List<Integer> lastTwo =
                    map.keySet().stream().sorted()
                   .collect(Collectors.toList());
            System.out.println("Two lists of the longest " +
                        "words with all letters different:");
            int len = lastTwo.get(lastTwo.size()-2);
            System.out.println("length " + len + ": " +
                                           map.get(len));
            len = lastTwo.get(lastTwo.size()-1);
            System.out.println("length " + len + ": " +
                                           map.get(len));
        } catch(IOException e) {
            System.out.println("Something wrong...");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
 