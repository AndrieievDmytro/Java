import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StudentList{

    public static void main(String[] args) {
        
        String book = "Students.txt";        
        try (Stream<String> lines =
        Files.lines(Paths.get(book)))
        {
        Map<Integer,List<String>> map = lines
        .flatMap( l -> Stream.of( l.split( "\\s+" ) ) )
        .distinct()
        .filter(w -> Stream.of(w.split("")).distinct().collect( Collectors.toList() ).size() == w.length() )
        .collect( Collectors.groupingBy( w-> w.length() ) );


        List<Integer> lastTwo =
            map.keySet().stream().sorted()
            .collect(Collectors.toList());
            System.out.println("Two lists of the longest " +
                        "words with all letters different:");
            int len = lastTwo.get(lastTwo.size());
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