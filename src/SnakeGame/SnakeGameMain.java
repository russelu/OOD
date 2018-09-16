package SnakeGame;

public class SnakeGameMain {
    public static void main(String[] args) {
        System.out.println(" ");

        final Board board = new Board("Board1",7,7);

        final Snake snake = new Snake("Snake1");

        final SnakeGame.Game game = new SnakeGame.Game(board,snake);


        board.setFood(1,2);
        board.setFood(2,2);
        board.setFood(3,2);

        game.printBoard();

        System.out.println("-------------------------- ");

        snake.move(Direction.Right);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();

        snake.move(Direction.Down);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();

        snake.move(Direction.Down);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();

        snake.move(Direction.Right);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();

        snake.move(Direction.Right);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();

        snake.move(Direction.Right);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();

        snake.move(Direction.Down);
        System.out.println("The Snake size is: " + snake.size());
        game.printBoard();


        for (int i = 0; i < 5; i++){
            snake.move(Direction.Left);
            System.out.println("The Snake size is: " + snake.size());
            game.printBoard();
        }

    }
}
