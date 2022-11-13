package haveliHakimi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import javax.sound.midi.Sequence;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;
import stackImplementation.ArrayStack;
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

    // private void createEdges(CanvasWindow canvas, Bag<Integer>[] graph) {
    //     for(int i = 0; i < graph.length; i++){
    //         for(int j = 0; j < verticeList.size(); j++){
    //             if(i.contains(j))
    //         }
    //         Line edge = new Line(new Point(vertex), new Point())
    //         canvas.add(edge);

    //     }

    // }

    private boolean isGraphical(int[] degreeSequence) {
        if(check1(degreeSequence) == true && check2(degreeSequence) == true && haveliHakimi(degreeSequence) == true) {
            return true;
        }
        System.out.println("Degree sequence is not graphical");
        return false;
    }

    // private boolean haveliHakimi(int[] degreeSequence) {   
    //     while(){
    //         for(int i = 0; i < degreeSequence.length; i++){
    //             Arrays.sort(degreeSequence);
    //             for(int j = i + 1; j < degreeSequence[0])
    //         }
    //     }
    //     System.out.println();
    //     if()
    //     return false;
    // }

    private boolean check1(int[] degreeSequence) {
        int total = 0;
        for(int num: degreeSequence){
            total += num;
        }
        if(degreeSequence.length % 2 != 0 && total % 2 != 0){
            return false;
        }
        return true;
    }

    private boolean check2(int[] degreeSequence) {
        Arrays.sort(degreeSequence);
        if(degreeSequence[0] > degreeSequence.length) {
            return false;
        }
        return true;
    }

    private void createVertex(CanvasWindow canvas, double degree) {
        Ellipse vertex = new Ellipse(canvasWidth / 2 - 15, canvasHeight / 2 - 115, 30, 30);
        vertex.setAnchor(15,115);
        vertex.setRotation(degree);
        canvas.add(vertex);
        verticeList.add(vertex);
    }

    private void createText(CanvasWindow canvas, double degree, String num) {
        GraphicsText text = new GraphicsText(num, canvasWidth / 2 - 4, canvasHeight / 2 - 95);
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
