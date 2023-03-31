
public class IncreasingHeartItem implements Item {

   private int sales;
   private int value;
   
   IncreasingHeartItem(){
      sales = 200;
      value = 5;
   }
   @Override
   public void purchase(User user) {
      // TODO Auto-generated method stub
      user.setGold(user.Gold() - this.sales());
      user.setHeart(user.Heart() + this.value());
   }

   @Override
   public int sales() {
      // TODO Auto-generated method stub
      return this.sales;
   }

   public int value(){
      return this.value;
   }
}