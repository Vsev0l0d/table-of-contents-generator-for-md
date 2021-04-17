import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int limit = -1;
        if (args.length < 1) {
            System.out.println("Not enough arguments. \n" +
                    "Enter file path.");
            System.exit(0);
        } else try {
            limit = Integer.parseInt(args[1]);
        } catch (Exception ignored){}

        String fileName = args[0];

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Stack<Integer> level = new Stack<>();
            String line;
            while((line = reader.readLine()) != null ){
                if (line.length() < 1) continue;
                int count = 0;
                while (line.charAt(count) == '#') count++;
                if (count < 1) continue;
                if (limit > 0 && count > limit) continue;
                line = line.substring(count + 1);

                while (level.size() < count) level.push(0);
                while (level.size() > count) level.pop();
                level.push(level.pop() + 1);

                for (int i = 1; i < level.size(); i++) System.out.print("\t");
                System.out.print(level.peek() + ". [" + line + "]" );
                System.out.println("(#"+ line.toLowerCase().replaceAll(" ", "-") + ")");
            }
            Files.readAllLines(Paths.get(fileName)).forEach(System.out::println);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
