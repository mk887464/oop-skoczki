package backend;

import java.util.ArrayList;
import java.util.LinkedList;

public class Piece {
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position;
    private final Board board;
    private final Color color;

    public Piece(Board board, Vector2d position, Color color){
        this.position = position;
        this.board = board;
        this.color = color;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public Color getColor(){
        return this.color;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : this.observers)
            observer.positionChanged(oldPosition, newPosition);
    }
    public void makeMoves(LinkedList<Vector2d> moves){
        for(Vector2d move: moves){
            positionChanged(this.position, move);
            this.position = move;
        }
    }
}
