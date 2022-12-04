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

/**
 * MainWindow to show the Haveli Hakimi algorithm.
 */
public class MainWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private ArrayStack<State> nextStack;
    private ArrayStack<State> previousStack;
    private Button nextButton;
    private Button previousButton;
    private Button exitButton;
    private Button enterButton;
    private Button lastStateButton;
    private Button firstStateButton;
    private CanvasWindow canvas;
    private Line xMiddle;
    private Line yMiddle;
    private GraphicsText title;
    private Rectangle graphFrame;
    private Rectangle titleFrame;
    private GraphicsText notGraphicalText;
    private State currentState;
    private TextField inputField;
    private ArrayList<Integer> inputArrayList;
    private GraphicsText reason;

    /**
     * Constructs a MainWindow object.
     */
    public MainWindow(){
        canvas = new CanvasWindow("Havel-Hakimi", CANVAS_WIDTH, CANVAS_HEIGHT);
        setupUI();
    }

    /**
     * Checks whether or not a degree sequence is graphical using the Havel Hakimi algorithm.
     * @return true if the degree sequence is graphical.
     */
    private boolean havelHakimi(ArrayList<Integer> degreeSequence) {  
        if(firstDegree(degreeSequence) && firstTheorem(degreeSequence)){
            nextStack.push(new State(degreeSequence));
            while(degreeSequence.size() > 1){
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
                
                if(isNegative(degreeSequence)){
                    notGraphicalResult();
                    reason.setText("Algorithm resulted in a contradiction");
                    canvas.add(reason,280,360);
                    return false;
                }
            }
            return true;
        } else {
            notGraphicalResult();
        }    
        return false; 
    }

    /**
     * Displays text and removes buttons if the input degree sequence is not graphical.
     */
    private void notGraphicalResult() {
        canvas.add(notGraphicalText,245,340);
        removeButtonIfOnCanvas(lastStateButton);
        removeButtonIfOnCanvas(nextButton);
    }

    /**
     * Checks if any vertex in the graph has a negative degree.
     * @return true if any integer in the degree sequence is a negative number.
     */
    private boolean isNegative(ArrayList<Integer> degreeSequence) {
        for(int i = 0; i < degreeSequence.size();i++){
            if(degreeSequence.get(i) < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whethor or not the the sum of the integers in the degree sequence is even.
     */
    private boolean firstTheorem(ArrayList<Integer> degreeSequence) {
        int total = 0;
        for(int num: degreeSequence){
            total += num;
        }
        if(total % 2 != 0){
            reason.setText("Degree sum is odd");
            canvas.add(reason, 330,360);
            return false;
        }
        return true;
    }

    /**
     * Checks whether or not the maximal degree in the degree sequence is less than the degrees of the other 
     * vertices.
     */
    private boolean firstDegree(ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence, Collections.reverseOrder());
        if(degreeSequence.get(0) >= degreeSequence.size()) {
            reason.setText("Maximal degree is not less than the number of vertices");
            canvas.add(reason, 215,355);
            return false;
        }
        return true;
    }

    /**
     * Creates the user interface.
     */
    private void setupUI() {
        notGraphicalText = new GraphicsText("Degree sequence is not graphical:");
        notGraphicalText.setFontSize(20);
        reason = new GraphicsText();
        reason.setFontSize(15);
        nextButton = new Button("Next");
        nextButton.onClick(() -> goToNextState().run(canvas));
        lastStateButton = new Button("Skip To End");
        lastStateButton.onClick(() -> goToLastState().run(canvas));
        firstStateButton = new Button("Go To Beginning");
        firstStateButton.onClick(() -> goToFirstState().run(canvas));
        enterButton = new Button("Enter");
        canvas.add(enterButton, 150, 500);
        enterButton.onClick(() -> enterDegreeSequence());
        previousButton = new Button("Previous");
        previousButton.onClick(()->goToPreviousState().run(canvas));
        exitButton = new Button("Exit");
        exitButton.onClick(() -> canvas.closeWindow());
        canvas.add(exitButton, 155, 527.5);
        yMiddle = new Line(new Point(400,0), new Point(400,600));
        yMiddle.setStrokeColor(Color.gray);
        canvas.add(yMiddle);
        xMiddle = new Line(new Point(0,(525/2) +75), new Point(800,(525/2)+75));
        xMiddle.setStrokeColor(Color.gray);
        canvas.add(xMiddle);
        title = new GraphicsText("Havel-Hakimi Algorithm", 140,55);
        title.setFontSize(50);
        canvas.add(title);
        titleFrame = new Rectangle(0, 0,CANVAS_WIDTH ,75);
        titleFrame.setStrokeWidth(4);
        canvas.add(titleFrame);
        graphFrame = new Rectangle(0, 75,CANVAS_WIDTH ,CANVAS_HEIGHT-75);
        graphFrame.setStrokeWidth(4);
        canvas.add(graphFrame);
        inputField = new TextField();
        canvas.add(inputField,50,500);
    }

    /**
     * Clears the canvas, but preserves all the UI objects
     */
    private void removeGraph() {
        canvas.removeAll();
        canvas.add(graphFrame);
        canvas.add(titleFrame);
        canvas.add(xMiddle);
        canvas.add(yMiddle);
        canvas.add(yMiddle);
        canvas.add(xMiddle);
        canvas.add(previousButton,550, 500);
        canvas.add(nextButton, 630, 500);
        canvas.add(firstStateButton,505,527.5);
        canvas.add(lastStateButton,630,527.5);
        canvas.add(enterButton);
        canvas.add(inputField);
        canvas.add(title);
        canvas.add(exitButton);
    }

    /**
     * Retrieves the last state in nextStack and removes the graph drawn of the current state.
     */
    private State goToLastState() {
        while(!nextStack.isEmpty()){
            removeGraph();
            goToNextState();
        }
        return currentState;
    }
    
    /**
     * Retrieves the last state in previousStack and removes the graph drawn of the current state.
     */
    private State goToFirstState(){
        while (!previousStack.isEmpty()) {
            removeGraph();
            goToPreviousState();
        }
        return currentState;
    }

    /**
     * Retrieves the state that is on top of nextStack and pushes the currentState to the top of previousStack.
     */
    private State goToNextState(){
        if(!nextStack.isEmpty()){
            removeGraph();
        }
        previousStack.push(currentState);
        currentState = nextStack.pop();
        if (nextStack.isEmpty()) {
            canvas.remove(nextButton);
            canvas.remove(lastStateButton);
        }
        return currentState;
    } 

    /**
     * Retrieves the state that is on top of previousStack and pushes the currentState to the top of nextStack.
     */
    private State goToPreviousState(){
        if (!previousStack.isEmpty()) {
            removeGraph();
        }
        nextStack.push(currentState);
        currentState = previousStack.pop();

        if (previousStack.isEmpty()) {
            canvas.remove(previousButton);
            canvas.remove(firstStateButton);
        }
        return currentState;
    }

    /**
     * Removes button if it is currently displayed on the canvas.
     */
    private void removeButtonIfOnCanvas(Button button) {
        if(canvas.getElementAt(button.getCenter().getX(),button.getCenter().getY()) != null) {
            canvas.remove(button);
        } 
    }

    /**
     * Allows user to input a degree sequence.
     */
    private void enterDegreeSequence() {
        if(!inputField.getText().equals("")) {
            nextStack = new ArrayStack<>();
            previousStack = new ArrayStack<>();
            removeGraph();           
            removeButtonIfOnCanvas(previousButton);
            removeButtonIfOnCanvas(firstStateButton);
            inputArrayList = new ArrayList<>();
            String[] textArr = inputField.getText().replace(" ", "").split(",");
            for(String num: textArr) {
                inputArrayList.add(Integer.parseInt(num)); 
            }
            if(havelHakimi(inputArrayList)){
                currentState = nextStack.pop();
                currentState.run(canvas);
            }
            inputField.setText("");
        } 
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }
}