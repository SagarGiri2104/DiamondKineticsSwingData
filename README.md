# DiamondKineticsSwingData

## 1. Pre-requisites:
IDE: SpringToolSuite4 must be downloaded and installed.
JDK 1.8v

## to run the application:

## 2. Build & Run the project

1. Open the project From File >> import >> maven >> existing maven project >> select the root directory as project folder and click finish. Select src >> main >> java >> com >> diamond >> kinetics >> swing >> SwingApplication.java
2. This File calls all the 4 swing operations or methods. Edit the parameters value passed in methods.
### Approach 1
3. Right Click and select Run >> "SwingApplication" (This will select the latestSwing.csv file from resources folder, replace the .csv file and perform operations)
### Approach 2
1. run the spring application through command "mvn spring-boot:run -Dspring-boot.run.arguments="path/to/latestSwing.csv" and pass the path to csv file in the arguments.
