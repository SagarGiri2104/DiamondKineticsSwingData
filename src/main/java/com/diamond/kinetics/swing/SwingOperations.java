package com.diamond.kinetics.swing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwingOperations {
    private List<SwingData> swingDataSamples;
    private SwingData swingDataSample;

    public boolean isValidSwing(String swingOperation, int indexBegin,
                         int indexEnd, int sampleSize){
        if( indexBegin < 0 || indexBegin >= sampleSize
                || indexEnd < 0 || indexEnd >= sampleSize) {
            throw new IndexOutOfBoundsException("invalid indices entered");
        }
        if (swingOperation.equals("backSearchContinuityWithinRange")){
            if (indexBegin < indexEnd){
                throw new IllegalArgumentException("invalid indices entered: indexBegin must be larger than indexEnd");
            }
        }else{
            if (indexBegin > indexEnd){
                throw new IllegalArgumentException("invalid indices entered: indexBegin must be smaller than indexEnd");
            }
        }

        return true;
    }

    public SwingOperations(File swingData) throws FileNotFoundException {
        Scanner scanInput = new Scanner(swingData);
        this.swingDataSamples = new ArrayList<>();
        while (scanInput.hasNextLine()) {
            String[] line = scanInput.nextLine().split(",");
            this.swingDataSample = new SwingData(
                    Integer.decode(line[0]),
                    Double.parseDouble(line[1]),
                    Double.parseDouble(line[2]),
                    Double.parseDouble(line[3]),
                    Double.parseDouble(line[4]),
                    Double.parseDouble(line[5]),
                    Double.parseDouble(line[6]));
            this.swingDataSamples.add(this.swingDataSample);
        }
    }

    public int searchContinuityAboveValue(Column data, int indexBegin, int indexEnd, double threshold,
                                          int winLength) {
        int resultIndex = -1;
        int prevIndex = -1;
        int sampleCount = 0;
        int sampleSize = this.swingDataSamples.size();

        if(!isValidSwing("searchContinuityAboveValue",indexBegin,indexEnd,sampleSize))return -1;

        for (int i = indexBegin; i <= indexEnd; i++) {
            double current = this.swingDataSamples.get(i).getColumnData(data);
            if (current < threshold) {
                resultIndex = -1;
                prevIndex = -1;
                sampleCount = 0;
                continue;
            } else if (current > threshold && prevIndex == -1) {
                sampleCount++;
                resultIndex = i;
                prevIndex = i;
            } else if (current > threshold && prevIndex != -1) {
                sampleCount++;
                prevIndex = i;
            }

            if (sampleCount >= winLength) {
                break;
            }
        }
        return resultIndex == -1 ? -1 : resultIndex+1;
    }


    public int backSearchContinuityWithinRange(Column data, int indexBegin, int indexEnd, double thresholdLo,
                                               double thresholdHi, int winLength) {
        int resultIndex = -1;
        int prevIndex = -1;
        int sampleCount = 0;
        int sampleSize = this.swingDataSamples.size();
        if(!isValidSwing("backSearchContinuityWithinRange",indexBegin,indexEnd,sampleSize))return -1;

        for (int i = indexBegin; i >= indexEnd; i--) {
            double current = this.swingDataSamples.get(i).getColumnData(data);
            if (current > thresholdHi || current < thresholdLo) {
                resultIndex = -1;
                prevIndex = -1;
                sampleCount = 0;
                continue;
            } else if (current > thresholdLo && current < thresholdHi && prevIndex == -1) {
                sampleCount++;
                resultIndex = i;
                prevIndex = i;
            } else if (current > thresholdLo && current < thresholdHi && prevIndex != -1) {
                sampleCount++;
                prevIndex = i;
            }

            if (sampleCount >= winLength) {
                break;
            }
        }
        return resultIndex == -1 ? -1 : resultIndex+1;
    }

    public int searchContinuityAboveValueTwoSignals(Column data1, Column data2, int indexBegin,
                                                    int indexEnd, double threshold1, double threshold2, int winLength) {
        int resultIndex = -1;
        int prevIndex = -1;
        int sampleCount = 0;
        int sampleSize = this.swingDataSamples.size();

        if(!isValidSwing("searchContinuityAboveValueTwoSignals",indexBegin,indexEnd,sampleSize)) return -1;

        for(int i = indexBegin; i<=indexEnd;i++){
            double current1 = this.swingDataSamples.get(i).getColumnData(data1);
            double current2 = this.swingDataSamples.get(i).getColumnData(data2);
            if (current1 < threshold1 || current2 < threshold2) {
                resultIndex = -1;
                prevIndex = -1;
                sampleCount = 0;
                continue;
            } else if (current1 > threshold1 && current2 > threshold2 && prevIndex == -1) {
                sampleCount++;
                resultIndex = i;
                prevIndex = i;
            } else if (current1 > threshold1 && current2 > threshold2 && prevIndex != -1) {
                sampleCount++;
                prevIndex = i;
            }

            if (sampleCount >= winLength) {
                break;
            }
        }

        return resultIndex == -1 ? -1 : resultIndex+1;
    }

    public List<String> searchMultiContinuityWithinRange(Column data,int indexBegin,
                                                         int indexEnd, double thresholdLo, double thresholdHi, int winLength){

        int startIndex = -1;
        int prevIndex = -1;
        int endIndex =-1;
        int sampleCount = 0;

        String pattern = "%s,%s";
        List<String> startAndEndIndexList = new ArrayList<>();
        int sampleSize = this.swingDataSamples.size();

        if(!isValidSwing("searchMultiContinuityWithinRange",indexBegin,indexEnd,sampleSize)) return startAndEndIndexList;

        for (int i = indexBegin; i <= indexEnd; i++) {
            double current = this.swingDataSamples.get(i).getColumnData(data);
            if (current > thresholdHi || current < thresholdLo) {
                startIndex = -1;
                prevIndex = -1;
                sampleCount = 0;
                continue;
            } else if (current > thresholdLo && current < thresholdHi && prevIndex == -1) {
                sampleCount++;
                startIndex = i;
                endIndex = i;
                prevIndex = i;
            } else if (current > thresholdLo && current < thresholdHi && prevIndex != -1) {
                sampleCount++;
                prevIndex = i;
                endIndex = i;
            }

            if (sampleCount >= winLength) {
                startAndEndIndexList.add(String.format(pattern,startIndex+1,endIndex+1));
                startIndex = -1;
                prevIndex = -1;
                endIndex = -1;
                sampleCount = 0;
            }
        }
        return startAndEndIndexList;
    }
}
