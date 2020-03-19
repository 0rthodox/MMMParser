import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static List<String> parseMMM(String dir) {
        List<String> parsedData = new ArrayList<>();
        for(Integer i = 10; i <= 300; ++i) {
            parsedData.add(String.valueOf(i) + ' ' + parseSum(buildMMMFileName(dir, i)));
        }
        return parsedData;
    }

    private static String buildMMMFileName(String dir, Integer i) {
        return new StringBuilder(dir).append("/MultiLcOutput_N=").append(i).append(".txt").toString();
    }

    private static double parseSum(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(name))){
            skipLines(reader, 5);
            String parsedLine = reader.readLine();
            parsedLine = parsedLine.substring(9);
            return Double.parseDouble(parsedLine);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }


    }
    private static void skipLines(BufferedReader reader, int lines) throws IOException {
        for (int i = 0; i < lines; ++i) {
            reader.readLine();
        }
    }
    public static void parseMMMToFile(String oldDirectory, String file) {
        try (BufferedWriter ostream = new BufferedWriter(new FileWriter(file))){
            ostream.write("N S");
            ostream.newLine();
            for (String dataString : parseMMM(oldDirectory)) {
                ostream.write(dataString);
                ostream.newLine();
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }
}
