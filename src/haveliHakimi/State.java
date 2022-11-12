package haveliHakimi;

import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.GraphicsText;

public class State {
    private MainWindow window = new MainWindow();
    ArrayList<Ellipse> verticeList = new ArrayList<>();
    private int canvasHeight = window.getCanvasHeight();
    private int canvasWidth = window.getCanvasWidth();

    public State(CanvasWindow canvas){
       
    }

    private ArrayList<Ellipse> drawVertices(CanvasWindow canvas, int numVertices) {
        Point midPoint = new Point(canvasWidth/2,canvasHeight/2);
        Ellipse midVertex = new Ellipse(midPoint.getX() - 5, midPoint.getY() - 5 , 10, 10);
        midVertex.setFillColor(Color.red);
        canvas.add(midVertex);
        double interval = 360 / numVertices;
        double degree = 0.0;
        for(Integer i = 1; i <= numVertices; i++){
            createVertex(canvas, degree);
            createText(canvas, degree, i.toString());
            degree += interval;
        }
        return verticeList;
    }

    // private void drawEdges() {

    // }

    private void createVertex(CanvasWindow canvas, double degree) {
        Ellipse vertex = new Ellipse(canvasWidth / 2 - 15, canvasHeight / 2 - 115, 30, 30);
        vertex.setAnchor(15,115);
        vertex.setRotation(degree);
        canvas.add(vertex);
        verticeList.add(vertex);
    }

    private void createText(CanvasWindow canvas, double degree, String num) {
        GraphicsText text = new GraphicsText(num, canvasWidth / 2 - 19, canvasHeight / 2 - 110);
        text.setAnchor(5,95);
        text.setRotation(degree);
        canvas.add(text);
    }

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("State", 800, 600);
        State state = new State(canvas);
        state.drawVertices(canvas, 10);
        
    }
}
