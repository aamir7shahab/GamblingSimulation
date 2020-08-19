class GamblingSimulation {
	//Constant
	public static final int STARTING_DAILY_STAKE = 100;
	public static final int BET = 1;
	public static final int DAYS = 30;
	
	//Global variables
	public static int totalWin = 0;
	public static int totalLoss = 0;

	//method to check win or loss
	private static int checkWin() {
		return (int) Math.floor(Math.random()*10)%2;
	}

	//method for checking resign
	private static int checkResign(int startingStake, int endDayAmount) {
		//initialising control variable
		double winResignAmt = 1.5 * startingStake;
		double lossResignAmt = 0.5 * startingStake;
		if (endDayAmount >= winResignAmt || endDayAmount <= lossResignAmt)
			return 1;
		else
			return 0;
	}

	public static void main(String[] args) {
		//variables to check lucky day and unlucky day
		int luckyDay=0;
		int unluckyDay=0;
		int maxAmtWon=0;
		int maxAmtLoss=0;

		for (int day=1;day<=DAYS;day++) {

			int endDayAmount = STARTING_DAILY_STAKE;
			int dailyWin = 0;
			int dailyLoss = 0;

			while(checkResign(STARTING_DAILY_STAKE, endDayAmount) != 1){

				if (checkWin() == 1){
					endDayAmount += BET;
					dailyWin += BET;
				}
				else {
					endDayAmount -= BET;
					dailyLoss += BET;
				}
			}
			totalWin += dailyWin;
			totalLoss += dailyLoss;

			//funtionality to check lucky and unlucky day
			maxAmtWon = Math.max(maxAmtWon, dailyWin);
			maxAmtLoss = Math.max(maxAmtLoss, dailyLoss);
			
			if (maxAmtWon == dailyWin)
				luckyDay = day;

			if (maxAmtLoss == dailyLoss)
				unluckyDay = day;

			System.out.println("On day "+ day +"\tgambler won:\t"+ dailyWin +"\tand lost:\t"+ dailyLoss +"\tand day result:\t"+ (dailyWin - dailyLoss) +".");
		}

		System.out.println("Total amount won : "+ totalWin);
		System.out.println("Total amount lost : "+ totalLoss);
		System.out.println("Net amount : "+ (totalWin-totalLoss));
		System.out.println("Lucky day is Day "+luckyDay+" amount won is "+maxAmtWon+".");
		System.out.println("Unlucky day is Day "+unluckyDay+" amount lost is "+maxAmtLoss+".");
	}
}