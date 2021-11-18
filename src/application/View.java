package application;

public enum View {
	THEME("../ui/fxml/theme.fxml"),
	CARD("../ui/fxml/card.fxml"),
	//DIALOG_THEME("../fxml/dialogTheme.fxml"),
	DIALOG_THEME("/ui/fxml/dialogTheme.fxml"),
	DIALOG_CARD("/ui/fxml/dialogCard.fxml");
	
	//DIALOG_CARD("../fxml/dialogCard.fxml");
	
    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}