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

		int searchContinuityAboveValueOutput = -1, backSearchContinuityWithinRangeOutput = -1, searchContinuityAboveValueTwoSignalsOutput = -1;
		List<String> searchMultiContinuityWithinRangeList = new ArrayList<>();
		try{
			searchContinuityAboveValueOutput = swingOperations.searchContinuityAboveValue(Column.AX,11,30,0,5);

			backSearchContinuityWithinRangeOutput= swingOperations.backSearchContinuityWithinRange(Column.AX,30,11,0,5, 5);

			searchContinuityAboveValueTwoSignalsOutput = swingOperations.searchContinuityAboveValueTwoSignals(Column.AX,Column.AY,11,30,-0.30,0.5, 5);

			searchMultiContinuityWithinRangeList = swingOperations.searchMultiContinuityWithinRange(Column.AX,11,50,0,5, 5);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			System.out.println(searchContinuityAboveValueOutput);
			System.out.println(backSearchContinuityWithinRangeOutput);
			System.out.println(searchContinuityAboveValueTwoSignalsOutput);
			System.out.println(searchMultiContinuityWithinRangeList.toString());
		}




	}


}
