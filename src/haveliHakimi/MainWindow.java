package haveliHakimi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import stackImplementation.ArrayStack;

public class MainWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private ArrayStack<State> nextStack;
    private ArrayStack<State> previousStack;
    private Button nextButton;
    private Button previousButton;
    private Button exitButton;
    private CanvasWindow canvas;
    // private Line xMiddle;
    // private Line yMiddle;
    private GraphicsText title;
    private Rectangle frame;
    private GraphicsText notGraphicalText;
    // private ArrayList<State> stateArray;
    // private int currentState;
    
    public MainWindow(){
        // stateArray = new ArrayList<>();
        canvas = new CanvasWindow("Haveli Hakimi", CANVAS_WIDTH, CANVAS_HEIGHT);
        nextStack = new ArrayStack<>();
        previousStack = new ArrayStack<>();
        // haveliHakimi(new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4)));
        haveliHakimi(new ArrayList<>(Arrays.asList(2,1,0)));
        setupUI();
        // currentState = 0;
    }

    private void haveliHakimi(ArrayList<Integer> degreeSequence) {   
        if(firstTheorem(degreeSequence) == true && firstDegree(degreeSequence) == true){
            Collections.sort(degreeSequence, Collections.reverseOrder());
            // System.out.println(degreeSequence);
            // State state = new State(degreeSequence);
            // stateStack.push(state);
            // System.out.println(state.degreeSequence);
            nextStack.push(new State(degreeSequence));
            // stateArray.add(new State(degreeSequence));
                for(int i = 0; i < degreeSequence.size(); i++){
                    ArrayList<Integer> tempArr = new ArrayList<>();
                    // Collections.sort(degreeSequence, Collections.reverseOrder());
                    for(int j = 1; j <= degreeSequence.get(0); j++) {
                        tempArr.add(degreeSequence.get(j) - 1);
                    }
                    for(int k = degreeSequence.get(0) + 1; k < degreeSequence.size(); k++) {
                        tempArr.add(degreeSequence.get(k));
                    }
                    Collections.sort(tempArr, Collections.reverseOrder());
                    // System.out.println(tempArr);
                    // State state2 = new State(degreeSequence);
                    // stateStack.push(state2);
                    // System.out.println(state2.degreeSequence); 
                    nextStack.push(new State(tempArr));
                    // stateArray.add(new State(tempArr));
                    degreeSequence=tempArr;
            }
        } else{
            notGraphicalText = new GraphicsText("Degree Sequence Is Not Graphical");
            notGraphicalText.setFontSize(20);
            canvas.add(notGraphicalText,250,300);
        }
        
    }

    private boolean firstTheorem(ArrayList<Integer> degreeSequence) {
        int total = 0;
        for(int num: degreeSequence){
            total += num;
        }
        if(degreeSequence.size() % 2 != 0 && total % 2 != 0){
            System.out.println("Failed First Theorem of Graph Theorem");
            return false;
        }
        return true;
    }

    private boolean firstDegree(ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence);
        if(degreeSequence.get(0) > degreeSequence.size()) {
            System.out.println("First vertex has a higher degree than the order of the graph");
            return false;
        }
        return true;
    }
    
    public int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public int getCanvasHeight(){
        return CANVAS_HEIGHT;
    }

    private void setupUI() {
        nextButton = new Button("Next");
        nextButton.onClick(() -> nextState());
        canvas.add(nextButton, 320, 500);
        previousButton = new Button("Previous");
        previousButton.onClick(()->previousState());
        canvas.add(previousButton, 420, 500);
        exitButton = new Button("Exit");
        exitButton.onClick(() -> canvas.closeWindow());
        canvas.add(exitButton, 370, 540);
        // yMiddle = new Line(new Point(400,0), new Point(400,600));
        // canvas.add(yMiddle);
        // xMiddle = new Line(new Point(0,300), new Point(800,300));
        // canvas.add(xMiddle);
        title = new GraphicsText("Havel-Hakimi Algorithm", 140,100);
        title.setFontSize(50);
        canvas.add(title);
        frame = new Rectangle(CANVAS_WIDTH/4, CANVAS_HEIGHT/4,CANVAS_WIDTH/2 ,CANVAS_HEIGHT/2);
        frame.setStrokeWidth(5);
        canvas.add(frame);
    }

    private void removeGraph() {
        canvas.removeAll();
        canvas.add(nextButton);
        canvas.add(previousButton);
        // canvas.add(yMiddle);
        // canvas.add(xMiddle);
        canvas.add(frame);
        canvas.add(title);
        canvas.add(exitButton);
    }

    private void nextState(){
        removeGraph();
        // currentState++;
        // stateArray.get(currentState).run(canvas);
        // System.out.println(stateArray.get(currentState).degreeSequence);
        State nextState = nextStack.pop();
        previousStack.push(nextState);
        nextState.run(canvas);
        System.out.println(nextState.degreeSequence);
    } 
    
    // private void handleState(ArrayStack<State> popStack, ArrayStack<State> pushStack){
    //     State state = popStack.pop();
    //     pushStack.push(state);
    //     state.run(canvas);
    //     removeGraph();
    // }

    private void previousState(){
        removeGraph();
        // currentState--;
        // stateArray.get(currentState).run(canvas);
        // System.out.println(stateArray.get(currentState).degreeSequence);
        State previousState = previousStack.pop();
        nextStack.push(previousState);
        previousState.run(canvas);
        System.out.println(previousState.degreeSequence);
    }

    public static void main(String[] args) {
        // CanvasWindow canvas = new CanvasWindow("Haveli Hakimi", CANVAS_WIDTH, CANVAS_HEIGHT);
        MainWindow window = new MainWindow();
        // System.out.println(window.stateArray);
        // for(State state: window.stateArray){
        //     System.out.println("test"+state.degreeSequence);
        // }
        
        
        // System.out.println(window.testArray);
        // window.stateStack.pop().run(canvas);
        // canvas.add(stateStack.poll());
        // System.out.println(window.haveliHakimi(new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4))));
    }
}
