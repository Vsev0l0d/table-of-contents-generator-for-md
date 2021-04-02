import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Stack<Integer> level = new Stack<>();
            String line;
            while((line = reader.readLine()) != null ){
                if (line.length() < 1) continue;
                int count = 0;
                while (line.charAt(count) == '#') count++;
                if (count < 1) continue;
                line = line.substring(count + 1);

                while (level.size() < count) level.push(0);
                while (level.size() > count) level.pop();
                level.push(level.pop() + 1);

                for (int i = 1; i < level.size(); i++) System.out.print("\t");
                System.out.print(level.peek() + ". [" + line + "]" );
                System.out.println("(#"+ line.toLowerCase().replaceAll(" ", "-") + ")");
            }

            System.out.println("\n" + new String(Files.readAllBytes(Paths.get(fileName))));
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
