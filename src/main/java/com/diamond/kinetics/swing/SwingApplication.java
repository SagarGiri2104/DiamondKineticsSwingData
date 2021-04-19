package com.diamond.kinetics.swing;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwingApplication {
	private static SwingOperations swingOperations;

	public static void main(String[] args) {
		try {
			File file;
			if(args.length > 0) {
				System.out.println(args[0]);
				file = new File(args[0]);
			}else{
				String fileName = "latestSwing.csv";
				ClassLoader classLoader = SwingApplication.class.getClassLoader();
				URL resource = classLoader.getResource(fileName);
				file = new File(resource.getFile());
			}

			swingOperations = new SwingOperations(file);
		} catch(Exception ex){
			ex.printStackTrace();
		}

		int operation1 = -1, operation2 = -1, operation3 = -1;
		List<String> operation4 = new ArrayList<>();
		try{
			operation1 = swingOperations.searchContinuityAboveValue(Column.AX,11,30,0,5);

			operation2= swingOperations.backSearchContinuityWithinRange(Column.AX,30,11,0,5, 5);

			operation3 = swingOperations.searchContinuityAboveValueTwoSignals(Column.AX,Column.AY,11,30,-0.30,0.5, 5);

			operation4 = swingOperations.searchMultiContinuityWithinRange(Column.AX,11,50,0,5, 5);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			System.out.println(operation1);
			System.out.println(operation2);
			System.out.println(operation3);
			System.out.println(operation4.toString());
		}




	}


}
