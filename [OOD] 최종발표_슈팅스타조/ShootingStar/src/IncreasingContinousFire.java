
public class IncreasingContinousFire implements Item {

	private int sales;

	public IncreasingContinousFire() {
		// TODO Auto-generated constructor stub
		sales = 300;
	}

	@Override
	public void purchase(User user) {
		// TODO Auto-generated method stub
		user.setGold(user.Gold() - this.sales());
		user.setFirespeed(user.firespeed-2);
	}

	@Override
	public int sales() {
		// TODO Auto-generated method stub
		 return this.sales;
	}

}
