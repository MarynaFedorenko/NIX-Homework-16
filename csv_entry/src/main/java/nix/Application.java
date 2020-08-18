package nix;

import nix.data.TableData;
import nix.util.CsvTable;
import nix.util.Initializer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {
        try {

            Optional<CsvTable> table = CsvTable.fromFile(Path.of ("csv_data/src/main/resources/data.csv"));
            List<TableData> tableData = new Initializer<>(TableData.class).init(table.get());
            for (TableData tableDatum : tableData) {
                tableDatum.print();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
