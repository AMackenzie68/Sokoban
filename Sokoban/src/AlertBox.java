import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class AlertBox {

    public static void display(String alertTitle, String alertMessage){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(alertTitle);
        window.setMinWidth(250);

        Label alertLabel = new Label();
        alertLabel.setText(alertMessage);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox alertLayout = new VBox(10);

        alertLayout.getChildren().addAll(alertLabel,closeButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(alertLayout);

        window.setScene(scene);
        window.showAndWait();

    }
}
