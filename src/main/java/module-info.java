module pbs.edu.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jdk.accessibility;

    opens pbs.edu.tictactoe to javafx.fxml;
    exports pbs.edu.tictactoe;
}