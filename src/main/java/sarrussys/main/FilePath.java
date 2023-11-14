package sarrussys.main;

public enum FilePath {
    file500("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\resources\\conta500.txt"),
    file1000("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\resources\\conta1000.txt"),
    file5000("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\resources\\conta5000.txt"),
    file10000("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\resources\\conta10000.txt"),
    file50000("C:\\Users\\Detemann\\Documents\\Projetos\\ASAQV\\src\\main\\resources\\conta50000.txt");

    private String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
