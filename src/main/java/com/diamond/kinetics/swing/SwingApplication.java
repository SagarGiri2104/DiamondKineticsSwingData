package com.diamond.kinetics.swing;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

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
		//	SpringApplication.run(SwingApplication.class, args);
		} catch(Exception ex){
			ex.printStackTrace();
		}

		int s1 = -1, s2 = -1, s3 = -1;
		List<String> s4 = new ArrayList<>();
		try{
			s1 = swingOperations.searchContinuityAboveValue(Column.AX,11,30,0,5);

			s2= swingOperations.backSearchContinuityWithinRange(Column.AX,30,11,0,5, 5);

			s3 = swingOperations.searchContinuityAboveValueTwoSignals(Column.AX,Column.AY,11,30,-0.30,0.5, 5);

			s4 = swingOperations.searchMultiContinuityWithinRange(Column.AX,11,50,0,5, 5);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			System.out.println(s4.toString());
		}




	}


}
