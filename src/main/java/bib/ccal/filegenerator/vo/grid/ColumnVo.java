package bib.ccal.filegenerator.vo.grid;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColumnVo {
    private String columnName;
    private String columnIndex;
    private ColumnType columnType;
}
