package bib.ccal.filegenerator.controller.service;

import bib.ccal.filegenerator.vo.FileVo;
import bib.ccal.filegenerator.vo.grid.Data;
import bib.ccal.filegenerator.vo.grid.GridVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExcelService {
    public FileVo generateExcel(GridVo grid) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet();
            HSSFRow header = sheet.createRow(0);

            Map<String, List<Data>> groupedByIndex = grid.getData().stream()
                    .flatMap(x -> x.getData().stream())
                    .collect(Collectors.groupingBy(Data::getIndex));

            AtomicInteger columnIndex = new AtomicInteger();
            grid.getColumns().forEach(x -> {
                HSSFCell cell = header.createCell(columnIndex.get(), CellType.STRING);
                cell.setCellValue(x.getColumnName());
                cell.setCellType(CellType.STRING);
                List<Data> data = groupedByIndex.getOrDefault(x.getColumnName(), Collections.emptyList());
                columnIndex.getAndIncrement();
            });
            return new FileVo(workbook.getBytes(), "file.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("");
    }
}
