package models;

public enum View {
	THEME("theme.fxml"),
	CARD("card.fxml"),
	DIALOG_THEME("dialogTheme.fxml"),
	DIALOG_CARD("dialogCard.fxml");
    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}