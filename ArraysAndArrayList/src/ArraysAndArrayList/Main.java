package ArraysAndArrayList;

public class Main {

    public static void main(String[] args) {
        //> examples int midtermscores1
        // [] mean array, = new type[size]
        int midtermscores[] = new int [17];

        // the first item of the 17 is at index 0 - at index 0 == [0]
        midtermscores[0] = 100;
        midtermscores[1] = 97;

        // looping through arrays - they have indexes 0 - length ( not including )
        for ( int index = 0; index < midtermscores.length; index++ ){
           // midtermscores[index] = 100;
            midtermscores[index] = (int)(Math.random() * 41) + 60;
        }

        // the first is the largest, until we know otherwise
        int highestScore = midtermscores[0];
        int lowestScore = midtermscores[0];

        // int totalScore = 0;
        double totalScore = 0;

        // enhanced for loop is READ ONLY
        // each value in midtermscores will be referred to as score in the loop
        for( int score : midtermscores){
            System.out.println(score);
            if ( score > highestScore){
                highestScore = score;
            }
            if ( score < lowestScore){
                lowestScore = score;
            }
            totalScore += score;
        }

        System.out.println("Highest score: " + highestScore);
        System.out.println("Lowest score: " + lowestScore);
        System.out.println("Average score: " + totalScore / midtermscores.length);

        // arrays of reference types will default to NULL -- danger danger
        String[] names = new String[8];
        names[0] = "Eric";
        names[1] = "Jasmine";
        names[2] = "Joy";
        names[3] = "Jeb";
        names[4] = "Jenavieve";
        names[5] = "Journey";
        names[6] = "Jubilee";
        names[7] = "Jackson";

        // read only loop, won't change the values in the array
        for ( String name : names ) {
            name = name.toUpperCase();
            System.out.println(name);
        }

        for ( String name : names ) {
            name = name.toUpperCase();
            System.out.println(name);
        }



    }
}