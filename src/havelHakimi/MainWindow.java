package havelHakimi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import stackImplementation.ArrayStack;

public class MainWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private ArrayStack<State> nextStack;
    private ArrayStack<State> previousStack;
    private Button nextButton;
    private Button previousButton;
    private Button exitButton;
    private Button enterButton;
    private CanvasWindow canvas;
    private Line xMiddle;
    private Line yMiddle;
    private GraphicsText title;
    private Rectangle infoFrame;
    private Rectangle graphFrame;
    private Rectangle titleFrame;
    private GraphicsText notGraphicalText;
    private State currentState;
    private TextField inputField;
    ArrayList<Integer> inputArrayList;
    
    public MainWindow(){
        canvas = new CanvasWindow("Havel-Hakimi", CANVAS_WIDTH, CANVAS_HEIGHT);
        nextStack = new ArrayStack<>();
        previousStack = new ArrayStack<>();
        setupUI();
        // havelHakimi(new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4)));
        // havelHakimi(new ArrayList<>(Arrays.asList(4,4,3,3,2,2)));
        // havelHakimi(new ArrayList<>(Arrays.asList(2,2,2,1)));
        // havelHakimi(new ArrayList<>(Arrays.asList(2,1,0)));
        // havelHakimi(new ArrayList<>(Arrays.asList(4,3,2,1,0)));
        
    }

    private boolean havelHakimi(ArrayList<Integer> degreeSequence) {   
        if(firstTheorem(degreeSequence) && firstDegree(degreeSequence)){
            Collections.sort(degreeSequence, Collections.reverseOrder());
            nextStack.push(new State(degreeSequence));
            while(!isSumZero(degreeSequence) || !isNegative(degreeSequence)){
                ArrayList<Integer> tempArr = new ArrayList<>();
                for(int j = 1; j <= degreeSequence.get(0); j++) {
                    tempArr.add(degreeSequence.get(j) - 1);
                }
                for(int k = degreeSequence.get(0) + 1; k < degreeSequence.size(); k++) {
                    tempArr.add(degreeSequence.get(k));
                }
                Collections.sort(tempArr, Collections.reverseOrder());
                nextStack.push(new State(tempArr));
                degreeSequence=tempArr;
                if(isSumZero(degreeSequence)){
                    return true;
                }
                if(isNegative(degreeSequence)){
                    canvas.add(notGraphicalText,400,335);
                    canvas.remove(nextButton);
                    canvas.remove(previousButton);
                    return false;
                }
            }
            return true;
        } 
        else {
            canvas.add(notGraphicalText,400,335);
            canvas.remove(nextButton);
            canvas.remove(previousButton);
        }    
        return false; 
    }

    private boolean isSumZero(ArrayList<Integer> degreeSequence) {
        int sum = 0;
        for(int i = 0; i < degreeSequence.size(); i++){
            sum += degreeSequence.get(i);
            if(sum <= 0) {
                return true;
            }
        }   
        return false;
    }

    private boolean isNegative(ArrayList<Integer> degreeSequence) {
        for(int i = 0; i < degreeSequence.size();i++){
            if(degreeSequence.get(i) < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean firstTheorem(ArrayList<Integer> degreeSequence) {
        int total = 0;
        for(int num: degreeSequence){
            total += num;
        }
        if(total % 2 != 0){
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

    private void setupUI() {
        notGraphicalText = new GraphicsText("Degree Sequence Is Not Graphical");
        notGraphicalText.setFontSize(20);
        nextButton = new Button("Next");
        nextButton.onClick(() -> goToNextState());
        enterButton = new Button("Enter");
        canvas.add(enterButton, 610, 500);
        enterButton.onClick(() -> enterDegreeSequence());
        previousButton = new Button("Previous");
        previousButton.onClick(()->goToPreviousState());
        exitButton = new Button("Exit");
        exitButton.onClick(() -> canvas.closeWindow());
        canvas.add(exitButton, 672, 500);
        yMiddle = new Line(new Point(550,0), new Point(550,600));
        yMiddle.setStrokeColor(Color.gray);
        canvas.add(yMiddle);
        xMiddle = new Line(new Point(0,337.5), new Point(800,337.5));
        xMiddle.setStrokeColor(Color.gray);
        canvas.add(xMiddle);
        title = new GraphicsText("Havel-Hakimi Algorithm", 140,55);
        title.setFontSize(50);
        canvas.add(title);
        titleFrame = new Rectangle(0, 0,CANVAS_WIDTH ,75);
        titleFrame.setStrokeWidth(4);
        canvas.add(titleFrame);
        infoFrame = new Rectangle(0, 75,300 ,CANVAS_HEIGHT-75);
        infoFrame.setStrokeWidth(4);
        canvas.add(infoFrame);
        graphFrame = new Rectangle(300, 75,500 ,CANVAS_HEIGHT-75);
        graphFrame.setStrokeWidth(4);
        canvas.add(graphFrame);
        inputField = new TextField();
        canvas.add(inputField,510,500);
    }

    private void removeGraph() {
        canvas.removeAll();
        canvas.add(xMiddle);
        canvas.add(yMiddle);
        canvas.add(yMiddle);
        canvas.add(xMiddle);
        canvas.add(previousButton,370, 500);
        canvas.add(nextButton, 450, 500);
        canvas.add(enterButton);
        canvas.add(inputField);
        canvas.add(infoFrame);
        canvas.add(graphFrame);
        canvas.add(titleFrame);
        canvas.add(title);
        canvas.add(exitButton);
    }

    private void goToNextState(){
        if(!nextStack.isEmpty()){
            removeGraph();
        }
        previousStack.push(currentState);
        currentState = nextStack.pop();
        currentState.run(canvas);
        if (nextStack.isEmpty()) {
            canvas.remove(nextButton);
        }
    } 

    private void goToPreviousState(){
        if (!previousStack.isEmpty()) {
            removeGraph();
        }
        nextStack.push(currentState);
        currentState = previousStack.pop();
        currentState.run(canvas);
        if (previousStack.isEmpty()) {
            canvas.remove(previousButton);
        }
    }


    private void clearStack(ArrayStack<State> stack){
        for(int i = 0; i < stack.size(); i++){
            stack.pop();
        }
    }

    private void enterDegreeSequence() {
        removeGraph();
        // if(canvas.getElementAt(380, 510) != null) {
        //     canvas.remove(previousButton);
        // }
        // clearStack(nextStack);
        // clearStack(previousStack);
        inputArrayList = new ArrayList<>();
        String[] textArr = inputField.getText().split(",");
        for(String num: textArr) {
            inputArrayList.add(Integer.parseInt(num)); 
        }
        if(havelHakimi(inputArrayList)){
            currentState = nextStack.pop();
            currentState.run(canvas);
        }
        canvas.remove(previousButton);
        inputField.setText("");
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
      
    }
}