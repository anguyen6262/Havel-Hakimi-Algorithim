package haveliHakimi;

import java.awt.Color;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// import org.jgrapht.*;
// import org.jgrapht.alg.drawing.CircularLayoutAlgorithm2D;
// import org.jgrapht.graph.*;
// import org.jgrapht.nio.*;
// import org.jgrapht.nio.dot.*;
// import org.jgrapht.traverse.*;
// import org.jgrapht.util.SupplierUtil;
// import javax.sound.midi.Sequence;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;
import stackImplementation.ArrayStack;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.GraphicsText;

public class State {
    
    private MainWindow window = new MainWindow();
    private int canvasHeight = window.getCanvasHeight();
    private int canvasWidth = window.getCanvasWidth();
    Map<Integer, Ellipse> verticesMap;
    Map<Integer, Line> edgesMap;

    public State(){
        verticesMap = new HashMap<>();
        edgesMap = new HashMap<>();
    }

    private void drawVertices(CanvasWindow canvas, int numVertices) {
        Point midPoint = new Point(canvasWidth/2,canvasHeight/2);
        Ellipse midVertex = new Ellipse(midPoint.getX() - 5, midPoint.getY() - 5 , 10, 10);
        midVertex.setFillColor(Color.red);
        canvas.add(midVertex);
        double interval = 360 / numVertices;
        double degree = 0.0;
        for(Integer i = 0; i < numVertices; i++){
            createVertex(canvas, degree, i);
            createText(canvas, degree, i.toString());
            degree += interval;
        }   
    }

    private void drawEdges(CanvasWindow canvas, Graph graph, ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence, Collections.reverseOrder());
        for(int i = 0; i < degreeSequence.size() - 1; i++){
            if(degreeSequence.get(i) > graph.degree(i)){
                for(int j = 1; j < degreeSequence.size(); j++){
                    if(degreeSequence.get(j) > graph.degree(j)) {
                        createEdge(canvas, graph, i, j);
                    }
                }
            }
        }
    }

    private void createVertex(CanvasWindow canvas, double degree, int num) {
        Ellipse vertex = new Ellipse(canvasWidth / 2 - 15, canvasHeight / 2 - 115, 30, 30);
        vertex.setAnchor(15,115);
        vertex.setRotation(degree);
        canvas.add(vertex);
        // Point location = canvas.getElementAt(vertex.getCenter()).getCenter();
       
        verticesMap.put(num, vertex);
        // Line line = new Line(new Point(canvasWidth / 2, canvasHeight / 2 ), new Point(canvasWidth / 2, canvasHeight / 2 - 100));
        // line.setRotation(degree);
        // Ellipse vertex = new Ellipse(line.getX2(),line.getY2(), 30, 30);
        // vertex.setCenter(new Point(line.getX2(),line.getY2()));
        // canvas.add(vertex);
        // canvas.add(line);
        // line.setAnchor(0,line.getHeight()/2);
        // verticesMap.put(num, vertex);
    }

    private void createEdge(CanvasWindow canvas, Graph graph, int source, int target) {
        graph.addEdge(source, target);
        Line edge = new Line(verticesMap.get(source).getCenter(), verticesMap.get(target).getCenter());
        canvas.add(edge);
    }
    
    private void createText(CanvasWindow canvas, double degree, String num) {
        GraphicsText text = new GraphicsText(num, canvasWidth / 2 - 4, canvasHeight / 2 - 95);
        text.setAnchor(5,95);
        text.setRotation(degree);
        canvas.add(text);
    }

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("State", 800, 600);
        State state = new State();
        Graph graph = new Graph(7);
        // graph.addEdge(1, 4);
        // graph.addEdge(2, 1);
        // graph.addEdge(3, 4);
        // graph.addEdge(3, 2);
        state.drawVertices(canvas,graph.V());
        state.drawEdges(canvas,graph, new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4)));
        System.out.println(graph.toString());
        // Line test = new Line(state.verticesMap.get(0).getCenter(), state.verticesMap.get(2).getCenter());;
        // canvas.add(test);
        
        
        // state.drawVertices(canvas, graph.V());
    // System.out.println(state.haveliHakimi(new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4))));
    // for(Integer vertex: graph.vertexSet()) {
    //     System.out.println(vertex.deg);
    // }
    // System.out.println(graph.vertexSet());


        //     Graph<Integer, String> g =
    //         new SimpleGraph<>(SupplierUtil.createIntegerSupplier(1), null, false);
    // for(int i=0; i<5; i++)
    //     g.addVertex();
    //     state.createVertex(canvas);
        
    // g.addEdge(1,2,"a1");
    // // Line line = new Line(g.getEdgeSource("a1")., g.getEdgeSource("a1"))
    // g.addEdge(2,3,"b1");
    // g.addEdge(3,4,"b2");
    // g.addEdge(4,5,"b3");
    // g.addEdge(5,1,"a2");
    // removeEdge(g,"b1");
    // removeEdge(g,"b2");
    // removeEdge(g,"b3");
    // System.out.println(g.getEdge(1, 2));        
    //     state.drawVertices(canvas, g.vertexSet().size());
        
    }
        
        
}

