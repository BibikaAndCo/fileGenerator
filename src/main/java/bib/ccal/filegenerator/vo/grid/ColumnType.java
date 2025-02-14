package bib.ccal.filegenerator.vo.grid;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.apache.poi.ss.usermodel.CellType;

@Getter
public enum ColumnType {
    NUMERIC(CellType.NUMERIC),
    STRING(CellType.STRING),
    DATETIME(CellType.NUMERIC),
    ;

    private CellType cellType;

    ColumnType(CellType cellType) {
        this.cellType = cellType;
    }

    @JsonCreator
    public static ColumnType forValue(final String value) {
        return ColumnType.valueOf(value);
    }

}
