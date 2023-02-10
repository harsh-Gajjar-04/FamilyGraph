import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvParser {
    public ArrayList<String[]> scanData(File filePath) throws FileNotFoundException {

        Scanner scanFile = new Scanner(filePath);
        ArrayList<String[]> list = new ArrayList<>();

        while(scanFile.hasNext()){
            String[] toString = scanFile.next().split(",");
            list.add(toString);
        }
        scanFile.close();

        return list;
    }
}
