// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;
// public class Test {
//     Map<Integer, List<String>> map = lines.
//     flatMap(l -> Stream.of(l.split("\\P{L}+"))).
//     distinct().
//     filter(w -> {
//         Set<Character> s = new HashSet<>();
//         for (char a : w.toCharArray())
//             s.add(a);
//         return s.size() == w.length() && w.length() >= minLen;
//     }).
//     collect(Collectors.groupingBy(String::length));
// }
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        String book = "schultz_sklepy_cynamonowe_UTF8.txt";
        // String book = "melville_moby_dick.txt";

        int minLen = 5; 
        try (Stream<String> lines =
                Files.lines(Paths.get(book)))
        {
                Map<Integer, List<String>> map = lines.
        flatMap(l -> Stream.of(l.split("\\P{L}+"))).
        distinct().
        filter(w -> {
            Set<Character> s = new HashSet<>();
            for (char a : w.toCharArray())
                s.add(a);
            return s.size() == w.length() && w.length() >= minLen;
        }).
        collect(Collectors.groupingBy(String::length));


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