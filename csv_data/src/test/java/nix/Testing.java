package nix;

import nix.data.TableData;
import nix.util.CsvTable;
import nix.util.Initializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Testing {

    private List<TableData> init(){
        List<TableData> tableData = null;
        try {

            Optional<CsvTable> table = CsvTable.fromFile(Path.of ("src/main/resources/data.csv"));
            tableData = new Initializer<>(TableData.class).init(table.get());
            for (TableData tableDatum : tableData) {
                tableDatum.print();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tableData;


    }

    @Test
    public void tests(){
        List<TableData> tableData = init();
        List<TableData> comparable = new ArrayList<>();
        comparable.add(new TableData("Mike", 27, "male", "janitor"));
        comparable.add(new TableData("Beth", 23, "female", "recruiter"));

        for(int i=0; i<tableData.size(); i++){
            assertEquals(tableData.get(i).name, comparable.get(i).name);
            assertEquals(tableData.get(i).getAge(), comparable.get(i).getAge());
            assertEquals(tableData.get(i).gender, comparable.get(i).gender);
            assertEquals(tableData.get(i).getOccupation(), comparable.get(i).getOccupation());

        }

    }
}
