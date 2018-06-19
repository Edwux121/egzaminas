package application;

	import javafx.application.Application;
	import javafx.stage.Stage;
	import view.Dashboard;
	
	public class Main extends Application {
		Stage primaryStage;
		@Override
		public void start(Stage primaryStage) {
			Dashboard dashboard = new Dashboard(primaryStage);
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}