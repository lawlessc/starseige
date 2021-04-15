package Player_wealth;

import Entity_properties.Allegiance;
import Entity_types.BaseEntitys.SatBasic;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 11/09/2017.
 */

public final class Player_money {


    private int  wallet;
    private int  wallet_max= 300;//default 300


    private long last_runtime=0;
    private int  update_time=10000;

    public char[] digits;
    public char[] wallet_digits;




    public Player_money()
    {

        updateWalletRead();
        updateWalletLimit();
    }

    //I could just use direct references.
    public void addMoney(int x)
    {
        if((x +wallet) > wallet_max)
        {//this stops the wallet max being exceeded
         //i will need to add other more expensive objects to make
         //a larger wallet worth while
            wallet=wallet_max;
        }
        else
        {
            wallet+=x;
        }

    }

    public boolean subMoney(int x)
    {
        if(x > wallet)//to prevent a player spending more than they have.
        {
            return false;
        }
        else
        {
            wallet-=x;
        }
        updateWalletRead();
        return  true;
    }

    public void addToLimit(int x)
    {
        wallet_max +=x;
        updateWalletLimit();
    }

    public boolean subtractLimit(int x)
    {
        if(wallet_max <= 300)
        {
            updateWalletLimit();
            return false;
        }
        else {

            wallet_max -= x;
            updateWalletLimit();
            return true;
        }
    }


    public int wallamount()
    {return wallet;};


    public void update()
    {

        if(SingletonObjects.runningTime >= (last_runtime+update_time))
        {//function will only run if a certain time has elapsed, should i make this individual to each object though?

            for (int i = 0; i < SingletonObjects.game_objects.size(); i++) {
                if (SingletonObjects.game_objects.get(i) instanceof SatBasic) {
                    energyPayment((SatBasic) SingletonObjects.game_objects.get(i));
                }
            }

            last_runtime=SingletonObjects.runningTime;
            System.out.println("Player Wealth is:" + wallet);

            updateWalletRead();
            updateWalletLimit();

        }



    }





    public void energyPayment(SatBasic ent)
    {
        if(ent.allegiance == Allegiance.Player)
            SingletonObjects.player_money.addMoney(ent.payment);

        updateWalletRead();
    }


    public void updateWalletRead()

    {
        digits = Integer.toString(wallet).toCharArray();
    }

    public void updateWalletLimit()

    {
        wallet_digits = Integer.toString(wallet_max).toCharArray();
    }


}
