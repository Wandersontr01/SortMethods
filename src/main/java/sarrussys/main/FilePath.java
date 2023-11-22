package sarrussys.main;

import java.io.File;

public enum FilePath {
    file500("src/main/resources/conta500.txt"),
    file1000("src/main/resources/conta1000.txt"),
    file5000("src/main/resources/conta5000.txt"),
    file10000("src/main/resources/conta10000.txt"),
    file50000("src/main/resources/conta50000.txt"),
    filecpfs("src/main/resources/cpfs_para_pesquisa.txt");

    private String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
    public String getNome() {
        File file = new File(filePath);
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName;
        }
    }
}
