import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
 
public class Campus extends Application {
	//Create Global Population Vars
	int pop1;
	int pop2;
	int pop3;
	int pop4;
	int pop5;
	//Main Method
    public static void main(String[] args) {
        launch(args);
    }
    //GUI Code
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Campus Map");
        
        //Draw Background
        final int IMG_SIZE = 1000;
        Image campus = new Image(getClass().getResourceAsStream("campus.png"));
        
        ImageView backgroundView = new ImageView();
        backgroundView.setImage(campus);
        
        backgroundView.setFitWidth(IMG_SIZE);
        backgroundView.setPreserveRatio(true);
        
        //Draw Each Dorm and Text
        Rectangle dorm1 = new Rectangle();
        double dorm1X = 700;
        double dorm1Y = 500;
        pop1 = 10;
        Text text1 = new Text();
        createDorm(1, dorm1, text1, dorm1X, dorm1Y, pop1, 100, 100, 0.8, Color.CORNFLOWERBLUE);
        
        Rectangle dorm2 = new Rectangle();
        double dorm2X = 250;
        double dorm2Y = 660;
        pop2 = 5;
        Text text2 = new Text();
        createDorm(2, dorm2, text2, dorm2X, dorm2Y, pop2, 100, 100, 0.8, Color.LIME);
        
        Rectangle dorm3 = new Rectangle();
        double dorm3X = 750;
        double dorm3Y = 850;
        pop3 = 5;
        Text text3 = new Text();
        createDorm(3, dorm3, text3, dorm3X, dorm3Y, pop3, 100, 100, 0.6, Color.PINK);
        
        Rectangle dorm4 = new Rectangle();
        double dorm4X = 250;
        double dorm4Y = 800;
        pop4 = 5;
        Text text4 = new Text();
        createDorm(4, dorm4, text4, dorm4X, dorm4Y, pop4, 100, 100, 0.6, Color.TOMATO);
        
        Rectangle dorm5 = new Rectangle();
        double dorm5X = 850;
        double dorm5Y = 280;
        pop5 = 5;
        Text text5 = new Text();
        createDorm(5, dorm5, text5, dorm5X, dorm5Y, pop5, 100, 100, 0.6, Color.DARKMAGENTA);
      
        //Draw Center of All Dorms (Custom Functions are used here for Readability, their definitions are at the end of this file)
        Circle midDorms = new Circle();
        //Adds 50 to each value to go from top left of rectangle to center(width of rectangles is 100)
        double midDormsX = getCenter((dorm1X + 50), (dorm2X + 50), (dorm3X + 50), (dorm4X + 50), (dorm5X + 50), pop1, pop2, pop3, pop4, pop5);
        double midDormsY = getCenter((dorm1Y + 50), (dorm2Y + 50), (dorm3Y + 50), (dorm4Y + 50), (dorm5Y + 50), pop1, pop2, pop3, pop4, pop5);
        midDorms.setCenterX(midDormsX);
        midDorms.setCenterY(midDormsY);
        midDorms.setRadius(10);
        midDorms.setFill(Color.GOLD);
        Text midDormsText = new Text();
        midDormsText.setFont(new Font(20));
        midDormsText.setText("Dorms Center Point\n\t(" + (int)midDormsX + ", " + (int)midDormsY + ")");
        midDormsText.setX(midDormsX - 80);
        midDormsText.setY(midDormsY + 25);
        engageDormController(dorm1, midDorms, midDormsText, 
        		dorm1, dorm2, dorm3, dorm4, dorm5, 
        		pop1, pop2, pop3, pop4, pop5);
        engageDormController(dorm2, midDorms, midDormsText, 
        		dorm1, dorm2, dorm3, dorm4, dorm5, 
        		pop1, pop2, pop3, pop4, pop5);
        engageDormController(dorm3, midDorms, midDormsText, 
        		dorm1, dorm2, dorm3, dorm4, dorm5, 
        		pop1, pop2, pop3, pop4, pop5);
        engageDormController(dorm4, midDorms, midDormsText, 
        		dorm1, dorm2, dorm3, dorm4, dorm5, 
        		pop1, pop2, pop3, pop4, pop5);
        engageDormController(dorm5, midDorms, midDormsText, 
        		dorm1, dorm2, dorm3, dorm4, dorm5, 
        		pop1, pop2, pop3, pop4, pop5);
        
