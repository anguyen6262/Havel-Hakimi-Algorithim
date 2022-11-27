package haveliHakimi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.GraphicsText;

public class State {
    List<Color> colors= new ArrayList<>(Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.PINK, Color.GRAY, Color.CYAN));
    // private MainWindow window = new MainWindow();
    // private int canvasHeight = window.getCanvasHeight();
    // private int canvasWidth = window.getCanvasWidth();
    Map<Integer, Ellipse> verticesMap;
    ArrayList<Integer> degreeSequence;
    
    public State(ArrayList<Integer> degreeSequence){
        verticesMap = new HashMap<>();
        this.degreeSequence = degreeSequence;
    }

    private void drawVertices(CanvasWindow canvas, int numVertices) {
        int xPos = 200;
        double yPos = 300;
        for(Integer i = 0; i < numVertices; i++){
            Ellipse vertex = new Ellipse(xPos, yPos, 30, 30);
            vertex.setFillColor(Color.black);
            canvas.add(vertex);
            verticesMap.put(i, vertex);
            xPos+=70;
            if(i % 2 ==1) {
                yPos+=Math.ceil(Math.random()*150);
            } else {
                yPos-=Math.ceil(Math.random()*150);
            }
        }   
    }

    private void drawEdges(CanvasWindow canvas, Graph graph, ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence, Collections.reverseOrder());
        for(int i = 0; i < degreeSequence.size() - 1; i++){
            for(int j = 1; j < degreeSequence.size(); j++){
                if(degreeSequence.get(i) > graph.degree(i)){
                    if(degreeSequence.get(j) > graph.degree(j) && j != i) {
                        graph.addEdge(i, j);    
                        Line edge = new Line(verticesMap.get(i).getCenter(), verticesMap.get(j).getCenter());
                        canvas.add(edge);
                    }
                }
            }
        }
    }
    
    private void drawText(CanvasWindow canvas) {
        for(int i = 0; i < verticesMap.size(); i++){
            verticesMap.get(i).getCenter().getX();
            Integer num = i;
            GraphicsText text = new GraphicsText(num.toString(), verticesMap.get(i).getCenter().getX()-4, verticesMap.get(i).getCenter().getY()+4);
            text.setFillColor(colors.get(i));
            canvas.add(text);
        }
    }

    public void run(CanvasWindow canvas){
        Graph graph = new Graph(this.degreeSequence.size());
        drawVertices(canvas, this.degreeSequence.size());
        drawEdges(canvas, graph, this.degreeSequence);
        drawText(canvas);
    }

    // public static void main(String[] args) {
    //     // CanvasWindow canvas = new CanvasWindow("State", 800, 600);
    //     // State state = new State(new ArrayList<>(Arrays.asList(3,2,3,4,2,4)));
    //     // state.run(canvas, new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4)));
    //     // state.run(canvas, new ArrayList<>(Arrays.asList(3,2,3,4,2,4)));
    //     // System.out.println(graph.toString());            
    // }   
}

