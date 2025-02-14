package bib.ccal.filegenerator.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class FileVo {
    private byte[] bytes;
    private String filename;

    private String directory = "../files/";

    public FileVo() {
    }

    public FileVo(byte[] bytes, String filename) {
        this.bytes = bytes;
        this.filename = filename;
    }

    public void save() {
        try {
            FileUtils.writeByteArrayToFile(new File(directory + filename), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