        //Draw Population Text Fields
        TextField popField1 = new TextField(Integer.toString(pop1));
        popField1.setLayoutY(0);
        popField1.textProperty().addListener(new ChangeListener<String>() {//Change Values as text is Changed
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                pop1 = Integer.parseInt(newValue);
                text1.setText("Dorm 1\nPopulation: " + pop1);
                refreshMidDorms(midDorms, midDormsText, 
                		dorm1, dorm2, dorm3, dorm4, dorm5, 
                		pop1, pop2, pop3, pop4, pop5);
            }
        });
        TextField popField2 = new TextField(Integer.toString(pop2));
        popField2.setLayoutY(30);
        popField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                pop2 = Integer.parseInt(newValue);
                text2.setText("Dorm 2\nPopulation: " + pop2);
                refreshMidDorms(midDorms, midDormsText, 
                		dorm1, dorm2, dorm3, dorm4, dorm5, 
                		pop1, pop2, pop3, pop4, pop5);
            }
        });
        TextField popField3 = new TextField(Integer.toString(pop3));
        popField3.setLayoutY(60);
        popField3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                pop3 = Integer.parseInt(newValue);
                text3.setText("Dorm 3\nPopulation: " + pop3);
                refreshMidDorms(midDorms, midDormsText, 
                		dorm1, dorm2, dorm3, dorm4, dorm5, 
                		pop1, pop2, pop3, pop4, pop5);
            }
        });
        TextField popField4 = new TextField(Integer.toString(pop4));
        popField4.setLayoutY(90);
        popField4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                pop4 = Integer.parseInt(newValue);
                text4.setText("Dorm 4\nPopulation: " + pop4);
                refreshMidDorms(midDorms, midDormsText, 
                		dorm1, dorm2, dorm3, dorm4, dorm5, 
                		pop1, pop2, pop3, pop4, pop5);
            }
        });
        TextField popField5 = new TextField(Integer.toString(pop5));
        popField5.setLayoutY(120);
        popField5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                pop5 = Integer.parseInt(newValue);
                text5.setText("Dorm 5\nPopulation: " + pop5);
                refreshMidDorms(midDorms, midDormsText, 
                		dorm1, dorm2, dorm3, dorm4, dorm5, 
                		pop1, pop2, pop3, pop4, pop5);
            }
        });

        //Draw Students
        Circle student1 = new Circle();
        int student1X = 710;
        int student1Y = 580;
        Text sText1 = new Text();
        createStudent(student1, sText1, student1X, student1Y, "Johnny");
        
        Circle student2 = new Circle();
        int student2X = 350;
        int student2Y = 630;
        Text sText2 = new Text();
        createStudent(student2, sText2, student2X, student2Y, "David");
        
        Circle student3 = new Circle();
        int student3X = 750;
        int student3Y = 375;
        Text sText3 = new Text();
        createStudent(student3, sText3, student3X, student3Y, "Peter");
        
        //Draw Center of Students
        Circle midStudy = new Circle();
        int midStudyX = (student1X + student2X + student3X)/3;
        int midStudyY = (student1Y + student2Y + student3Y)/3;
        midStudy.setCenterX(midStudyX);
        midStudy.setCenterY(midStudyY);
        midStudy.setRadius(10);
        midStudy.setFill(Color.BLUE);
        Text midStudyText = new Text();
        midStudyText.setFont(new Font(20));
        midStudyText.setText("Study Center Point\n\t(" + midStudyX + ", " + midStudyY + ")");
        midStudyText.setX(midStudyX - 80);
        midStudyText.setY(midStudyY + 25);
        
        //Add Creations to Scene as a Group
        Group root = new Group(backgroundView, dorm1, dorm2, dorm3, dorm4, dorm5, 
        		text1, text2, text3, text4, text5, midDorms, 
        		student1, student2, student3, sText1, sText2, sText3, midStudy, midStudyText, midDormsText, 
        		popField1, popField2, popField3, popField4, popField5);
        
        primaryStage.setScene(new Scene(root, IMG_SIZE, IMG_SIZE));
        primaryStage.show();
    }
    public static void createDorm(int ID, Rectangle dorm, Text text, double x, double y, int pop, int width, int height, double opacity, Color color)
	{
        dorm.setX(x);
        dorm.setY(y);
        dorm.setWidth(width);
        dorm.setHeight(height);
        dorm.opacityProperty().set(opacity);
        dorm.setFill(color);
        dorm.setOnMouseDragged((MouseEvent me) -> {
        	dorm.setX(me.getX());
            dorm.setY(me.getY());
            text.setX(me.getX() + 20);
            text.setY(me.getY() + 120);
        });
        text.setFont(new Font(20));
        text.setText("Dorm " + ID + "\nPopulation: " + pop);
        text.setX(x + 20);
        text.setY(y + 120);
	}
    public static void createStudent(Circle student, Text text, double x, double y, String name)
    {
        student.setCenterX(x);
        student.setCenterY(y);
        student.setRadius(10);
        student.setFill(Color.CYAN);
        text.setFont(new Font(20));
        text.setText(name);
        text.setX(x - 25);
        text.setY(y + 25);
    }
    public static double getCenter(double n1, double n2, double n3, double n4, double n5, int pop1, int pop2, int pop3, int pop4, int pop5)
    {
    	//(x1+x2+...+xn)/n gets center of all points. 
    	//Multiplying each x value by its population(and dividing by it at the end) increases the influence of high population values on the final center.
    	return((
            		(n1 * pop1) + 
            		(n2 * pop2) + 
            		(n3 * pop3) + 
            		(n4 * pop4) + 
            		(n5 * pop5)
            		)
            		/(pop1+pop2+pop3+pop4+pop5));
    }
    public static void refreshMidDorms(Circle midDorms, Text midDormsText, 
    		Rectangle dorm1, Rectangle dorm2, Rectangle dorm3, Rectangle dorm4, Rectangle dorm5, 
    		int pop1, int pop2, int pop3, int pop4, int pop5)
    {
    	midDorms.setCenterX(getCenter((dorm1.getX() + 50), (dorm2.getX() + 50), (dorm3.getX() + 50), (dorm4.getX() + 50),(dorm5.getX() + 50), 
    			pop1, pop2, pop3, pop4, pop5));
    	midDorms.setCenterY(getCenter((dorm1.getY() + 50), (dorm2.getY() + 50), (dorm3.getY() + 50), (dorm4.getY() + 50),(dorm5.getY() + 50), 
    			pop1, pop2, pop3, pop4, pop5));
    	midDormsText.setX(midDorms.getCenterX() - 80);
        midDormsText.setY(midDorms.getCenterY() + 25);
        midDormsText.setText("Dorms Center Point\n\t(" + (int)midDorms.getCenterX() + ", " + (int)midDorms.getCenterY() + ")");
    }
    public static void engageDormController(Rectangle dorm, Circle midDorms, Text midDormsText, 
    		Rectangle dorm1, Rectangle dorm2, Rectangle dorm3, Rectangle dorm4, Rectangle dorm5, 
    		int pop1, int pop2, int pop3, int pop4, int pop5)
    {
    	dorm.setOnMouseReleased((MouseEvent me) -> {
        	midDorms.setCenterX(getCenter((dorm1.getX() + 50), (dorm2.getX() + 50), (dorm3.getX() + 50), (dorm4.getX() + 50),(dorm5.getX() + 50), 
        			pop1, pop2, pop3, pop4, pop5));
        	midDorms.setCenterY(getCenter((dorm1.getY() + 50), (dorm2.getY() + 50), (dorm3.getY() + 50), (dorm4.getY() + 50),(dorm5.getY() + 50), 
        			pop1, pop2, pop3, pop4, pop5));
        	midDormsText.setX(midDorms.getCenterX() - 80);
            midDormsText.setY(midDorms.getCenterY() + 25);
            midDormsText.setText("Dorms Center Point\n\t(" + (int)midDorms.getCenterX() + ", " + (int)midDorms.getCenterY() + ")");
        });
    }
}
