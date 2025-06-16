//Name: Sonal Kamble

import java.util.Scanner;

class Assignment1
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0, countGrades = -1;

		System.out.println("Please enter a list of scores below: ");
		System.out.println("(Enter -1 to stop input)");
		
		int score = 0;

		while(score != -1)
		{
		    score = sc.nextInt();
			countGrades++;

			if(score >= 90 && score <= 100)
				countA++;
			else if(score >= 80 && score <= 89)
				countB++;
			else if(score >= 70 && score <= 79)
				countC++;
			else if(score >= 60 && score <= 69)
				countD++;
			else if(score >= 0 && score <= 59)
				countF++;

		}

        System.out.println();
        
		System.out.println("Total number of grades = " + countGrades);
		System.out.println("Number of A's = " + countA);
		System.out.println("Number of B's = " + countB);
		System.out.println("Number of C's = " + countC);
		System.out.println("Number of D's = " + countD);
		System.out.println("Number of F's = " + countF);

	}
}