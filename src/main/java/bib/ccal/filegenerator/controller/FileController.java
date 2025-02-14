package bib.ccal.filegenerator.controller;

import bib.ccal.filegenerator.controller.service.ExcelService;
import bib.ccal.filegenerator.vo.FileVo;
import bib.ccal.filegenerator.vo.grid.ColumnType;
import bib.ccal.filegenerator.vo.grid.ColumnVo;
import bib.ccal.filegenerator.vo.grid.GridVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final ExcelService excelService;
    @GetMapping("file")
    public ResponseEntity<FileVo> getFile() throws IOException {
        byte[] file = Files.readAllBytes(Path.of("C:\\Users\\Romanus\\Desktop\\ssh.txt"));
        FileVo fileVo = new FileVo(file, "file.txt");
        return ResponseEntity.ofNullable(fileVo);
    }

    @PostMapping("file")
    public ResponseEntity<?> createFile() throws IOException {
        GridVo gridVo = new GridVo();
        gridVo.setColumns(List.of(new ColumnVo("Название", "name", ColumnType.STRING),
                new ColumnVo("Количество", "count", ColumnType.NUMERIC)));
        FileVo fileVo = excelService.generateExcel(gridVo);
        fileVo.save();
        return ResponseEntity.ok("");
    }
}
