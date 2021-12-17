package pbs.edu.tictactoe;

import javafx.fxml.FXML;


import java.util.Arrays;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



public class Controller {
    int turn = 0; // 1 - XXX @@@  2 - OOO
    int winnerValue = 0; // 1 - XXX @@@ 2 - OOO
    int selectedButton = 0;
    int startingPlayer = getStartingPlayer();

    int[][] board = {{0,0,0}, {0,0,0}, {0,0,0}};



    @FXML
    private Label winner;
    @FXML
    private GridPane gridContainer;

    //get selected button and pass it to play function
    public void selectedButton(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();

        if(source != null){
            selectedButton = Integer.parseInt(source.getId());
            play(source);
        } else {
            selectedButton = 0;
        }

    }

    //function to reset game
    public void resetGame(){

            gridContainer.getChildren().forEach((btn) -> {
                btn.setDisable(false);
                Button bat = (Button) btn;
                bat.setText("");


            });
    //reset board state
        for(int i = 0; i < board.length; i++){
            Arrays.fill(board[i], 0);
        }
        winner.setText("");
        //reset turns etc
        turn = 0;
        winnerValue = 0;

    }

    public void play(Button target){
        if(selectedButton != 0){
            setTurn();
            markButton(target);
            checkTicTac();
            System.out.println(startingPlayer);


        }
    }
    //function to check if tictac
    private void checkTicTac(){
        int board_size = 3;
        int col_count = 0;
        int row_count = 0;
        int diagonal_count = 0;

        //check for column win
        for(int row = 0; row < board_size; row++){

            col_count = 0;
            for(int col = 0; col < 3; col++){
                // - 1 (O) wins @@@ 1 (X) wins
                col_count += (board[row][col] == 1) ? 1 : (board[row][col] == 2) ?  - 1 : 0;


            }
            if(col_count == 3 || col_count == -3) {
                winnerValue = col_count / Math.abs(col_count);
            }
        }

        //check for row win
        for(int col = 0; col < board_size; col++){

            row_count = 0;
            for(int row = 0; row < board_size; row++){
                // - 1 (O) wins @@@ 1 (X) wins
                row_count += (board[row][col] == 1) ? 1 : (board[row][col] == 2) ?  - 1 : 0;
            }

            if(row_count == 3 || row_count == -3){
                winnerValue = row_count / Math.abs(row_count);
            }

        }

        //check for diagonal win ltr
        diagonal_count = 0;
        for(int col = 0; col < board_size; col++) {
            // - 1 (O) wins @@@ 1 (X) wins
            diagonal_count += (board[col][2-col] == 1)?  1 : (board[col][2-col] == 2) ? -1 : 0;
        }
        if (diagonal_count == 3 || diagonal_count == -3){
            winnerValue =  diagonal_count / Math.abs(diagonal_count);
        }


        //check for diagonal win ltr
        diagonal_count = 0;
        for(int col = 0; col < 3; col++){
            diagonal_count += (board[col][col] == 1)?  1 : (board[col][col] == 2 ) ? -1 : 0;
        }
        if (diagonal_count == 3 || diagonal_count == -3){
            winnerValue = diagonal_count / Math.abs(diagonal_count); // Return either 1 or -1
        }

        if(winnerValue == 1){
            winner.setText("Wygrało X");
            setBoardWonState();
        }
        if(winnerValue == -1){
            winner.setText("Wygrał O");
            setBoardWonState();
        }
        if((winnerValue != 1 || winnerValue != -1) && turn ==9){

            winner.setText("Jest remis.");

        }
        System.out.println("winnerValue: " + winnerValue);

    }

    //function to set a board state
    private void setBoardState(int player){
        // player = 1 (X) or 2 (O)
        if(selectedButton == 1 || selectedButton == 2 || selectedButton == 3){
            board[0][selectedButton - 1] = player;
        } else if(selectedButton == 4  || selectedButton == 5 || selectedButton == 6){
            board[1][selectedButton - 4] = player;
        } else if (selectedButton == 7  || selectedButton == 8 || selectedButton == 9){
            board[2][selectedButton - 7] = player;
        }
    }

    private void setBoardWonState(){

    //iterate through board to find empty fields and calculate the id of empty fields
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                int idx = 0;
                if(board[row][col] == 0){
                    switch (row) {
                        case 0 -> idx = 1;
                        case 1 -> idx = 4;
                        case 2 -> idx = 7;
                    }
                    int btn_idx = idx + col;
                    System.out.println(btn_idx);
                    //end the game with all the buttons set to disable
                    gridContainer.getChildren().forEach((btn)->{
                        if(Objects.equals(btn.getId(), String.valueOf(btn_idx))){
                            btn.setDisable(true);
                        }
                    });
                    System.out.println("Indeks pustego pola: " + idx + ":" + col);
                }
            }

        }

    }
    //set text on buttons depenging on which turn it is
    private void markButton(Button target){

        //X move, x always moves first
        if(turn % 2 != 0){
            target.setText("X");
            setBoardState(1);
            System.out.println(Arrays.deepToString(board));
        }
        if(turn % 2 == 0){
            target.setText("O");
            setBoardState(2);
            System.out.println(Arrays.deepToString(board));
        }
        target.setDisable(true);

    }
    //increment turn and print it as an info

    private void setTurn(){
        turn++;
        System.out.println("Turn: " + turn);

    }


    private int getStartingPlayer(){
        return (int) ((Math.random() * (3-1) + 1));
    }


}
