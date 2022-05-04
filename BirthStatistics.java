package part1;
import csv.CSVParser;
import csv.CSVRecord;
import csv.SEFileUtil;

import java.io.File;

public class BirthStatistics {

    public final String pathToDirCSVs;

    public BirthStatistics (String pathCSVs){
        pathToDirCSVs = pathCSVs;
    }

    /**
     * This method returns the path to the CSV file of the specified year
     * @param year
     * @return
     */
    private String getPathToCSV (int year){
        File[] csvFiles = new File (pathToDirCSVs).listFiles();
        for (File csvF : csvFiles){
            if (csvF.getName().contains(Integer.toString(year))){
                return csvF.getAbsolutePath();
            }
        }
        return null;
    }

    /**
     * This method returns the row number in the CSV file of the most popular name by the given gender
     * @param year
     * @param gender
     * @return
     */
    private int getCsvRowOfMostPopularNameByGender(int year, String gender){
        int rank = -1;
        SEFileUtil seFileUtil = new SEFileUtil(getPathToCSV(year));
        for (CSVRecord record : seFileUtil.getCSVParser()) {
            String currGender = record.get(1);
            if (currGender.equals(gender)){
                rank = (int) record.getRecordNumber();
                break;
            }
        }
        return rank;
    }

    /**
     * This method calculates and prints the birth sums for male and female by given year
     * @param year
     */
    private void totalBirths(int year){
        int counter = 0;
        int females = 0;
        SEFileUtil seFileUtil = new SEFileUtil(getPathToCSV(year));
        for (CSVRecord record : seFileUtil.getCSVParser()) {
            counter += Integer.parseInt(record.get(2));
            if (record.get(1).equals("F"))
                females += Integer.parseInt(record.get(2));
        }
        System.out.printf("total births = %d\nfemale girls = %d\nmale boys = %d", counter, females, (counter-females)).println();
    }
    /**
     * This method checks the rank of specific name according to year and gender
     * @param year
     * @param name
     * @param gender
     * @return
     */
    private int getRank(int year, String name, String gender){
        int counter = 0;
        SEFileUtil seFileUtil = new SEFileUtil(getPathToCSV(year));
        for (CSVRecord record : seFileUtil.getCSVParser()) {
            String currGender = record.get(1);
            if (currGender.equals(gender)){
                counter +=1;
                String currName = record.get(0);
                if (currName.equals(name)) {
                    return counter;
                }
            }
        }
        return -1;
    }
    /**
     * This method checks the name of specific rank according to year and gender
     * @param year
     * @param rank
     * @param gender
     * @return
     */
    private String getName(int year, int rank, String gender){
        int counter = 0;
        SEFileUtil seFileUtil = new SEFileUtil(getPathToCSV(year));
        for (CSVRecord record : seFileUtil.getCSVParser()) {
            String currGender = record.get(1);
            if (currGender.equals(gender)){
                counter +=1;
                if (counter == rank) {
                    return record.get(0);
                }
            }
        }
        String s = "NO NAME";
        return s;
    }
    /**
     * This method returns the year of the highest rank of a name between range the user gave
     * @param startYear
     * @param endYear
     * @param name
     * @param gender
     * @return
     */
    private int yearOfHighestRank(int startYear, int endYear, String name, String gender){
        int popularYear = -1;
        int popularRank = 0;
        for (int i = startYear+1; i <= endYear; i++){
            int currRank = getRank(i, name, gender);
            if(currRank == -1)
                continue;
            if (popularRank == 0 || popularRank > currRank)
            {
                popularRank = currRank;
                popularYear = i;
            }
        }
        return popularYear;
    }
    /**
     * This method returns the average rank of a name between range the user gave
     * @param startYear
     * @param endYear
     * @param name
     * @param gender
     * @return
     */
    private double getAverageRank(int startYear, int endYear, String name, String gender){
        double counter = 0;
        for (int i = startYear; i <= endYear; i++){
            double currRank = getRank(i, name, gender);
            if(currRank == -1)
                continue;
            else
                counter += currRank;
        }
        return counter/(1+endYear-startYear);
    }
    /**
     * This method sums the number of birth which rank is higher than given name by gender and year
     * @param year
     * @param name
     * @param gender
     * @return
     */
    private int getTotalBirthsRankedHigher(int year, String name, String gender){
        int counter = 0;
        SEFileUtil seFileUtil = new SEFileUtil(getPathToCSV(year));
        for (CSVRecord record : seFileUtil.getCSVParser()) {
            String currGender = record.get(1);
            if (currGender.equals(gender)){
                if (!name.equals(record.get(0)))
                    counter += Integer.parseInt(record.get(2));
                else
                    break;
            }
        }
        return counter;
    }


    public static void main(String[] args) {
        BirthStatistics birthStatistics = new BirthStatistics(args[0]);
        birthStatistics.totalBirths(2010);
        int rank = birthStatistics.getRank(2010, "Asher", "M");
        System.out.println("Rank is: " + rank);
        String name = birthStatistics.getName(2012, 10, "M");
        System.out.println("Name: " + name);
        System.out.println(birthStatistics.yearOfHighestRank(1880, 2010,"David", "M"));
        System.out.println(birthStatistics.yearOfHighestRank(1880, 2014,"Jennifer", "F"));
        System.out.println(birthStatistics.getAverageRank(1880, 2014, "Benjamin", "M"));
        System.out.println(birthStatistics.getAverageRank(1880,2014, "Lois", "F"));
        System.out.println(birthStatistics.getTotalBirthsRankedHigher(2014, "Draco", "M"));
        System.out.print(birthStatistics.getTotalBirthsRankedHigher(2014, "Sophia", "F"));
        System.out.println();

    }


}
