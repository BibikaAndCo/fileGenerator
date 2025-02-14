package bib.ccal.filegenerator.vo.grid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GridVo {
    List<ColumnVo> columns = new ArrayList<>();
    List<DataRowVo> data = new ArrayList<>();
}
