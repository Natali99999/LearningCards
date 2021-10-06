package models;

public enum View {
	THEME("theme.fxml"),
	CARD("card.fxml");
	
    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}