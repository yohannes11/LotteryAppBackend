package et.com.Lottery.utility;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.Instant;
import java.util.Random;

@Stateless
public  class RandomNumberGenerator {
    public RandomNumberGenerator() {
    }

    @PersistenceContext(unitName = "LotteryApp-persistence-unit", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
	//pass the number of digits and this function will return a random integer of the specified length
	//it can gnerate 10 digits max
	public String generateRandomNumber(int no_digits){
		Random randomNumber=new Random();
		randomNumber.setSeed(Instant.now().toEpochMilli());	
		int firstDigit=randomNumber.nextInt(10);
		int lastDigits=randomNumber.nextInt(1000000000);
		String formattedLastNumber = String.format("%09d",lastDigits);
		String GenereatedId=firstDigit+formattedLastNumber;
		return GenereatedId;
	}
}
