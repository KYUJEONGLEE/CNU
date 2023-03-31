public class CleanEnemyItem implements Item{

   private int sales;
   
   CleanEnemyItem(){
      sales = 300;
   }
   @Override
   public void purchase(User user) {
      // TODO Auto-generated method stub
      user.setGold(user.Gold() - this.sales());
   }

   @Override
   public int sales() {
      // TODO Auto-generated method stub
      return this.sales;
   }

}